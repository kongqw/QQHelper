# QQ开放平台功能封装

[![](https://jitpack.io/v/kongqw/QQHelper.svg)](https://jitpack.io/#kongqw/QQHelper)

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

``` gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

``` gradle
dependencies {
    implementation 'com.github.kongqw:QQHelper:1.1.0'
}
``` 

### Gradle 配置 QQ_APP_ID

``` gradle
android {
    ……
    defaultConfig {
        ……
        manifestPlaceholders = [
                QQ_APP_ID    : '你申请的appid'
        ]
        ……
    }
    ……
}
```

## 初始化（非必要）

``` kotlin
QQHelper.init(true)
```

## QQ分享

### 分享图文链接

``` kotlin
QQHelper.getInstance().shareImageAndText(`activity`, `标题`, `摘要`, `链接`, `缩略图`, `监听接口`)
```

### 分享图片

``` kotlin
QQHelper.getInstance().shareLocalImage(`activity`, `本地图片地址`, `监听接口`)
```

### 分享到QQ控件

``` kotlin
QQHelper.getInstance().shareToQZone(`activity`, `标题`, `摘要`, `链接`, `缩略图`, `监听接口`)
```

### 自定义分享

`自定义Bundle`详见 [API调用说明](http://wiki.open.qq.com/wiki/mobile/API%E8%B0%83%E7%94%A8%E8%AF%B4%E6%98%8E#1.13_.E5.88.86.E4.BA.AB.E6.B6.88.E6.81.A)

``` kotlin
QQHelper.getInstance().customShare(`activity`, `自定义Bundle`, `监听接口`)
```

### 监听接口

``` kotlin
interface OnQQShareListener {
    fun onQQShareStart()
    fun onQQShareComplete(p0: Any?)
    fun onQQShareCancel()
    fun onQQShareError(errorCode: Int?, errorMessage: String?, errorDetail: String?)
}
```

## 授权登录

``` kotlin
QQHelper.getInstance().authLogin(`Activity Context`, `监听接口`)
```

### 监听接口

``` kotlin
interface OnQQAuthLoginListener {
    fun onQQAuthLoginStart()
    fun onQQAuthLoginSuccess(qqLoginInfo: QQLoginInfo?)
    fun onQQAuthLoginCancel()
    fun onQQAuthLoginFail()
    fun onQQAuthLoginError(errorCode: Int?, errorMessage: String?, errorDetail: String?)
}
```