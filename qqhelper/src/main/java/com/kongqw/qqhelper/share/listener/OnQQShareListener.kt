package com.kongqw.qqhelper.share.listener

interface OnQQShareListener {

    fun onQQShareStart()

    fun onQQShareComplete(p0: Any?)

    fun onQQShareCancel()

    fun onQQShareError(errorCode: Int?, errorMessage: String?, errorDetail: String?)

    fun onQQShareWarning(code: Int)

    fun onNotInstall()
}