package com.poker.naverlogin

import android.content.Context
import com.navercorp.nid.NidOAuth
import com.navercorp.nid.core.data.datastore.NidOAuthInitializingCallback
import com.navercorp.nid.oauth.util.NidOAuthCallback
import com.navercorp.nid.profile.domain.vo.NidProfile
import com.navercorp.nid.profile.util.NidProfileCallback

object NaverLoginUtils {

    /** 应用程序注册后颁发的客户端 ID **/
    const val OAUTH_CLIENT_ID = "VmwjEe27vfjnlYddFJm1"

    /** 应用程序注册后颁发的客户端密钥 **/
    const val OAUTH_CLIENT_SECRET = "PMLSK70YnZ"

    /** 要在 Naver 应用登录屏幕上显示的应用名称 **/
    const val OAUTH_CLIENT_NAME = "PokerNaver"

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
        //只是退出登录，清除了用户的token信息，授权还在
        NidOAuth.logout(callback)
        //授权也取消，调用后下次拉起登录重新授权
        NidOAuth.disconnect(callback)
    }
}