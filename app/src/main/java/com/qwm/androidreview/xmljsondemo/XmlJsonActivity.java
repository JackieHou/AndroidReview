package com.qwm.androidreview.xmljsondemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qwm.androidreview.R;

import junit.framework.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiwenming
 * @date 2016/4/18 20:41
 * @ClassName: XmlJsonActivity
 * @Description: XML JSON操作
 */
public class XmlJsonActivity extends AppCompatActivity {

    private String TAG = XmlJsonActivity.class.getName();

    private List<TestArrayBean> testArrayBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_json);

        //初始化数据
        for (int i = 1; i < 4; i++) {
            List<TestBean> testBeanList = new ArrayList<>();
            for (int j = 1; j < 3; j++) {
                int age = i*10+j;
                String phone = "130266297"+i+j;
                String name = "xm_"+i+"_"+j;
                TestBean testBean = new TestBean(age,phone,name);
                testBeanList.add(testBean);
            }
            TestArrayBean arrayBean = new TestArrayBean(i+"",testBeanList);
            testArrayBeanList.add(arrayBean);
        }

    }


    //---------------------------xml--------------------------------

    /**
     * 写xml
     * @param view
     */
    public void writeXML(View view) {
        File file = new File(Environment.getExternalStorageDirectory(),"xmltest.xml");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            XmlSerializer xml = Xml.newSerializer();
            xml.setOutput(fos,"UTF-8");
            //文档开始
            xml.startDocument("UTF-8",true);
            for(TestArrayBean arrayBean : testArrayBeanList){
                //创建节点
                xml.startTag(null,"arraybeans");
                xml.startTag(null,"nid");
                xml.text(arrayBean.getNid());
                xml.endTag(null,"nid");
                xml.startTag(null,"persons");
                for (TestBean testBean: arrayBean.getPersons()) {
                    xml.startTag(null,"testbean");

                    xml.startTag(null,"name");
                    xml.text(testBean.getName());
                    xml.endTag(null,"name");

                    xml.startTag(null,"phoneNum");
                    xml.text(testBean.getPhoneNum());
                    xml.endTag(null,"phoneNum");

                    xml.startTag(null,"age");
                    xml.text(testBean.getAge()+"");
                    xml.endTag(null,"age");

                    xml.endTag(null,"testbean");
                }
                xml.endTag(null,"persons");
                xml.endTag(null,"arraybeans");
            }
            fos.flush();
            xml.endDocument();
            fos.close();
            Toast.makeText(XmlJsonActivity.this, "xml写入完成了", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "writeXML: "+file.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取xml  pull解析
     * @param view
     */
    public void readXML(View view) {
        List<TestArrayBean> aBeanList = new ArrayList<>();
        List<TestBean> persons = null;
        TestBean person = null;
        TestArrayBean arrayBean = null;
       XmlPullParser xmlPull =  Xml.newPullParser();
        File file = new File(Environment.getExternalStorageDirectory(),"xmltest.xml");
        try {
            FileInputStream fis = new FileInputStream(file);
            xmlPull.setInput(fis,"UTF-8");
            int eventType = xmlPull.getEventType();
            while(eventType!= XmlPullParser.END_DOCUMENT){//不是文档结束，那么继续
                switch (eventType){
                    case XmlPullParser.START_TAG://标签结束
                        if("arraybeans".equals(xmlPull.getName())){
                            arrayBean = new TestArrayBean();
                        }else  if("nid".equals(xmlPull.getName())){
                            arrayBean.setNid(xmlPull.nextText());
                        }else  if("persons".equals(xmlPull.getName())){
                            persons = new ArrayList<>();
                        }else  if("testbean".equals(xmlPull.getName())){
                            person = new TestBean();
                        }else  if("age".equals(xmlPull.getName())){
                            person.setAge(Integer.parseInt(xmlPull.nextText()));
                        }else  if("phoneNum".equals(xmlPull.getName())){
                            person.setPhoneNum(xmlPull.nextText());
                        }else  if("name".equals(xmlPull.getName())){
                            person.setName(xmlPull.nextText());
                        }
                      break;
                    case XmlPullParser.END_TAG://标签开始
                        if("arraybeans".equals(xmlPull.getName())){
                            aBeanList.add(arrayBean);
                        }else  if("persons".equals(xmlPull.getName())){
                            arrayBean.setPersons(persons);
                        }else  if("testbean".equals(xmlPull.getName())){
                            persons.add(person);
                        }
                        break;
                }
                //下一个
                eventType = xmlPull.next();
            }
            for (TestArrayBean abean: aBeanList) {
                Log.i(TAG, "readXML: "+abean.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //---------------------------Gson--------------------------------

    private String jsonStr = "";
    private TestArrayBean jsonTab;

    /**
     * 写json
     * @param view
     */
    public void writeGson(View view) {

        getTab("Gson");

        Gson gson = new Gson();
        jsonStr = gson.toJson(jsonTab);

        Log.i(TAG, "writeGson: "+jsonStr);
    }


    /**
     * 读json
     * @param view
     */
    public void readGson(View view) {

        if(!jsonStr.equals("")){

            Gson gson  = new Gson();
            TestArrayBean abean = gson.fromJson(jsonStr,TestArrayBean.class);

            Log.i(TAG, "readXML: "+abean.toString());
        }
    }

    //---------------------------fastjson--------------------------------

    /**
     *写 fastjson
     * @param view
     */
    public void writeFastjson(View view) {
        getTab("Fast");

        jsonStr = JSON.toJSONString(jsonTab);

        Log.i(TAG, "writeFastjson: "+jsonStr);
    }

    /**
     * 读 fastjson
     * @param view
     */
    public void readFastjson(View view) {
        if(!jsonStr.equals("")){
            TestArrayBean abean  = JSON.parseObject(jsonStr,TestArrayBean.class);
            Log.i(TAG, "readXML: "+abean.toString());
        }
    }


    public TestArrayBean getTab(String jName){
        //初始化数据
        List<TestBean> testBeanList = new ArrayList<>();
        for (int j = 1; j < 3; j++) {
            int age = j;
            String phone = "1302662978"+j;
            String name = jName+"_"+j;
            TestBean testBean = new TestBean(age,phone,name);
            testBeanList.add(testBean);
        }
        jsonTab = new TestArrayBean(jName+"100",testBeanList);
        return jsonTab;
    }

}
