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

### 初始化

- MyAndroidToolsConfig继承AndroidToolsConfig 修改配置

在Application中 添加

```
AndroidToolsConfig.init(new MyAndroidToolsConfig(this));

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