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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class home_list_price extends AppCompatActivity  implements  View.OnClickListener {


    String name, name2, name3, name4, name5, name6, name7, name8, name9;

    String datab1, datab2, datab3, datab4;

    private static final String readdata2 = "https://project-alltest.com/appfood/load_product_list_price.php"; ///

    ImageView databtn1, databtn2, databtn3, databtn4;
    private TextView bm1, bm2, bm3, bm4, bm5, bm6;
    ImageView imgadd;
    ImageView imgbtnback;
    ImageView imgbtncon;
    EditText searchdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_price);

        Bundle b = getIntent().getExtras();
        name = b.getString("A");
        name2 = b.getString("B");
        name3 = b.getString("C");

        name4 = b.getString("D1");
        name5 = b.getString("D2");
        name6 = b.getString("D3");
        name7 = b.getString("D4");

        datab1 = b.getString("F1");
        datab2 = b.getString("F2");
        datab3 = b.getString("F3");
        datab4 = b.getString("F4");

        bm1 = (TextView) findViewById(R.id.name_order);
        bm1.setText(Html.fromHtml(
                " <b> " + name4 + " </b> <br> " +
                        " ท็อปปิ้ง :  - <br> <br>  "  +
                        " <small> ราคา : " + name6 + " บาท </small> "  ));

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        String chk1 =name7;
        String url = "";
        if (!name7.equals("")) {
            url = "https://project-alltest.com/appfood/img/" + name7;
            Picasso.with(home_list_price.this).load(url).resize(650, 450).into(imageView);
        }


        String urlSuffix2 =  "?txtKeyword="+name3;
        class RegisterUser2 extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(home_list_price.this, "Please Wait", null, true, true);

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


        databtn1 = (ImageView) findViewById(R.id.btnnext1);
        databtn2 = (ImageView) findViewById(R.id.btnnext2);
        databtn3 = (ImageView) findViewById(R.id.btnnext3);
        databtn4 = (ImageView) findViewById(R.id.btnnext4);

        databtn1.setOnClickListener(this);
        databtn2.setOnClickListener(this);
        databtn3.setOnClickListener(this);
        databtn4.setOnClickListener(this);

        imgbtnback = (ImageView) findViewById(R.id.imgbtnback);
        imgbtnback.setOnClickListener(this);

        imgbtncon = (ImageView) findViewById(R.id.img_confrim);
        imgbtncon.setOnClickListener(this);



    }

    public void onClick(View view) {
       if (view == databtn1) {

            Intent intent = new Intent(home_list_price.this, home.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn2) {

            Intent intent = new Intent(home_list_price.this, settingpage.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn3) {

            Intent intent = new Intent(home_list_price.this, bookmark.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();

        } else if (view == databtn4) {


            Intent intent = new Intent(home_list_price.this, profile.class);
            intent.putExtra("A", name);
            startActivity(intent);
            finish();


       }else  if (view == imgbtnback) {

           Intent intent = new Intent(home_list_price.this, home_list.class);
           intent.putExtra("A", name);
           intent.putExtra("B", name2);

           intent.putExtra("D1", datab1);
           intent.putExtra("D2", datab2);
           intent.putExtra("D3", datab3);
           intent.putExtra("D4", datab4);

           startActivity(intent);
           finish();

       }else  if (view == imgbtncon) {

           Intent intent = new Intent(home_list_price.this, home.class);
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
                map.put("price", c.getString("price"));
                map.put("onoff", c.getString("onoff"));
                map.put("type", c.getString("type"));

                MyArrList.add(map);

            }

            final ListView listViewMovies = (ListView) findViewById(R.id.list1);
            listViewMovies.setAdapter(new ImageAdapter(this, MyArrList));

            final androidx.appcompat.app.AlertDialog.Builder viewDetail = new androidx.appcompat.app.AlertDialog.Builder(this);
            final LayoutInflater factory = LayoutInflater.from(this);
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
                convertView = inflater.inflate(R.layout.activity_column_home_list_price, null);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            if (MyArr.get(position).get("type").equals("G")) {
                Picasso.with(context).load(R.drawable.glogo).into(imageView);
            }else if (MyArr.get(position).get("type").equals("P")) {
                Picasso.with(context).load(R.drawable.plogo).into(imageView);
            }

            ImageView imageView2 = (ImageView) convertView.findViewById(R.id.imgtop);
            if (MyArr.get(position).get("onoff").equals("0")) {
                imageView2.setVisibility(View.GONE);
            }else if (MyArr.get(position).get("onoff").equals("1")) {
                imageView2.setVisibility(View.VISIBLE);
            }

            // ColPosition
            TextView txtPosition1 = (TextView) convertView.findViewById(R.id.name_order);
            txtPosition1.setText(Html.fromHtml(
                    "  <b> รายการ " + name4  + " </b> <br> " +
                    "  <b> รายการ " + name4  + " </b> <br> " +
                            " <small> ราคา : " + MyArr.get(position).get("price") + " ฿ </small> "  ));

            // ColPosition
            TextView txtPosition2 = (TextView) convertView.findViewById(R.id.name_price);
            txtPosition2.setText(Html.fromHtml(  MyArr.get(position).get("price") + " ฿  "  ));

            return convertView;

        }


    }





    public void onBackPressed() {

    }

}