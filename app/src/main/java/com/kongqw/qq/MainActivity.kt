package com.kongqw.qq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kongqw.qqhelper.QQHelper
import com.kongqw.qqhelper.login.bean.QQLoginInfo
import com.kongqw.qqhelper.login.listener.OnQQAuthLoginListener
import com.kongqw.qqhelper.share.listener.OnQQShareListener
import com.tencent.connect.share.QQShare

class MainActivity : AppCompatActivity(), OnQQShareListener, OnQQAuthLoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 分享图文
     */
    fun onShareImageAndText(view: View) {
        QQHelper.getInstance().shareImageAndText(
            this,
            "干活集中营",
            "都是干货，还有妹子图哦",
            "http://gank.io",
            "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg",
            this
        )
    }

    /**
     * 分享图片
     */
    fun onShareLocalImage(view: View) {
        QQHelper.getInstance().shareLocalImage(
            this,
            "/storage/emulated/0/DCIM/Camera/IMG_20190602_220325.jpg",
            this
        )
    }

    /**
     * 分享到QQ空间
     */
    fun onShareToQZone(view: View) {
        QQHelper.getInstance().shareToQZone(
            this,
            "干活集中营",
            "都是干货，还有妹子图哦",
            "http://gank.io",
            "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg",
            this
        )
    }

    /**
     * 分享音乐【自定义】
     */
    fun onCustomShareMusic(view: View) {
        val params = Bundle().apply {
            putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO)
            putString(QQShare.SHARE_TO_QQ_TITLE, "干活集中营")
            putString(QQShare.SHARE_TO_QQ_SUMMARY, "都是干货，还有妹子图哦")
            putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://gank.io")
            putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg")
            putString(
                QQShare.SHARE_TO_QQ_AUDIO_URL,
                "http://m10.music.126.net/20190402151400/39f1d995f4b2d48efa312d1ecb71550f/ymusic/363b/72ef/7661/0b373b6cdfc54e3022ef436c3ad58ec3.mp3"
            )
            putString(QQShare.SHARE_TO_QQ_APP_NAME, String.format("%s%s", getString(R.string.app_name), ""))
            putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE)
        }
        QQHelper.getInstance().customShare(this, params, this)
    }

    /**
     * 分享APP【自定义】
     */
    fun onCustomShareApp(view: View) {
        val params = Bundle().apply {
//            putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP)
            putString(QQShare.SHARE_TO_QQ_TITLE, "干活集中营")
            putString(QQShare.SHARE_TO_QQ_SUMMARY, "都是干货，还有妹子图哦")
            putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg")
            putString(QQShare.SHARE_TO_QQ_APP_NAME, String.format("%s%s", getString(R.string.app_name), ""))
            putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE)
        }
        QQHelper.getInstance().customShare(this, params, this)
    }

    /**
     * 授权登录
     */
    fun onAuthLogin(view: View) {
        QQHelper.getInstance().authLogin(this, this)
    }



    override fun onQQAuthLoginStart() {
        Toast.makeText(applicationContext, "开始登录", Toast.LENGTH_SHORT).show()
    }

    override fun onQQAuthLoginSuccess(qqLoginInfo: QQLoginInfo?) {
        Log.i("MainActivity", "qqLoginInfo = $qqLoginInfo")
        Toast.makeText(applicationContext, "登录成功", Toast.LENGTH_SHORT).show()
    }

    override fun onQQAuthLoginCancel() {
        Toast.makeText(applicationContext, "取消登录", Toast.LENGTH_SHORT).show()
    }

    override fun onQQAuthLoginFail() {
        Toast.makeText(applicationContext, "登录失败", Toast.LENGTH_SHORT).show()
    }

    override fun onQQAuthLoginError(errorCode: Int?, errorMessage: String?, errorDetail: String?) {
        Toast.makeText(applicationContext, "登录异常", Toast.LENGTH_SHORT).show()
    }

    override fun onQQAuthLoginWarning(code: Int) {
        Toast.makeText(applicationContext, "登录警告 code = $code", Toast.LENGTH_SHORT).show()
    }

    override fun onQQShareStart() {
        Toast.makeText(applicationContext, "开始分享", Toast.LENGTH_SHORT).show()
    }

    override fun onQQShareComplete(p0: Any?) {
        Toast.makeText(applicationContext, "分享成功", Toast.LENGTH_SHORT).show()
    }

    override fun onQQShareCancel() {
        Toast.makeText(applicationContext, "取消分享", Toast.LENGTH_SHORT).show()
    }

    override fun onQQShareError(errorCode: Int?, errorMessage: String?, errorDetail: String?) {
        Toast.makeText(applicationContext, "分享失败", Toast.LENGTH_SHORT).show()
    }

    override fun onQQShareWarning(code: Int) {
        // Toast.makeText(applicationContext, "分享警告 code = $code", Toast.LENGTH_SHORT).show()
    }
}
