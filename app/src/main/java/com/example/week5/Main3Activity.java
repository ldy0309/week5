package com.example.week5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private float dollarRate = 0.0f;
    private float euroRate =0.0f;
    private float wonRate =0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        SharedPreferences sharedPreferences = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate = sharedPreferences.getFloat("won_rate",0.0f);

    }


    public void btndollar(View v) {

            transfer(dollarRate);
        }

        public void btneuro(View v){

            transfer(euroRate);
        }

        public void btnwon(View v){
            transfer(wonRate);

        }

        public void transfer(float i) {
            EditText input = findViewById(R.id.txt4);
            String str = input.getText().toString();
            float s = Float.parseFloat(str);
            float r = s * i;

            TextView show = findViewById(R.id.txt5);
            show.setText(String.format("%.2f",r));

        }

        public void btncon(View v){
            Intent config = new Intent(this,Main4Activity.class);
            Bundle bdl=new Bundle();

            bdl.putFloat("dollar_rate",dollarRate);
            bdl.putFloat("euro_rate",euroRate);
            bdl.putFloat("won_rate",wonRate);

            config.putExtras(bdl);

            //config.putExtra("dollar_rate",dollar_rate);
            //config.putExtra("euro_rate",euro_rate);
            //config.putExtra("won_rate",won_rate);

            SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.apply();


            startActivityForResult(config,1);
        }

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
              if (requestCode == 1 && resultCode == 2) {
                Bundle bundle = data.getExtras();
                dollarRate = bundle.getFloat("key_dollar", 0.1f);
                euroRate = bundle.getFloat("key_euro", 0.1f);
                wonRate = bundle.getFloat("key_won", 0.1f);

                  SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putFloat("dollar_rate",dollarRate);
                  editor.putFloat("euro_rate",euroRate);
                  editor.putFloat("won_rate",wonRate);
                  editor.apply();
              }
              super.onActivityResult(1, 2, data);
         }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menuabc,menu);
            return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
           if(item.getItemId()==R.id.menu_set){
               Intent config = new Intent(this,Main4Activity.class);
               Bundle bdl=new Bundle();

               bdl.putFloat("dollar_rate",dollarRate);
               bdl.putFloat("euro_rate",euroRate);
               bdl.putFloat("won_rate",wonRate);

               config.putExtras(bdl);

               startActivityForResult(config,1);

           }



            return super.onOptionsItemSelected(item);
    }
}
