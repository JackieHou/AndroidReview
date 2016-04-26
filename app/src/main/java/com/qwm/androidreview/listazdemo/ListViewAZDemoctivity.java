package com.qwm.androidreview.listazdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.qwm.androidreview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiwenming
 * @date 2016/4/21 16:48
 * @ClassName: ListViewAZDemoctivity
 * @Description:  TODO
 */
public class ListViewAZDemoctivity extends AppCompatActivity {

    private ListView listView;
    private Sidebar sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_azdemoctivity);
        listView = (ListView)findViewById(R.id.list);
        sidebar = (Sidebar)findViewById(R.id.sidebar);
        sidebar.setListView(listView);
        initData();
    }






    public void initData(){
        List<AZBean> list = new ArrayList<AZBean>();
        list.add(new AZBean("啊呀你是谁"));//a
        list.add(new AZBean("不要任我鬼火"));//b
        list.add(new AZBean("不要罗比所"));//b
        list.add(new AZBean("爸爸要亭湖"));//b
        list.add(new AZBean("巴莱巴拉"));//b
        list.add(new AZBean("Bollean"));//b
        list.add(new AZBean("股概念股"));//g
        list.add(new AZBean("红商还是地方和搜到烦的时候都是发货的师傅好ID号司法活动时候发货的沙发的护身符hi偶都是佛活动是"));//g
        list.add(new AZBean("哈哈恢复和萨芬hi都是符合当时安抚第三方hi偶都是佛挡杀佛很低收费的还是放的说法都是"));//g
        list.add(new AZBean("哈哈哈哈给和覅大厦覅都是佛挡杀佛收到"));//g
        list.add(new AZBean("靠什么"));//k
        list.add(new AZBean("灵魂空虚、物欲横流"));//l
        list.add(new AZBean("你知道呢个盘"));//n
        list.add(new AZBean("人们的精神堕入虚无主义，只能沉浸在金钱物质欲望和肉体感官刺激中"));//n
        list.add(new AZBean("死一边去"));//s
        list.add(new AZBean("我你大爷的"));//w
        list.add(new AZBean("沃你大爷的"));//w
        list.add(new AZBean("蜗牛是水呀"));//w
        list.add(new AZBean("哇一个"));//w
        list.add(new AZBean("有各种不安和痛苦。多少年轻人也失去了纯真的理想和纯美的爱情。"));//y
        list.add(new AZBean("这是极为特殊的历史转折期，物质文明发展到这一步注定了精神(信仰)的缺失"));//z

        listView.setAdapter(new AZAdapter(this,list));
    }
}
