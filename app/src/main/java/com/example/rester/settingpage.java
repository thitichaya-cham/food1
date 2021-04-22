package com.example.rester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class settingpage extends AppCompatActivity implements  View.OnClickListener {


    String name, name2, name3;

    ImageView databtn1, databtn2, databtn3, databtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingpage);


        Bundle b = getIntent().getExtras();
        name = b.getString("A");


        databtn1 = (ImageView) findViewById(R.id.btnnext1);
        databtn2 = (ImageView) findViewById(R.id.btnnext2);
        databtn3 = (ImageView) findViewById(R.id.btnnext3);
        databtn4 = (ImageView) findViewById(R.id.btnnext4);

        databtn1.setOnClickListener(this);
        databtn2.setOnClickListener(this);
        databtn3.setOnClickListener(this);
        databtn4.setOnClickListener(this);


    }


    public void onClick(View view) {
        if (view == databtn1) {

            Intent intent = new Intent(settingpage.this, home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn2) {

            Intent intent = new Intent(settingpage.this, settingpage.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn3) {

            Intent intent = new Intent(settingpage.this, bookmark.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn4) {


            Intent intent = new Intent(settingpage.this, profile.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed() {

    }
}