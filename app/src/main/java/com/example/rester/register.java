package com.example.rester;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class register extends AppCompatActivity   implements View.OnClickListener{

    private Button buttonsavebug, buttoncancel;
    String name, name2, name3 ;

    String Str1,Str2,Str3,Str4,Str5,Str6,Str7,Str8;

    private EditText no1, no2, no3, no4, no5, no6, no7, no8;
    String formattedDate2;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final String UPLOAD_URL = "https://project-alltest.com/appfood/saveregister.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonsavebug = (Button) findViewById(R.id.save);
        buttonsavebug.setOnClickListener(this);

        no1 = (EditText) findViewById(R.id.e1);
        no2 = (EditText) findViewById(R.id.e2);
        no3 = (EditText) findViewById(R.id.e3);
        no4 = (EditText) findViewById(R.id.e4);
        no5 = (EditText) findViewById(R.id.e5);


    }

    public void onClick(View view) {
        if(view == buttonsavebug) {
            submitForm();
        }
    }

    private void submitForm() {

        Str1 = no1.getText().toString().trim();
        Str2 = no2.getText().toString().trim();
        Str3 = no3.getText().toString().trim();
        Str4 = no4.getText().toString().trim();
        Str5 = no5.getText().toString().trim();



        if (Str1.equals("")|| Str2.equals("")| Str3.equals("")| Str4.equals("")| Str5.equals("")) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
            builder.setCancelable(false);
            builder.setTitle("แจ้งเตือน");
            builder.setMessage(" กรุณากรอกข้อมูลให้ครบ ");
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();


        }else{

            String urlSuffix = "?no1="+Str1+ "&no2="+Str2+ "&no3="+Str3+"&no4="+Str4+"&no5="+Str5;

            class RegisterUser extends AsyncTask<String, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(register.this, "Please Wait",null, true, true);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    int A = s.length();
                    if(s.equals("บันทึกไม่สำเร็จ")){

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
                        builder.setCancelable(false);
                        builder.setTitle("แจ้งเตือน");
                        builder.setMessage(" การสมัครผิดพลาด ");
                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();

                    }else if(s.equals("บันทึกเรียบร้อย")){

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
                        builder.setCancelable(false);
                        builder.setTitle("แจ้งเตือน");
                        builder.setMessage(" การสมัครเรียบร้อย ");
                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();


                                Intent intent = new Intent(register.this , MainActivity.class);
                                startActivity(intent);
                                finish();


                            }
                        });
                        builder.show();

                    }else if(s.equals("ชื่อซ้ำในระบบ")){

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(register.this);
                        builder.setCancelable(false);
                        builder.setTitle("แจ้งเตือน");
                        builder.setMessage(" ชื่อซ้ำในระบบ ");
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

    }

    public void onBackPressed() {
        Intent intent = new Intent(register.this , MainActivity.class);
        startActivity(intent);
        finish();

    }


}