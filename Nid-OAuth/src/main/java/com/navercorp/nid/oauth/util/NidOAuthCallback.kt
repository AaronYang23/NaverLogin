package com.navercorp.nid.oauth.util

import com.navercorp.nid.oauth.domain.vo.LoginInfo

interface NidOAuthCallback {
    fun onSuccess()
    fun onFailure(errorCode: String, errorDesc: String)
    fun onAuthCode(loginInfo: LoginInfo)
}