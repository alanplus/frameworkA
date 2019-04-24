# framework

[TOC]


### add dependency

Step 1. Add the JitPack repository to your build file

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.alanplus:framework:v0.1'
}
```

### 全局配置

- MyAndroidToolsConfig继承AndroidToolsConfig 修改配置

在Application中 添加

```
AndroidToolsConfig.init(new MyAndroidToolsConfig(this));

```

- 主题样式

```
<style name="MyTheme" parent="XmAppTheme">
    <!-- 标题栏颜色 -->
    <item name="title_bar_color">@android:color/holo_green_light</item>
    <!-- 状态栏颜色 -->
    <item name="status_bar_color">#FFFFFF</item>
    <!-- 状态栏图标是否为白色 -->
    <item name="status_bar_text_is_white">false</item>
    <!-- activity mode 0 不改变 1 沉浸式 2 穿透 -->
    <item name="activity_mode">1</item>
</style>

```


### 主要功能

- 网络框架（封装 Okhttp）
- 数据库框架（公司前人留下的）
- 图片框架 （封装 Glide）
- 音频播放
- 常用的工具类
- 常用的View

### 常用依赖库

- 'com.android.support:appcompat-v7:28.0.0'
- 'com.android.support:recyclerview-v7:26.1.0'
- 'com.android.support.constraint:constraint-layout:1.1.3'
- implementation 'io.reactivex.rxjava2:rxjava:2.0.1'
- implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
- implementation 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
- 'com.github.tbruyelle:rxpermissions:0.10.2@aar'

### 依赖的库

- implementation 'com.google.code.gson:gson:2.7'


### rxpermissions使用说明

```
依赖于
implementation 'io.reactivex.rxjava2:rxjava:2.0.1'
implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'

```

#### 网络框架

根据项目需要 可以 自行封装 ApiConfig

```
ApiResult apiResult = new ApiRequest(new ApiConfig() {
            @Override
            public String url() {
                return "http://www.xxx.com";
            }

            @Override
            public HashMap<String, String> params() {
                return null;
            }

            @Override
            public ApiResult onResultCallback(String text) {
                return null;
            }

            @Override
            public void onOtherErrorCallback(int code) {

            }
        }).executeByGet();
```

### 常用的View

- HomeBottom 底部TAB

1. xml 添加

```
<com.xm.framework.view.homebottom.HomeBottom
    android:id="@+id/home_bottom"
    android:layout_width="match_parent"
    android:layout_height="55dp" />
```

2. 实现接口 IHomeBottomConfig

```
public class RwHomeBottomConfig implements IHomeBottomConfig {

    private Context context;

    public RwHomeBottomConfig(Context context) {
        this.context = context;
    }

    @Override
    public IHomeBottomItem[] getItems() {
        int dt = Color.parseColor("#212832");
        int st = ContextCompat.getColor(ReciteWordApplication.app, R.color.theme1);
        return new IHomeBottomItem[]{
                new HomeBottomItem(context, new HomeBottomItemConfig("首页", dt, st, R.drawable.ic_index1, R.drawable.ic_index2)),
                new HomeBottomItem(context, new HomeBottomItemConfig("我的", dt, st, R.drawable.ic_user1, R.drawable.ic_user2))
        };
    }
}

```

3. 设置 HomeBottom

```
HomeBottom homeBottom = findViewById(R.id.home_bottom);
homeBottom.setHomeBottomConfig(new RwHomeBottomConfig(this), yxViewPager);
```

### BaseActivity

- 封装了EventBus

开启EventBus
```

@Override
protected boolean isOpenEventBus() {
    return true;
}

@Subscribe(threadMode = ThreadMode.MAIN)
public void onEventMainThread(Object eventMessage) {

}

```

### 音频播放 AudioDownloadView

- 加入xml

```
<com.xm.framework.media.download.view.AudioDownloadView
    android:id="@+id/img_en"
    android:layout_width="22dp"
    android:layout_height="22dp"
    style="@style/AudioViewStyleForWord"/>

<style name="AudioViewStyleForWord">
    <!-- 播放类型 --> audio_type_net 网络
    <item name="audio_type">audio_type_net</item>
    <!-- 默认情况下的背景图 -->
    <item name="default_backgroud">@drawable/icon_sound_small_white</item>
    <!-- 动画 根据 anim_type：type_bg 时为帧动画  -->
    <item name="anim">@drawable/anim_play_common</item>
    <!-- 下载时 loading图 固定为旋转动画  -->
    <item name="loading_drawable">@drawable/icon_audio_loading</item>
    <item name="anim_type">type_bg</item>
</style>

```

- 实现接口 IDownloadConfig

```
public class AudioDownloadConfig implements IDownloadConfig {

    private String name;

    public AudioDownloadConfig(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDestName(String s) {
        String path = "";
        return FileTools.join(path, s);
    }

    @Override
    public String getAudioName() {
        return name;
    }
}
```

- 使用

```
AudioDownloadView audioDownloadView = findViewbyId(1);
audioDownloadView.setAudioDownloadConfig(new AudioDownloadConfig(path, false));
```

### NeverCrash

集成了第三方库 NeverCrash

```
 NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.d("Jenly", Log.getStackTraceString(e));
//                e.printStackTrace();
                //异步
                showToast(e.getMessage());


            }
        });
```