package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void btn1(View v){
        show(1);
    }

    public void btn2(View v){
        show(2);
    }

    public void btn3(View v){
        show(3);
    }

    public void btnReset(View v){
        TextView out = (TextView)findViewById(R.id.txt2);
        out.setText("0");
    }

    public void show(int i){
        TextView out = (TextView)findViewById(R.id.txt2);
        String oldScore = (String) out.getText();
        String newScore = String.valueOf(Integer.parseInt(oldScore)+i);
        out.setText(newScore);
    }
}