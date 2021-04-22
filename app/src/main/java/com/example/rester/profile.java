package com.example.rester;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class profile extends AppCompatActivity implements  View.OnClickListener {

    String d1 = "";
    String d2 = "";
    String d3 = "";
    String d4 = "";
    String d5 = "";
    String d6 = "";
    String d7 = "";
    String d8 = "";
    String name, name2, name3;
    ImageView databtn1, databtn2, databtn3, databtn4;
    private static final String readdata1 = "https://project-alltest.com/appfood/readdata1.php"; /// โหลดชื่อผู้ใช้
    private TextView bm1, bm2, bm3, bm4, bm5, bm6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Bundle b = getIntent().getExtras();
        name = b.getString("A");

        bm1 = (TextView) findViewById(R.id.no1);
        bm2 = (TextView) findViewById(R.id.no2);
        bm3 = (TextView) findViewById(R.id.no3);
        bm4 = (TextView) findViewById(R.id.no4);

        String urlSuffix = "?txtKeyword="+name;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(profile.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                int A = s.length();

                String strJSON = s;
                try {
                    JSONArray data = new JSONArray(strJSON);

                    final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
                    HashMap<String, String> map;

                    for(int i = 0; i < data.length(); i++){
                        JSONObject c = data.getJSONObject(i);
                        int Row = i+1;
                        map = new HashMap<String, String>();
                        d1 =  c.getString("name");
                        d2 =  c.getString("name2");
                        d3 =  c.getString("telphone");
                        d4 =  c.getString("email");
                        MyArrList.add(map);

                    }

                    bm1.setText("  ชื่อ : " + d1);
                    bm2.setText("  นามสกุล : " +d2);
                    bm3.setText("  Email : " +d4);
                    bm4.setText("  เบอร์ : " +d3);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(readdata1+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);


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

            Intent intent = new Intent(profile.this, home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn2) {

            Intent intent = new Intent(profile.this, settingpage.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn3) {

            Intent intent = new Intent(profile.this, bookmark.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn4) {


            Intent intent = new Intent(profile.this, profile.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();
        }
    }


    public void onBackPressed() {

    }


}