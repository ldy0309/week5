package com.example.week5;

import androidx.annotation.NonNull;
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String  scorea = ((TextView)findViewById(R.id.txt2)).getText().toString();
        String  scoreb = ((TextView)findViewById(R.id.textView2)).getText().toString();
        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scorea = savedInstanceState.getString("teama_score");
        String scoreb = savedInstanceState.getString("teamb_score");

        ((TextView)findViewById(R.id.txt2)).setText(scorea);
        ((TextView)findViewById(R.id.textView2)).setText(scoreb);
    }

    public void btn1(View v){
        show1(1);
    }

    public void btn2(View v){
        show1(2);
    }

    public void btn3(View v){
        show1(3);
    }

    public void btn12(View v){
        show2(1);
    }

    public void btn22(View v){
        show2(2);
    }

    public void btn32(View v){
        show2(3);
    }




    public void btnReset(View v){
        TextView out = (TextView)findViewById(R.id.txt2);
        TextView out2 = (TextView)findViewById(R.id.textView2);
        out.setText("0");
        out2.setText("0");
    }

    public void show1(int i){
        TextView out = (TextView)findViewById(R.id.txt2);
        String oldScore = (String) out.getText();
        String newScore = String.valueOf(Integer.parseInt(oldScore)+i);
        out.setText(newScore);
    }

    public void show2(int i){
        TextView out = (TextView)findViewById(R.id.textView2);
        String oldScore = (String) out.getText();
        String newScore = String.valueOf(Integer.parseInt(oldScore)+i);
        out.setText(newScore);
    }
}
