package com.kongqw.qqhelper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kongqw.qqhelper.login.QQLoginActivity
import com.kongqw.qqhelper.login.listener.OnQQAuthLoginListener
import com.kongqw.qqhelper.share.QQShareActivity
import com.kongqw.qqhelper.share.listener.OnQQShareListener
import com.kongqw.qqhelper.utils.AppUtils
import com.tencent.connect.share.QQShare
import com.tencent.connect.share.QzoneShare
import com.tencent.tauth.Tencent

class QQHelper {

    companion object {
        var IS_LOGGABLE: Boolean = false

        @Volatile
        private var instance: QQHelper? = null

        var mQQUiListener: OnQQShareListener? = null
        var mOnQQAuthLoginListener: OnQQAuthLoginListener? = null

        @JvmStatic
        fun init(isLoggable: Boolean, isPermissionGranted: Boolean = true) {

            IS_LOGGABLE = isLoggable

            Tencent.setIsPermissionGranted(isPermissionGranted)
        }

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: QQHelper().also { instance = it }
        }
    }

    /**
     * 分享图文
     */
    fun shareImageAndText(activity: Activity, title: String, summary: String, targetUrl: String, imageUrl: String, listener: OnQQShareListener) {
        mQQUiListener = listener
        // 检查QQ是否安装
        if(!AppUtils.isQQInstalled(activity)){
            mQQUiListener?.onNotInstall()
            return
        }
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT)
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title)
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary)
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl)
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE)
        QQShareActivity.share(activity, params)
    }

    /**
     * 分享纯图片
     */
    fun shareLocalImage(activity: Activity, localImageUrl: String, listener: OnQQShareListener) {
        mQQUiListener = listener
        // 检查QQ是否安装
        if(!AppUtils.isQQInstalled(activity)){
            mQQUiListener?.onNotInstall()
            return
        }
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, localImageUrl)
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE)
        QQShareActivity.share(activity, params)
    }

    /**
     * 分享到QQ空间
     */
    fun shareToQZone(activity: Activity, title: String, summary: String, targetUrl: String, imageUrl: String, listener: OnQQShareListener) {
        mQQUiListener = listener
        // 检查QQ是否安装
        if(!AppUtils.isQQInstalled(activity)){
            mQQUiListener?.onNotInstall()
            return
        }
        val params = Bundle()
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT)
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title)
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary)
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl)
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl)
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN)
        QQShareActivity.share(activity, params)
    }

    /**
     * 自定义分享
     */
    fun customShare(activity: Activity, bundle: Bundle, listener: OnQQShareListener) {
        mQQUiListener = listener
        // 检查QQ是否安装
        if(!AppUtils.isQQInstalled(activity)){
            mQQUiListener?.onNotInstall()
            return
        }
        QQShareActivity.share(activity, bundle)
    }

    /**
     * 授权登录
     */
    fun authLogin(context: Context, listener: OnQQAuthLoginListener) {
        mOnQQAuthLoginListener = listener
        // 检查QQ是否安装
        if(!AppUtils.isQQInstalled(context)){
            mOnQQAuthLoginListener?.onNotInstall()
            return
        }
        context.startActivity(Intent(context, QQLoginActivity::class.java))
    }
}