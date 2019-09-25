package com.example.week5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        }

        float dollar_rate = 0.1445f;
        float euro_rate = 0.1227f;
        float won_rate = 168.0538f;

        public void btndollar(View v) {
            transfer(dollar_rate);
        }

        public void btneuro(View v){
            transfer(euro_rate);
        }

        public void btnwon(View v){
            transfer(won_rate);

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

            bdl.putFloat("dollar_rate",dollar_rate);
            bdl.putFloat("euro_rate",euro_rate);
            bdl.putFloat("won_rate",won_rate);

            config.putExtras(bdl);

            //config.putExtra("dollar_rate",dollar_rate);
            //config.putExtra("euro_rate",euro_rate);
            //config.putExtra("won_rate",won_rate);


            startActivityForResult(config,1);
        }

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
              if (requestCode == 1 && resultCode == 2) {
                Bundle bundle = data.getExtras();
                dollar_rate = bundle.getFloat("key_dollar", 0.1f);
                euro_rate = bundle.getFloat("key_euro", 0.1f);
                won_rate = bundle.getFloat("key_won", 0.1f);
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

               bdl.putFloat("dollar_rate",dollar_rate);
               bdl.putFloat("euro_rate",euro_rate);
               bdl.putFloat("won_rate",won_rate);

               config.putExtras(bdl);

               startActivityForResult(config,1);

           }



            return super.onOptionsItemSelected(item);
    }
}
