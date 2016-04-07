package com.qwm.androidreview.referencedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qwm.androidreview.R;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        testSoftReference();
        testWeakReference();
        testPhantomReference();
    }


//======================================引用测试=========================================
    /**
     * 虚引用
     */
    public void testPhantomReference(){
        String str = "testPhantomReference";
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(str,referenceQueue);
        System.out.println("------------testPhantomReference-----------------");
        System.out.println("phantomReference.get() \t"+phantomReference.get());
        System.out.println("referenceQueue.poll() \t"+referenceQueue.poll());
    }

    /**
     * 软引用
     */
    public void testWeakReference(){
        String str = "testWeakReference";
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference weakReference = new WeakReference(str,referenceQueue);
        str = null;
        //添加更多的回收可能
        System.gc();
        System.gc();
        System.out.println("---------testWeakReference----------");
        //获取强引用，如果被回收了，那么返回null
        System.out.println("weakReference.get() \t"+weakReference.get());
        //如果上面的软引用被回收了，就会加入到这个队列中
        System.out.println("referenceQueue.poll() \t"+referenceQueue.poll());
    }

    /**
     * 软引用
     */
    public void testSoftReference(){
        //1.创建强引用
        String str = "testSoftReference";
        //2.创建引用队列
        ReferenceQueue referenceQueue = new ReferenceQueue();
        //3.引用降级为 软引用(SoftReference) 只有内存不存的时候才回收
        SoftReference softRef = new SoftReference(str,referenceQueue);
        //调用一哈回收
        str = null;
        System.gc();
        System.gc();
        System.gc();
        System.out.println("---------testSoftReference----------");
        //获取强引用，如果被回收了，那么返回null
        System.out.println("softRef.get() \t"+softRef.get());
        //如果上面的软引用被回收了，就会加入到这个队列中
        System.out.println("referenceQueue.poll() \t"+referenceQueue.poll());
    }

    //===========================================java测试用例========================================
//
//    import java.lang.ref.*;
//    import java.util.HashSet;
//    import java.util.Set;
//    /*** *@author qiwenming
//     *@date 2016/4/6 10:34
//     *@classname Grocery
//     *@package
//     *@discription
//     */
//    public class ReferenceDemo {
//
//
//        private static ReferenceQueue rq = new ReferenceQueue();
//
//        public static void checkQueue() {
//            Reference inq = rq.poll();
//            // 从队列中取出一个引用
//            if (inq != null)
//                System.out.println("In queue: " + inq + " : " + inq.get());
//        }
//
//        public static void main(String[] args) {
//            final int size = 10;
//            System.out.println("-----------------------软引用----------------------------");
//            // 创建10个Grocery对象以及10个软引用
//            Set sa = new HashSet();
//            for (int i = 0; i < size; i++) {
//                SoftReference ref = new SoftReference(new Grocery("soft" + i), rq);
//                System.out.println("Just created soft: " + ref.get());
//                sa.add(ref);
//            }
//            System.gc();
//            checkQueue();
//
//            System.out.println("-----------------------弱引用----------------------------");
//            // 创建10个Grocery对象以及10个弱引用
//            Set wa = new HashSet();
//            for (int i = 0; i < size; i++) {
//                WeakReference ref = new WeakReference(new Grocery("weak " + i), rq);
//                System.out.println("Just created weak: " + ref.get());
//                wa.add(ref);
//            }
//            System.gc();
//            checkQueue();
//
//            System.out.println("--------------------------弱引用-------------------------");
//            // 创建10个Grocery对象以及10个弱引用
//            Set pa = new HashSet();
//            for (int i = 0; i < size; i++) {
//                PhantomReference ref = new PhantomReference(new Grocery("Phantom " + i), rq);
//                System.out.println("Just created Phantom: " + ref.get());
//                pa.add(ref);
//            }
//            System.gc();
//            checkQueue();
//        }
//
//
//        /*** *@author qiwenming
//         *@date 2016/4/6 10:34
//         *@classname Grocery
//         *@package
//         *@discription
//         */
//        public static class Grocery {
//
//            private static final int SIZE = 10000;
//            // 属性d使得每个Grocery对象占用较多内存，有80K左右
//            private double[] d = new double[SIZE];
//            private String id;
//
//            public Grocery(String id) {
//                this.id = id;
//            }
//
//            public String toString() {
//                return id;
//            }
//
//            public void finalize() {
//                System.out.println("Finalizing " + id);
//            }
//        }
//    }

}