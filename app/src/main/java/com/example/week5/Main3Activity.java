package com.example.week5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main3Activity extends AppCompatActivity implements Runnable {

    private float dollarRate = 0.0f;
    private float euroRate = 0.0f;
    private float wonRate = 0.0f;
    private final String TAG = "Rate";

    TextView txt5;
    EditText txt4;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt4 = (EditText)findViewById(R.id.txt4);
        txt5 = (TextView)findViewById(R.id.txt5);

        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dollarRate = sharedPreferences.getFloat("dollar_rate", 0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate", 0.0f);
        wonRate = sharedPreferences.getFloat("won_rate", 0.0f);

        Log.i(TAG,"onCreate: sp dollarRate="+dollarRate);
        Log.i(TAG,"onCreate: sp euroRate="+euroRate);
        Log.i(TAG,"onCreate: sp wonRate="+wonRate);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==5){
                    String str = (String)msg.obj;
                    Log.i(TAG,"handleMessage:getMessage msg="+str);
                    txt5.setText(str);

                }


                super.handleMessage(msg);
            }
        };


    }


    public void btndollar(View v) {

        transfer(dollarRate);
    }

    public void btneuro(View v) {

        transfer(euroRate);
    }

    public void btnwon(View v) {
        transfer(wonRate);

    }

    public void transfer(float i) {
        EditText input = findViewById(R.id.txt4);
        String str = input.getText().toString();
        float s = Float.parseFloat(str);
        float r = s * i;

        TextView show = findViewById(R.id.txt5);
        show.setText(String.format("%.2f", r));

    }

    public void btncon(View v) {
        Intent config = new Intent(this, Main4Activity.class);
        Bundle bdl = new Bundle();

        bdl.putFloat("dollar_rate", dollarRate);
        bdl.putFloat("euro_rate", euroRate);
        bdl.putFloat("won_rate", wonRate);

        config.putExtras(bdl);

        //config.putExtra("dollar_rate",dollar_rate);
        //config.putExtra("euro_rate",euro_rate);
        //config.putExtra("won_rate",won_rate);

        Log.i(TAG,"openOne:dollarRate="+dollarRate);
        Log.i(TAG,"openOne:euroRate="+euroRate);
        Log.i(TAG,"openOne:wonRate="+wonRate);


        startActivityForResult(config, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("key_dollar", 0.1f);
            euroRate = bundle.getFloat("key_euro", 0.1f);
            wonRate = bundle.getFloat("key_won", 0.1f);

            Log.i(TAG,"onActivityResult:dollarRate="+dollarRate);
            Log.i(TAG,"onActivityResult:euroRate="+euroRate);
            Log.i(TAG,"onActivityResult:wonRate="+wonRate);

            SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("dollar_rate", dollarRate);
            editor.putFloat("euro_rate", euroRate);
            editor.putFloat("won_rate", wonRate);
            editor.apply();
            Log.i(TAG,"onActivityResult: 数据已保存到sharedPreferences");
        }
        super.onActivityResult(1, 2, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuabc, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_set) {
            Intent config = new Intent(this, Main4Activity.class);
            Bundle bdl = new Bundle();

            bdl.putFloat("dollar_rate", dollarRate);
            bdl.putFloat("euro_rate", euroRate);
            bdl.putFloat("won_rate", wonRate);

            config.putExtras(bdl);

            startActivityForResult(config, 1);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {

        Log.i(TAG,"run:run()");
        for(int i=1;i<3;i++){
            try{
                Thread.sleep(2000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Message msg = handler.obtainMessage(5);
        //msg.what=5;
        msg.obj = "Hello from run()";
        handler.sendMessage(msg);

        URL url = null;
        try{
            url = new URL("http://www.usd-cny.com/icbc.htm");
            HttpURLConnection http =(HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html =inputStream2String(in);
            Log.i(TAG,"run:html"+html);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private String inputStream2String(InputStream inputStream) throws IOException{
          final int bufferSize = 1024;
          final char[] buffer = new char[bufferSize];
          final StringBuilder out = new StringBuilder();
          Reader in = new InputStreamReader(inputStream,"gb2312");
          while(true){
              int rsz = in.read(buffer,0,buffer.length);
              if(rsz<0)
                  break;
              out.append(buffer,0,rsz);

          }
          return  out.toString();
    }
}