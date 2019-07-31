package com.kongqw.qq

import android.app.Application
import com.kongqw.qqhelper.QQHelper

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // init Library
        // WeChatLoginLibrary(this).init(BuildConfig.DEBUG)

        QQHelper.init(true)
    }
}