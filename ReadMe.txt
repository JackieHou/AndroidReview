Android 进程和线程
  --进程是被系统创建的，但内存不足的时候，又会被系统回收。
  --进程的级别
    --Foreground process
    --Visible process
    --Service process
    --Background process
    --Empty process

  --进程回收
    -- 5---1
    -- 先回收一个，够了就不再回收
    -- 特殊回收 3 2 1三种进程
       内存不够，回收三种进程，内存够了，会自动重启
       如果用户手动关了服务，服务不会再重启


Android 四大主件
  --Activty
  --BroadcastReceiver
  --ContentProvider
  --Service

  --Activity
   --启动模式
       --Standard
       --SingleTop
       --SingleTask
       --SingleInstance
   --生命周期
       -- OnCreate  OnStart  OnResume  OnPause  OnStop  OnDestory  OnReStart
   --能够传递的类型
       --8种基本数据类型（byte short int long  float double boolean char ）
       --List<String>
       --Bundle
       --Serilizable
  --BroadcastReceiver
       --广播的分类
         --普通广播 不可中断、不能相互传递数据
         --有序广播 abortBroadcast
       --广播的定义
         --定义类继承BroadcastReceiver 重写 onReceive
         --清单文件或者代码中注册广播
         --有序广播接收顺序可以使用 priority来设置，默认0，值越大优先级越高
       --注意
          如果要在广播接收者中打开Activity，需要设置一下Intent.FLAG_ACTIVITY_NEW_TASK，因为广播接收者是没有Activity任务栈的
  --ContentProvider
        --数据对外共享 数据访问同一  只暴露我们希望提供的数据   数据更改监听
        --创建内容提供者
          --定义类继承 ContentProvider
          --清单文件配置(name  authorities)
        --短信的内容提供者 content://sms
  --Service
        --后台运行
        --startService():  onCreate() onStartCommand()       onDestory()
        --bindService() :  onCreate() onBind()   onUnbind()  onDestory()
        --AIDL
          --IPC(inter process communication)
          --原理：AIDL （Android Interface Definition Language）
          --步骤：
             --创建.aidl文件，声明方法
             --创建Service内部类，继承自 Stub
             --返回上面的对象
             --客户端导入上面的.aidl
             --使用隐式意图
              Intent intent = new Intent("com.qwm.aidl");

Handler
  原理：
     Looper.prepare()
     new Handler()
        -- Looper
        ---MessageQueue
     Looper.loop();


    ---sendMessage(msg)
    --->sendMessageDelayed(msg,0)
    --->sendMessageAtTime(msg,uptimeMills)
    --->enqueueMessage(queue,msg,uptimeMills)  msg.target = this;
    --->queue.enqueueMessage(msg,uptimeMills)  排序

    ---loop()
    --->dispatchMessage(msg)    msg.target.dispatchMessage;
    --->handlerMessage(msg)
AsyncTask
    AsyncTask()
       mWorker
          call()  ----- doInBackground()
      mFuture
          done()  ------->handler---> onPostExecute()

    ----execute(params...);
    ---->prepare()
    ---->exec.execute(mFuture); //执行任务

ListView优化
   复用coverView
       ViewHolder
   分页加载
       int position = contentLv.getLastVisiblePosition();
   复杂的item处理

   图片优化
   其他优化
      BaseAdapter尽量避免使用 static
      尽量使用getApplicationContext()
      尽量避免在Adapter总使用线程

引用的了解
   android
        Intent.ACTION_DEVICE_STORAGE_LOW;
        设备内存不足时发出的广播,此广播只能由系统使用，其它APP不可用；
        Intent.ACTION_DEVICE_STORAGE_OK;
        设备内存从不足到充足时发出的广播,此广播只能由系统使用，其它APP不可用；
   对象的引用级别（jdk1.2开始）
      强引用、软引用、弱引用、虚引用
        --强引用(StrongReference)
            Object object = new Object();
            不会回收：
        --软引用（SoftReference）
           Object object = new Object();
           SoftReference sr = new SoftReference(object);
           内存不足的时候回收，回收搜 get()=null
           get()获取强引用
        --弱引用（WeakReference）
           生命周期更短
           垃圾回收器一旦发现弱引用，就回收（不论内存够不够）
        --虚引用（PhantomReference）
           形同虚设
           任何时候都可被回收
           特点：
               a.形同虚设；
               b.可用来跟踪对象被垃圾回收器回收的活动；
               c.虚引用与软引用和弱引用的一个区别在于：
               虚引用必须和引用队列 （ReferenceQueue）联合使用，
               当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中；
        http://sishuok.com/forum/blogPost/list/342.html

图片缓存
     压缩图片   BitmapFactory.Option()    options.inJustDecodeBounds = true;
     图片缓存技术
         LruCache
               (LRU  Least Recently Used 近期最少使用) v4
               最近使用的对象强引用村存储在 LinkedHashMap中，最近很少使用达到峰值的对象移除LinkedHashMap
               LruCache原理
                    trimToSize(maxSize) 不断的计算，直到存储的大小小于总的大小
         三级缓存
              内存  本地缓存  服务器   304(未修改过)

OOM异常的处理
     产生的原因
           1.资源释放问题
           2.对象内存过大问题
           3.static
              解决方法：
                  1.static尽量不要修饰资源耗费过多的示例
                  2.Context尽量使用ApplicationContext
                  3.WeakReference代替强引用
           4.线程导致内存异常

     避免OOM的方案
          1.图片过大解决方案
               1.图片压缩并且及时回收（bitmap.recycle()）
               2.软引用并且及时回收
          2.复用ListView
          3.界面切换
              单个界面横竖屏N
                   1.去除xml中设置的背景，改为程序中设置
                   2.xml加载到一个容器中，使用的时候直接调用，避免重复加载
               少重复使用一些代码
      常见的内存使用不当的情况
          1.查询数据库的时候，没有关闭游标
          2.构造Adapter的时候，没有使用缓存的convertView
          3.Bitmap对象不再使用时，没有调用recycle()释放内存
          4.对象的引用没有释放
          5.其他

      Android性能优化的一些方案
          1.敏感计算方面用NDK完成
          2.图形优化(recycle())
          3.gif的处理
       图片占用进程的内存算法简介
          width * height * config
       内存监测工具 DDMS --> Heap

动画
   帧动画
      所有的动画组合起来
   补间动画
      位移、旋转、缩放、透明度
   属性动画 3.0
       blog
         Android属性动画完全解析(上)，初识属性动画的基本用法                     http://blog.csdn.net/guolin_blog/article/details/43536355
         Android属性动画完全解析(中)，ValueAnimator和ObjectAnimator的高级用法
         Android属性动画完全解析(下)，Interpolator和ViewPropertyAnimator的用法
       ValueAnimator
       ObjectAnimator

Shape的使用
   corners ---------圆角
   size    ---------大小
   padding ---------内边距
   solid   ---------填充
   stroke  ---------描边
   gradient---------渐变

屏幕适配
   横竖屏
         onConfigurationChanged
         3.2以前
          android:configChanges="orientation|keyboardHidden"
         3.2以后screenSize
          android:configChanges="keyboardHidden|orientation|screenSize"
          或者
          android:configChanges="orientation|screenSize"
   分辨率的问题
      使用layout_weight
      清单文件配置（不同屏幕写不同的布局）
      其他（使用dp等）
   px和dp的关系
      px = dp*PPI/160
      dp = px / (PPI/ 160)

qq三方登陆
   授权--->code-->token-->openid-->信息

Android底层
   1. Linux kernel
   2. Libraries   Application Runtime (Core Libraries ,Dalvik Virtrul Machine)
   3. Application Framework
   4. Application

   Linux kernel
      Linux内核与驱动
   Libraries
      动态库、android运行库、dvm   -------> c c++
   Application Framework
      java
   Application
      java

   Dalvik VM与JVM（java Virtual Machine）
     JVM：  .java--> 编译为 .class -->打包为 .jar
     DVM:   .java--> 编译为 .class -->打包为 .dex --->打包为 .apk （通过dx工具）

   Davik VM 与 ART VM
     Davik 实时编译
     ART  安装时编译
    http://blog.csdn.net/zengmingen/article/details/49513505


    JVM  .java  .class  .jar
    DVM  .java  .class  .dex  .apk


   系统启动过程
        Linux init
        init.rc (配置，启动守护进程)

        1. app_main.cpp
           set_process_name("zygote");
           runtime.start("com.android.internal.os.ZygoteInit",startSystemServer);

        2. AndroidRuntime.start
            startVM
            startReg---注册JNI
            ZygoteInitl.main

        3. ZygoteInit.main
             registerZygoteSocket();   创建socket用于和ActivityManagerService通信
             startSystemServer();  启动SystemeServer组件
             runSelectLoopMode   进入无限循环 等待ActivityManagerService请求创建应用进程

        4. ZygoteInit.registerZygoteSocket()
             zygote   根据文件描述符


    应用启动过程
         PackageManagerService  读取所有的mainfest，添加到信息库中
              1) system/app  system/framework  data/app data/app-private

              2)权限分配

              3)保存数据
      	         将每个apk的信息保存到packages.xm和packages.list中
         Launcher
            1)通过Binder进入到ActivityManagerService进程 ---调用--->ActivityManagerService.startActivity

      	    2)ActivityManagerService调用ActivityStack.startActivityMayWait准备启动的activity信息

      	    3)ActivityStack通知ApplicationThread要进行Activity启动调度了（ApplicationThread代表的是调用ActivityManagerService.startActivity接口的进程，点击应用图标那么就是Launcher,内部启动那么是就是Activiy所在的进程了）

      	    4)ApplicationThread不执行真的启动操作，而是通过ActivityManagerService.activityPaused接口进入到 ActivityManagerService进程中，看看是否需要创建新的进程来启动Activity

      	    5)对于点击应用图标的情况，ActivityManagerService在这步中，会调用startProcessLocked创建新进程

      	    6)ActivityManagerService调用ApplicationThread.sheduleLaunchActivity接口，通知相应的进程来执行启动Activity的操作

      	    7)ApplicationThead把这个启动Activity的操作转发给ActivityThread，ActivityThread通过ClassLoader导入相应的Activity类，然后把它启动起来


    简述如何将Activity展现在手机上
      6.0以前
    	  1.在Activity创建是调用attach方法（在onCreate方法前面调用）

    	  2.在attach方法中，会调用PolicManager.makeNewWindow()创建PhoneWindow
    	    mWindow = PolicyManager.makeNewWindow(this);
    		 makeNewWindowd调用的是第3步骤中的方法

    	  3.在IPolicy的实现类中创建了PhoneWindow
    	     sPolicy.makeNewWindow(context)

    	  4.在PhoneWindow中setContentView的方法中,添加显示内容
    		public void setContentView(int layoutResID) {
    			if (mContentParent == null) {
    				installDecor();
    			} else {
    				mContentParent.removeAllViews();
    			}
    			mLayoutInflater.inflate(layoutResID, mContentParent);
    			final Callback cb = getCallback();
    			if (cb != null && !isDestroyed()) {
    				cb.onContentChanged();
    			}
    		}
    		PhoneWindow继承自Window
    		setContentView中首先判断 mContentParent是否为null,
    		   为null,调用 installDecor() 创建DecorView
    		   不为null,删除所有的控件
      6.0以后
        1.在Activity创建是调用attach方法（在onCreate方法前面调用）

    	2.在attach方法中创建PhoneWindow
    	   mWindow = PhoneWindow(this);
    	3. 在PhoneWindow中setContentView的方法中,添加显示内容
    	        @Override
    			public void setContentView(int layoutResID) {
    				// Note: FEATURE_CONTENT_TRANSITIONS may be set in the process of installing the window
    				// decor, when theme attributes and the like are crystalized. Do not check the feature
    				// before this happens.
    				if (mContentParent == null) {
    					installDecor();
    				} else if (!hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
    					mContentParent.removeAllViews();
    				}

    				if (hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
    					final Scene newScene = Scene.getSceneForLayout(mContentParent, layoutResID,
    							getContext());
    					transitionTo(newScene);
    				} else {
    					mLayoutInflater.inflate(layoutResID, mContentParent);
    				}
    				mContentParent.requestApplyInsets();
    				final Callback cb = getCallback();
    				if (cb != null && !isDestroyed()) {
    					cb.onContentChanged();
    				}
    			}

        Activity、Window和View三者间的关系
    	  Activity通过attach方法创建Window
    	  Window通过setContentView来添加View



    自定义View
        Android LayoutInflater原理分析，带你一步步深入了解View(一)   http://blog.csdn.net/guolin_blog/article/details/12921889
        Android视图绘制流程完全解析，带你一步步深入了解View(二)   http://blog.csdn.net/guolin_blog/article/details/16330267

        LayoutInflater
          inflate
              解析xml
              迭代rInflate
                 调用createViewFromTag创建view

