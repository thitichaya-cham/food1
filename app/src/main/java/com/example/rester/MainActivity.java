package com.example.rester;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {


    private TextView btnone, btntwo;
    private Button btnlogin;

    private EditText textphone, textpassword;
    String username = "";
    String password = "";
    private static final String UPLOAD_URL = "https://project-alltest.com/appfood/checkuser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = (Button) findViewById(R.id.login);
        btntwo = (TextView) findViewById(R.id.register);
        btnlogin.setOnClickListener(this);
        btntwo.setOnClickListener(this);


        textphone = (EditText) findViewById(R.id.phone);
        textpassword = (EditText) findViewById(R.id.password);


    }

    public void onClick(View view) {
        if (view == btnlogin) {

            username = textphone.getText().toString();
            password = textpassword.getText().toString();

            if (username.equals("")|| password.equals("")) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle("แจ้งเตือน");
                builder.setMessage(" กรุณากรอก Username / Password ");
                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

            }else {

                String urlSuffix = "?strUser="+username+"&password="+password;

                class RegisterUser extends AsyncTask<String, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(MainActivity.this, "Please Wait",null, true, true);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        int A = s.length();
                        if(s.equals("M")) {

                            Intent intent = new Intent(MainActivity.this, welcome.class);
                            intent.putExtra("A", username);
                            startActivity(intent);
                            finish();

                        }else{

                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(false);
                            builder.setTitle("แจ้งเตือน");
                            builder.setMessage(" Username / Password  ผิด ");
                            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();

                        }
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        String s = params[0];
                        BufferedReader bufferedReader = null;
                        try {
                            URL url = new URL(UPLOAD_URL+s);
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

            }

        }else  if (view == btntwo) {
            Intent intent = new Intent(MainActivity.this, register.class);
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage(" กรุณายืนยันการออกจากระบบ ?");

        builder.setPositiveButton(" ออกจากระบบ ", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton(" ยกเลิก ", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }


}