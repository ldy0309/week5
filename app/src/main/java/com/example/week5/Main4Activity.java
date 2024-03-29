package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    EditText input, input2, input3;
    float dollarRate, euroRate, wonRate;
    private final String TAG = "ConfigAcaptivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent config = getIntent();
        Bundle bdl;

        input = findViewById(R.id.txtdollar);
        input2 = findViewById(R.id.txteuro);
        input3 = findViewById(R.id.txtwon);

        bdl = config.getExtras();
        if (bdl != null) {
            dollarRate = bdl.getFloat("dollar_rate");
            euroRate = bdl.getFloat("euro_rate");
            wonRate = bdl.getFloat("won_rate");

            Log.i(TAG,"onCreate:dollar2="+dollarRate);
            Log.i(TAG,"onCreate:euro2="+euroRate);
            Log.i(TAG,"onCreate:won2="+wonRate);


            input.setText(String.valueOf(dollarRate));

            input2.setText(String.valueOf(euroRate));

            input3.setText(String.valueOf(wonRate));


        }
    }


        public void btnsave (View v){

            String str = input.getText().toString();
            dollarRate = Float.parseFloat(str);

            String str2 = input2.getText().toString();
            euroRate = Float.parseFloat(str2);


            String str3 = input3.getText().toString();
            wonRate = Float.parseFloat(str3);

            Log.i(TAG,"save:获取到的新的值");
            Log.i(TAG,"save:newdollar="+dollarRate);
            Log.i(TAG,"save:newEuro="+euroRate);
            Log.i(TAG,"save:newwon="+wonRate);

            Intent intent = getIntent();
            Bundle bdl = new Bundle();
            bdl.putFloat("key_dollar", dollarRate);
            bdl.putFloat("key_euro", euroRate);
            bdl.putFloat("key_won", wonRate);
            intent.putExtras(bdl);


            setResult(2, intent);

            finish();

        }
    }

