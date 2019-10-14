package com.example.week5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyList2Activity extends ListActivity implements Runnable,AdapterView.OnItemClickListener{

        private String TAG = "mylist2";
        Handler handler;
        private ArrayList<HashMap<String,String>> listItems;//存放文字、图片信息
        private SimpleAdapter listItemAdapter;//适配器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();

        //MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,listItems);
        //this.setListAdapter(myAdapter);
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==7){
                    List<HashMap<String,String>> list2 = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(MyList2Activity.this,list2,//listItems数据源
                            R.layout.list_item,//listItem的xml布局实现
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail}
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };

        /*getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/
        getListView().setOnItemClickListener(this);

    }

    private void initListView(){
        listItems = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < 10; i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate: " + i);//标题文字
            map.put("ItemDetail","detail" + i);//详情描述
            listItems.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this,listItems,//listItems数据源
                R.layout.list_item,//listItem的xml布局实现
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
        );
    }

    @Override
    public void run() {
        List<HashMap<String,String>> retlist = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            Thread.sleep(3000);
            doc = Jsoup.connect("http://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG, "run: " + doc.title());
            Elements tables = doc.getElementsByTag("table");
            /*int i = 1;
            for(Element table : tables ){
                Log.i(TAG,"run: table["+i+"] = " + table);
                i++;

            }*/

            Element table2 = tables.get(1);
            //Log.i(TAG,"run: table1=" + table1);

            //获取td中的数据
            Elements tds = table2.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=8){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);

                String str1 = td1.text();
                String val = td2.text();

                Log.i(TAG,"run: text=" + str1 + "==>" + val);
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemTitle",str1);
                map.put("ItemDetail",val);
                retlist.add(map);


            }

            /*for(Element td : tds){
                Log.i(TAG,"run: td=" + td);
                Log.i(TAG,"run : text=" + td.text());
                Log.i(TAG,"run : html=" + td.html());

            }*/


        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Message msg = handler.obtainMessage(7);
        msg.obj = retlist;
        handler.sendMessage(msg);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Log.i(TAG,"onItemClick：parent=" + adapterView);
        Log.i(TAG,"onItemClick：view=" + view);
        Log.i(TAG,"onItemClick：position=" + position);
        Log.i(TAG,"onItemClick：id=" + id);
        HashMap<String,String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");
        Log.i(TAG, "onItemClick: titleStr=" + titleStr);
        Log.i(TAG, "onItemClick: detailStr=" + detailStr);

        TextView title = view.findViewById(R.id.itemTitle);
        TextView detail = view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());
        Log.i(TAG, "onItemClick: title2=" + title2);
        Log.i(TAG, "onItemClick: detail2=" + detail2);

        //打开新的页面，传入参数
        Intent rateCalc = new Intent(this,RateCalcActivity.class);
        rateCalc.putExtra("title",titleStr);
        rateCalc.putExtra("rate",Float.parseFloat(detailStr));
        startActivity(rateCalc);


    }
}

