package com.example.rester;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class home_list extends AppCompatActivity implements  View.OnClickListener {


    String name, name2, name3, name4, name5, name6, name7, name8, name9;

    private static final String readdata2 = "https://project-alltest.com/appfood/load_product_list.php"; ///
    private static final String readdata3 = "https://project-alltest.com/appfood/load_product_list_search.php"; ///

    ImageView databtn1, databtn2, databtn3, databtn4;
    private TextView bm1, bm2, bm3, bm4, bm5, bm6;
    ImageView imgadd;
    ImageView imgbtnback;
    private static final String UPLOAD_URL = "https://project-alltest.com/appfood/save_bookmark.php";

    EditText searchdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);


        Bundle b = getIntent().getExtras();
        name = b.getString("A");
        name2 = b.getString("B");

        name3 = b.getString("D1");
        name4 = b.getString("D2");
        name5 = b.getString("D3");
        name6 = b.getString("D4");

        bm1 = (TextView) findViewById(R.id.title2);
        bm1.setText(Html.fromHtml( " " + name3 + " <br> " + name4));

        ImageView imageView = (ImageView) findViewById(R.id.imgtitle);
        String chk1 =name6;
        String url = "";
        if (!name6.equals("")) {
            url = "https://project-alltest.com/appfood/img/" + name6;
            Picasso.with(home_list.this).load(url).resize(1280, 680).into(imageView);
        }



        String urlSuffix2 =  "?txtKeyword="+name2;
        class RegisterUser2 extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(home_list.this, "Please Wait", null, true, true);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                int A = s.length();

                readdatalist(s);

            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(readdata2 + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        RegisterUser2 ru2 = new RegisterUser2();
        ru2.execute(urlSuffix2);


        imgadd = (ImageView) findViewById(R.id.imgadd);
        imgadd.setOnClickListener(this);


        imgbtnback = (ImageView) findViewById(R.id.imgbtnback);
        imgbtnback.setOnClickListener(this);



        databtn1 = (ImageView) findViewById(R.id.btnnext1);
        databtn2 = (ImageView) findViewById(R.id.btnnext2);
        databtn3 = (ImageView) findViewById(R.id.btnnext3);
        databtn4 = (ImageView) findViewById(R.id.btnnext4);

        databtn1.setOnClickListener(this);
        databtn2.setOnClickListener(this);
        databtn3.setOnClickListener(this);
        databtn4.setOnClickListener(this);


        searchdata = (EditText) findViewById(R.id.phone);
        searchdata.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String getdata = searchdata.getText().toString();

                String urlSuffix2 =  "?txtKeyword="+name2+"&search="+getdata;
                class RegisterUser2 extends AsyncTask<String, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        int A = s.length();

                        readdatalist(s);

                    }

                    @Override
                    protected String doInBackground(String... params) {
                        String s = params[0];
                        BufferedReader bufferedReader = null;
                        try {
                            URL url = new URL(readdata3 + s);
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                            String result;

                            result = bufferedReader.readLine();

                            return result;
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }
                RegisterUser2 ru2 = new RegisterUser2();
                ru2.execute(urlSuffix2);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });



    }

    public void onClick(View view) {
        if (view == imgadd) {

            String urlSuffix = "?member=" + name + "&rester=" + name2;
            class RegisterUser extends AsyncTask<String, Void, String> {

                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    int A = s.length();
                    if (s.equals("บันทึกไม่สำเร็จ")) {

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(home_list.this);
                        builder.setCancelable(false);
                        builder.setTitle("แจ้งเตือน");
                        builder.setMessage(" บันทึกไม่สำเร็จ ");
                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();

                    } else if (s.equals("บันทึกเรียบร้อย")) {


                    }
                }

                @Override
                protected String doInBackground(String... params) {
                    String s = params[0];
                    BufferedReader bufferedReader = null;
                    try {
                        URL url = new URL(UPLOAD_URL + s);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                        String result;

                        result = bufferedReader.readLine();

                        return result;
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            RegisterUser ru = new RegisterUser();
            ru.execute(urlSuffix);

        }else  if (view == imgbtnback) {

            Intent intent = new Intent(home_list.this, home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        }else   if (view == databtn1) {

            Intent intent = new Intent(home_list.this, home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn2) {

            Intent intent = new Intent(home_list.this, settingpage.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn3) {

            Intent intent = new Intent(home_list.this, bookmark.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn4) {


            Intent intent = new Intent(home_list.this, profile.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();
        }
    }


    private void readdatalist(String s) {

        String strJSON = s;
        try {
            JSONArray data = new JSONArray(strJSON);

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);
                int Row = i + 1;
                map = new HashMap<String, String>();
                map.put("count", Integer.toString(Row));
                map.put("id", c.getString("id"));
                map.put("name", c.getString("name"));
                map.put("detail", c.getString("detail"));
                map.put("price", c.getString("price"));
                map.put("rester", c.getString("rester"));
                map.put("img", c.getString("img"));

                MyArrList.add(map);

            }

            final ListView listViewMovies = (ListView) findViewById(R.id.list1);
            listViewMovies.setAdapter(new ImageAdapter(this, MyArrList));

            final androidx.appcompat.app.AlertDialog.Builder viewDetail = new androidx.appcompat.app.AlertDialog.Builder(this);
            final LayoutInflater factory = LayoutInflater.from(this);
            // OnClick Item
            listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView,
                                        int position, long mylng) {


                }
            });


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<HashMap<String, String>> MyArr = new ArrayList<HashMap<String, String>>();

        public ImageAdapter(Context c, ArrayList<HashMap<String, String>> list) {
            // TODO Auto-generated method stub
            context = c;
            MyArr = list;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return MyArr.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_column_home_list, null);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            String chk1 = MyArr.get(position).get("img");
            String url = "";
            if (!MyArr.get(position).get("img").equals("")) {

                url = "https://project-alltest.com/appfood/img/" + MyArr.get(position).get("img");
                Picasso.with(context).load(url).resize(350, 400).into(imageView);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String id_order = MyArr.get(position).get("id");
                    String title = MyArr.get(position).get("name");
                    String typedata = MyArr.get(position).get("detail");
                    String price = MyArr.get(position).get("price");
                    String img = MyArr.get(position).get("img");

                    Intent intent = new Intent(home_list.this, home_list_price.class);
                    intent.putExtra("A", name);
                    intent.putExtra("B", name2);
                    intent.putExtra("C", id_order);

                    intent.putExtra("D1", title);
                    intent.putExtra("D2", typedata);
                    intent.putExtra("D3", price);
                    intent.putExtra("D4", img);


                    intent.putExtra("F1", name3);
                    intent.putExtra("F2", name4);
                    intent.putExtra("F3", name5);
                    intent.putExtra("F4", name6);



                    startActivity(intent);
                    finish();

                }
            });



            // ColPosition
            TextView txtPosition1 = (TextView) convertView.findViewById(R.id.name_order);
            txtPosition1.setText(Html.fromHtml(" <b> " +MyArr.get(position).get("name")  + " </b> <br> " +
                            " <small> "+ MyArr.get(position).get("detail") + " </small> "));

            // ColPosition
            TextView txtPosition2 = (TextView) convertView.findViewById(R.id.name_price);
            txtPosition2.setText(Html.fromHtml(" <b> " + MyArr.get(position).get("price")  + " </b> " ));


            return convertView;

        }


    }


    public void onBackPressed() {

    }
}