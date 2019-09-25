package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    EditText input,input2,input3;
    float newDollar,newEuroi,newWon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent config = getIntent();
        Bundle bdl;

         input = findViewById(R.id.txtdollar);
         input2 = findViewById(R.id.txteuro);
         input3 = findViewById(R.id.txtwon);

         bdl=config.getExtras();
         if(bdl!=null){
             newDollar = bdl.getFloat("dollar_rate");
             newEuroi = bdl.getFloat("euro_rate");
             newWon = bdl.getFloat("won_rate");

             input.setText(String.valueOf(newDollar));
             input2.setText(String.valueOf(newEuroi));
             input3.setText(String.valueOf(newWon));
         }




    }




    public void btnsave(View v){

        String str = input.getText().toString();
        newDollar = Float.parseFloat(str);

        String str2 = input2.getText().toString();
        newEuroi= Float.parseFloat(str2);


        String str3 = input3.getText().toString();
        newWon= Float.parseFloat(str3);

        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("key_dollar",newDollar);
        bdl.putFloat("key_euro",newEuroi);
        bdl.putFloat("key_won",newWon);
        intent.putExtras(bdl);
        setResult(2,intent);

        finish();

    }
}
