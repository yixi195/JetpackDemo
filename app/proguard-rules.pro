# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepattributes *Annotation*

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
   public <init>(android.content.Context);
   public <init>(android.content.Context, android.util.AttributeSet);
   public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembernames class * {
     native <methods>;
 }
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers class * {
    void *(**On*Event);
}
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep class android.support.** {*;}
-keep class android.support.v4.** { *; }

##      Okio相关
## ----------------------------------
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

#--------------- BEGIN: okhttp ----------
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
#--------------- END: okhttp ----------

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8

-dontwarn rx.**
-dontwarn io.reactivex.**
-keepclassmembers class rx.internal.util.unsafe.* {
     *;
}

-dontwarn android.net.**
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
#LRecyclerview_library
-dontwarn com.github.jdsjlzx.**
-keep class com.github.jdsjlzx.**{*;}
#友盟分享
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.zjkf.iot.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keep public class com.turing.yishan.R$*{
    public static final int *;
}

# JPush
-dontoptimize
-dontpreverify
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

# For Baidu Crash.
-keep public class com.baidu.crabsdk.**
-keepclassmembers public class com.baidu.crabsdk.*{
    *;
}
#Native Crash收集请加入如下配置
-keepclassmembers public class com.baidu.crabsdk.sender.NativeCrashHandler{
    *;
}
# gson
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.com.zjkf.iot.model.**{ *; }
-keep class com.google.gson.com.ysl.framework.model.**{ *; }
-keep class com.google.gson.com.aliyun.demo.mode.**{*;}
-keep class com.google.gson.com.shuyu.textutillib.model.**{*;}

-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.zjkf.iot.model.**{ *; }
-keep class com.ysl.framework.model.**{ *; }
-keep class com.aliyun.demo.mode.**{*;}
-keep class com.shuyu.textutillib.model.**{*;}
# Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep public class * implements java.io.Serializable {*;}
# fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }
# 关于反射代码
-keep class com.turing.yishan.R$id{*;}
-keepclasseswithmembernames class com.turing.yishan.utils.DisplayUtil{*;}
-keep public class **.R$* {*;}
-keep public class * extends com.turing.yishan.base.BaseActivity {
      <fields>;
}
# For Glide.
# glide
-keep class com.bumptech.glide.Glide { *; }
-keep class com.bumptech.glide.request.RequestOptions {*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.**
# 从glide4.0开始，GifDrawable没有提供getDecoder()方法，
# 需要通过反射获取gifDecoder字段值，所以需要保持GifFrameLoader和GifState类不被混淆
-keep class com.bumptech.glide.load.resource.gif.GifDrawable$GifState{*;}
-keep class com.bumptech.glide.load.resource.gif.GifFrameLoader {*;}
# glide如果你的API级别<=Android API 27 则需要添加
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
## ----------------------------------
##      UIL相关
## ----------------------------------
-keep class com.nostra13.universalimageloader.** { *; }
-keepclassmembers class com.nostra13.universalimageloader.** {*;}
-dontwarn com.nostra13.universalimageloader.**
## ----------------------------------
##      Picasso相关
## ----------------------------------
-keep class com.squareup.picasso.Picasso { *; }
-dontwarn com.squareup.picasso.**
# ShortcutBadger
-dontwarn me.leolin.shortcutbadger.**
# Location
-keep class com.amap.api.**{*;}
-keep class com.amap.api.services.**{*;}
# ali photo
-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn org.apache.commons.codec.binary.**
#flyco
-dontwarn com.flyco.tablayout.**
-keep class com.flyco.tablaout.**{ *;}
#litepal
-dontwarn org.litepal.*
-keep class org.litepal.** { *; }
-keep enum org.litepal.**
-keep interface org.litepal.** { *; }
-keep public class * extends org.litepal.**
-keepclassmembers class * extends org.litepal.crud.DataSupport{*;}
#X5内核
-keep class com.tencent.smtt.sdk.** {*;}
# EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#Gradle Retrolambda混淆规则
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*
# kotlin
-dontwarn kotlin.**
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}
#jsoup
#-dontwarn org.jsoup.**
#-keep class org.jsoup.**{*;}

#crashreporter
-keep class com.alibaba.motu.crashreporter.MotuCrashReporter{*;}
-keep class com.alibaba.motu.crashreporter.ReporterConfigure{*;}
-keep class com.alibaba.motu.crashreporter.IUTCrashCaughtListener{*;}
-keep class com.ut.mini.crashhandler.IUTCrashCaughtListener{*;}
-keep class com.alibaba.motu.crashreporter.utrestapi.UTRestReq{*;}
-keep class com.alibaba.motu.crashreporter.handler.nativeCrashHandler.NativeCrashHandler{*;}
-keep class com.alibaba.motu.crashreporter.handler.nativeCrashHandler.NativeExceptionHandler{*;}
-keep interface com.alibaba.motu.crashreporter.handler.nativeCrashHandler.NativeExceptionHandler{*;}
#crashreporter3.0以后 一定要加这个
-keep class com.uc.crashsdk.JNIBridge{*;}
-keep class com.aliyun.vod.**{ *;}
-keep class com.aliyun.recorder.**{*;}
-keep class com.a.**{*;}
-keep class component.alivc.com.facearengine.*{*;}
#ijkplayer
-keep class tv.danmaku.ijk.media.player.** {*;}
-keep class tv.danmaku.ijk.media.player.IjkMediaPlayer{*;}
-keep class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi{*;}

-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**
-keep class com.dueeeke.videoplayer.** { *; }
-dontwarn com.dueeeke.videoplayer.**
# 蒙层
-keep class com.app.hubert.guide.**{*;}
-dontwarn com.app.hubert.guide.**
# 状态栏管理
-keep class com.gyf.immersionbar.* {*;}
-dontwarn com.gyf.immersionbar.**
# videoPlay
-keep class org.slf4j.* {*;}
-dontwarn org.slf4j.**
-keep class org.apache.log4j.* {*;}
-dontwarn org.apache.log4j.**
######################conan混淆配置#########################
-keepattributes *Annotation*
-keep class com.alivc.conan.DoNotProguard
-keep,allowobfuscation @interface com.alivc.conan.DoNotProguard
-keep @com.alivc.conan.DoNotProguard class *
-keepclassmembers class * {
    @com.alivc.conan.DoNotProguard *;
}
###### AndroidX #######
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
# 高德地图
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-keep   class com.amap.api.services.**{*;}
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
# LitPal
-keep class org.litepal.** {*;}
-keep class * extends org.litepal.crud.DataSupport {*;}
-keep class * extends org.litepal.crud.LitePalSupport {*;}