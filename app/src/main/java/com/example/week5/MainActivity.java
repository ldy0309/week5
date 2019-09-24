package com.example.week5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public  class MainActivity extends AppCompatActivity  {

    TextView out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc);
        out=findViewById(R.id.textout);

        EditText input=findViewById(R.id.inp);
        String str=input.getText().toString();

        Button btn = findViewById(R.id.btn);

    }

    public void btn (View v) {
        EditText input=findViewById(R.id.inp);
        String str=input.getText().toString();
        float s = Float.parseFloat(str);
        float r = s * 1.8f + 32;

        TextView show = findViewById(R.id.txt);
        show.setText("Result:" + String.format("%.2f",r));
        //show.setText(R.string.text3 + String.format("%.2f",r));
    }
}
