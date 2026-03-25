package com.poker.naverlogin

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.navercorp.nid.NidOAuth
import com.navercorp.nid.core.data.datastore.NidOAuthInitializingCallback
import com.navercorp.nid.oauth.domain.vo.LoginInfo
import com.navercorp.nid.oauth.util.NidOAuthCallback
import com.navercorp.nid.profile.domain.vo.NidProfile
import com.navercorp.nid.profile.util.NidProfileCallback
import com.poker.naverlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityMainBinding? = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        NaverLoginUtils.initSDK(this, object : NidOAuthInitializingCallback {
            override fun onSuccess() {

            }

            override fun onFailure(e: Exception) {

            }

        })
        binding?.apply {
            login.setOnClickListener {
                NaverLoginUtils.requestLogin(this@MainActivity, object : NidOAuthCallback {
                    override fun onSuccess() {
                        var token = NidOAuth.getAccessToken()
                        var refreshToken = NidOAuth.getRefreshToken()
                        Log.i("======", "======token:$token")
                        Log.i("======", "======refreshToken:$refreshToken")
                        message.text = message.text.toString() + "\n" + "token:$token"
                        message.text = message.text.toString() + "\n" + "refreshToken:$refreshToken"
                    }

                    override fun onFailure(errorCode: String, errorDesc: String) {

                    }

                    override fun onAuthCode(loginInfo: LoginInfo) {
                        val auchcode = loginInfo.oauthCode
                        message.text = message.text.toString() + "\n\n" + "auchcode : $auchcode"
                    }
                })
            }

            getUserMsg.setOnClickListener {
                NaverLoginUtils.getUserProfile(
                    object : NidProfileCallback<NidProfile> {
                        override fun onSuccess(result: NidProfile) {
                            Log.i("======", "======result:$result")
                            message.text = message.text.toString() + "\n\n" + "result:$result"
                            var refreshToken = NidOAuth.getRefreshToken()
                            var accessToken = NidOAuth.getAccessToken()
                            var id = result.profile.id
                            //这三个id都不一样， token用accessToken  顺便清楚下accessToken refreshToken的差异
                            message.text = message.text.toString() + "\n\n" + "refreshToken:$refreshToken" + "accessToken:$accessToken" + "id:$id"

                        }

                        override fun onFailure(
                            errorCode: String,
                            errorDesc: String,
                        ) {
                            Log.i("======", "======result:$errorDesc")
                        }
                    },
                )
            }

            cancelAuth.setOnClickListener {
                NaverLoginUtils.loginOut(object : NidOAuthCallback {
                    override fun onSuccess() {
                        var token = NidOAuth.getAccessToken()
                        message.text = message.text.toString() + "\n\n" + "logout : token:$token"
                    }

                    override fun onFailure(errorCode: String, errorDesc: String) {

                    }

                    override fun onAuthCode(loginInfo: LoginInfo) {
                        val auchcode = loginInfo.oauthCode
                        message.text = message.text.toString() + "\n\n" + "auchcode : auchcode:$auchcode"
                    }
                })
            }

        }
    }
}