package com.miwok.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView phrases,numbers,colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Miwok");

        phrases=findViewById(R.id.phrases_main);
        numbers=findViewById(R.id.Numbers_main);
        colors=findViewById(R.id.color_main);

        numbers.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this,Numbers.class)) ;
            }
        }));//its is onclick by which by clicking on no we get a page of ein
        colors.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,colour.class)) ;
            }
        }));//it is onclick by which by clicking on color we open rot or red vala page

        phrases.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Phrases.class)) ;
            }
        }));//it is on click by which we will open a page of what is your name








    }
}
