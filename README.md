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
- 常用的工具类
- 常用的View

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