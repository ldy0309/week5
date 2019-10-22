package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String TAG = "Mylist";
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        ListView listView = findViewById(R.id.mylist);
        final List<String> data = new ArrayList<String>();
        for(int i=0;i<100;i++){
            data.add("item" + i);
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.nodata));
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> listv, View view, int position, long id) {

        Log.i(TAG, "onItemClick: position" + position);
        Log.i(TAG, "onItemClick: parent" + listv);
        adapter.remove(listv.getItemAtPosition(position));
        //adapter.notifyDataSetChanged();
    }
}
