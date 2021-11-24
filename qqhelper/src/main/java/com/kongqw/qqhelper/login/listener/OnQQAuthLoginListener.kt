package com.kongqw.qqhelper.login.listener

import com.kongqw.qqhelper.login.bean.QQLoginInfo

interface OnQQAuthLoginListener {
    fun onQQAuthLoginStart()
    fun onQQAuthLoginSuccess(qqLoginInfo: QQLoginInfo?)
    fun onQQAuthLoginCancel()
    fun onQQAuthLoginFail()
    fun onQQAuthLoginError(errorCode: Int?, errorMessage: String?, errorDetail: String?)
    fun onQQAuthLoginWarning(code: Int)
}