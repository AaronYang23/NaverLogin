package com.poker.naverlogin

import android.content.Context
import com.navercorp.nid.NidOAuth
import com.navercorp.nid.core.data.datastore.NidOAuthInitializingCallback
import com.navercorp.nid.oauth.util.NidOAuthCallback
import com.navercorp.nid.profile.domain.vo.NidProfile
import com.navercorp.nid.profile.util.NidProfileCallback

object NaverLoginUtils {

    /** 应用程序注册后颁发的客户端 ID **/
    const val OAUTH_CLIENT_ID = ""

    /** 应用程序注册后颁发的客户端密钥 **/
    const val OAUTH_CLIENT_SECRET = ""

    /** 要在 Naver 应用登录屏幕上显示的应用名称 **/
    const val OAUTH_CLIENT_NAME = ""

    fun initSDK(context: Context, callback: NidOAuthInitializingCallback? = null) {
        NidOAuth.initialize(context, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME, callback)
    }

    /**
     *  请求登录/授权
     */
    fun requestLogin(context: Context, callback: NidOAuthCallback) {
        NidOAuth.requestLogin(context, callback)
    }

    /**
     *  获取用户信息，在授权成功之后
     */
    fun getUserProfile(callback: NidProfileCallback<NidProfile>) {
        NidOAuth.getUserProfile(callback)
    }

    /**
     *  要删除之前存储的访问令牌和刷新令牌，必须调用
     */
    fun loginOut(callback: NidOAuthCallback) {
        NidOAuth.logout(callback)
        NidOAuth.disconnect(callback)
    }
}