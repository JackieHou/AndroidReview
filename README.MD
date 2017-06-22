[TOC]

# Android

---

## 一、Android进程与线程 
[Android进程与线程](http://www.cnblogs.com/cqumonk/p/4828616.html)

[Android进程与线程基本知识](http://www.cnblogs.com/hanyonglu/archive/2012/04/12/2443262.html)

进程是由系统所创建的，但内存不足的时候，又会被系统所回收。      
进程的级别
> * Foreground Process
> * Visible Process
> * Service Process
> * Background Process
> * Empty Process

进程回收   

- 5---1 回收
- 先回收一个，够了不再回收
- 特殊回收 3 2 1
>内存不够，回收三种线程，内存够了，会自动重启  
>如果用户手动关闭了服务，服务不会重启


## 二、Android清单文件、四大组件、常用的布局文件
### 1 清单文件、备份
[专栏：Android清单文件详解](http://blog.csdn.net/column/details/androidmanifest.html)

### 2 四大组件
四大组件
>Activity        
>BroadcastReceiver      
>ContentProvider      
>Service      

#### 2.1 Activity
##### a 启动模式
>Standard      
>SingleTop      
>SingleTask      
>SingleInstance      

##### b 启动方式

##### c 生命周期
onCreate ---> onStart ---> onResume ----> onPause ---> onStop ---> onDestory     onRestart
![](myfile/img/activity_lifecycle.png)

**两个Activity生命周期**
1.第二个界面部分遮盖（比如打开一个dialog样式activity）      
缩进的代表dialog样式的activity  
```
onCreate
onStart
onResume
onPause
   onCreate
   onStart
   onResume
   onPause
onResum
   onStop
   onDestory
onPause
onStop
onDestory

```
2.第二个界面完全遮盖
```
onCreate
onStart
onResume
onPause
  onCreate
  onStart
  onResume
onStop
  onPause
onRestart
onStart
onResume
  onStop
  onDestory
onPause
onStop
onDestory
```

打开activity的方法

```
 startActivityForResult()
 startActivity()
```

获取放回值的方法

```
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
```
##### d 能够传递的类型
1. 基本数据类型(byte,short,int,long,float,double,char,boolean)
2. String
3. List<String>
4. Bundle
5. Serilazible
6. Parcelable

#### 2.2 BroadcastReceiver
[Android中的广播Broadcast详解](http://blog.csdn.net/jiangwei0910410003/article/details/19150705)
[Android：BroadcastRecevicer广播类型汇总](http://blog.csdn.net/carson_ho/article/details/53160580)

**广播的分类**
>普通广播（不能中断，不能相互传递数据）           
>系统广播       
>有序广播 abortBroadcast    
>本地广播      

**广播的定义**
1. 定义类继承BroadcastReceiver，重写onReceive方法   
2. 清单文件中声明<receiver>,需要在其中配置<intent-filter>指定接收的广播类型     
3. 当接收到匹配的广播之后，就会调用onReceive方法     
4. 有序广播中，如果要控制多个接收者之间的顺序，可在<intent-filter>配置priority属性，系统默认为0，值越大，优先级越      
5. BroadcastReceiver除了在清单文件中声明，也可以在代码中声明，使用registerReceiver方法注册Receiver     

[Changes to system broadcasts(7.1)](NewCharacteristics/7.0.md)

[JobScheduler 任务调度(5.0)](NewCharacteristics/5.0.md)

##### 2.2.1 有序广播
**发送有序广播**     
>a.sendOrderedBroadcast() 发送有序广播；       
b.通过Intent确定广播类型, 携带数据，Intent的数据同样修改无效；       
c.跟普通广播一样，也可以设置相应的权限；       
d.接收者可在<intent-filter>定义android:priority定义优先级，数字越大，优先级越高；       
e.有序广播会被接收者逐个接收，中途可以中断，或添加、修改数据；       
f.可以指定一个自己的广播接收者, 这个接收者将最后一个收到广播、不会被中断、不需要任何权限、不需要配置；       
g.可以指定一个Handler用来在自己的接收者中进行线程通信；       

Intent的数据不可一修改，但是我们能够通过 **setResultExtras(bundle)** 和 **Bundle bundle = getResultExtras(false);**来传递的数据可以呀改变的
例如

[OrderReceiverActivity.java](app/src/main/java/com/qwm/androidreview/receiverdemo/orderreceiver/OrderReceiverActivity.java)     
[OrderTest01Receiver.java](app/src/main/java/com/qwm/androidreview/receiverdemo/orderreceiver/OrderTest01Receiver.java)     
[OrderTest02Receiver.java](app/src/main/java/com/qwm/androidreview/receiverdemo/orderreceiver/OrderTest02Receiver.java)     
[OrderTest03Receiver.java](app/src/main/java/com/qwm/androidreview/receiverdemo/orderreceiver/OrderTest03Receiver.java)     

##### 2.2.2 本地广播

[LocalBroadcastManager—创建更高效、更安全的广播](http://blog.csdn.net/u010687392/article/details/49744579#comments)

----

#### 2.3 ContentProvider

[SQLite](#sqlite)

#### 2.4 Service


### 3 常用的布局文件




## 三、数据的存储
[存储选项](https://developer.android.google.cn/guide/topics/data/data-storage.html)
数据的存储主要分为5大类
> 1. SharedPreferences   
  2. 文件存储
       1. Internal Storage(内部存储)
       2. External Storage(外部存储)
  3. ContentProvider
  4. SQLite Databases(sqlite数据库)
  5. Network Connection(网络)
  
  
### 3.1 SharedPreferences  
要获取应用的 SharedPreferences 对象，请使用以下两个方法之一：
getSharedPreferences() - 如果您需要多个按名称（使用第一个参数指定）识别的首选项文件，请使用此方法。
getPreferences() - 如果您只需要一个用于 Activity 的首选项文件，请使用此方法。 由于这将是用于 Activity 的唯一首选项文件，因此无需提供名称。

要写入值：
1. 调用 edit() 以获取 SharedPreferences.Editor。      
2. 使用 putBoolean() 和 putString() 等方法添加值。    
3. 使用 commit() 提交新值

### 3.2 Internal Storage(内部存储)  
使用文件名称和操作模式调用 openFileOutput()。 这将返回一个 FileOutputStream。
使用 write() 写入到文件。
使用 close() 关闭流式传输。

```
getDir : /data/user/0/com.qwm.androidreview01/app_test1
getCacheDir : /data/user/0/com.qwm.androidreview01/cache
getDataDir : /data/user/0/com.qwm.androidreview01
getFilesDir : /data/user/0/com.qwm.androidreview01/files
getCodeCacheDir : /data/user/0/com.qwm.androidreview01/code_cache
getNoBackupFilesDir : /data/user/0/com.qwm.androidreview01/no_backup
```

```
getFilesDir()
获取在其中存储内部文件的文件系统目录的绝对路径。
getDir()
在您的内部存储空间内创建（或打开现有的）目录。
deleteFile()
删除保存在内部存储的文件。
fileList()
返回您的应用当前保存的一系列文件。
```

### 3.3 External Storage(外部存储)  


### 3.4 SQLite Databases(sqlite数据库)

创建一个类继承 **SQLiteOpenHelper**

查询所有的表名
```
select name from sqlite_master where type='table' order by name
```


### 3.5 Network Connection(网络)   
 
