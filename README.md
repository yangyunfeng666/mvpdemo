# 工程目录

* app 主工程
* basemodule 是一个依赖model 包含了一些组件
* keystore 密匙存储目录
* build.gradle 主构建文件
* config.gradle 包依赖版本文件

## app
工程包文件目录如下

* activity: 存放Activity目录
* adapter: 存放适配器目录
* component.lifecycle: 这个是livedata+viewmodel+retrofit 实现的包目录
* dagger: dagger2使用方法的测试文件类
* data: 数据源定义包目录
* datamanager: 可以忽略，以前定义retrofit访问的管理类
* db: 数据库文件存放目录 包括dao 和entity文件
* fragment: fragment文件存放目录
* http: retrofit的网络定义，拦截器，okhttp的封装类
* hybrid:bybrid scheme协议定义
* mvp:mvp模式的基础类目录
* persistence:持久化目录 包括shareprefrencec和greenDao 升级类
* pojo:数据传递类目录
* MyApplication:工程Application

## basemodule
工程包文件目录如下：

* glide:glide4 图片加载器和自定义view
* hybrid：bybrid 一个回调类目录
* log：日志组件 KyeLogUtils
* mvp：mvp模式的基础定义目录
* network：网络组件的一些基础组件定义
* shareperfence：shareperfence持久化的定义
* util：工具类 DisplayUtil 分辨率的转换
* view：自定义的view 







