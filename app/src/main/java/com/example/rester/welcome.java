package com.example.rester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class welcome extends AppCompatActivity {

    String name, name2, name3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Bundle b = getIntent().getExtras();
        name = b.getString("A");


        new Thread(new Runnable() {
            public void run() {
                try  {
                    Thread.sleep(1000);
                }catch (InterruptedException e){}

                Intent intent = new Intent(welcome.this , home.class);
                intent.putExtra("A", name);
                startActivity(intent);
                finish();

            }
        }).start();


    }
}