package com.example.app_kasir_uas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity2 extends AppCompatActivity implements ListView.OnItemClickListener{

    //deklarasi variabel
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //mengambil id dari activity_main2.xml untuk di pakai di variabel di sebelah kiri
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

        //mengambil id dari activity_main2.xml untuk di pakai di variabel di sebelah kiri
        Button btnKembali = findViewById(R.id.btnKembali);
        //mensetting button btnTampilData agar ketika diklik
        //dapat menampilkan MainActivity1
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(kembali);
            }
        });

    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        //membuat arraylist bernama list
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            //mengambil data dan menambahkannya ke arraylist list
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_IDKASIR);
                String namab = jo.getString(konfigurasi.TAG_NAMAB);
                String harga = jo.getString(konfigurasi.TAG_HARGA);
                String jumlah = jo.getString(konfigurasi.TAG_JUMLAH);
                String diskon = jo.getString(konfigurasi.TAG_DISKON);
                String tanggal = jo.getString(konfigurasi.TAG_TANGGAL);
                String namak = jo.getString(konfigurasi.TAG_NAMAK);
                String total = jo.getString(konfigurasi.TAG_TOTAL);

                HashMap<String,String> pkll = new HashMap<>();
                pkll.put(konfigurasi.TAG_IDKASIR,id);
                pkll.put(konfigurasi.TAG_NAMAB,namab);
                pkll.put(konfigurasi.TAG_HARGA,harga);
                pkll.put(konfigurasi.TAG_JUMLAH,jumlah);
                pkll.put(konfigurasi.TAG_DISKON,diskon);
                pkll.put(konfigurasi.TAG_TANGGAL,tanggal);
                pkll.put(konfigurasi.TAG_NAMAK,namak);
                pkll.put(konfigurasi.TAG_TOTAL,total);
                list.add(pkll);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //menampilkan data pada listview
        ListAdapter adapter = new SimpleAdapter(
                MainActivity2.this, list, R.layout.list_item,
                new String[]{konfigurasi.TAG_IDKASIR,konfigurasi.TAG_NAMAB,konfigurasi.TAG_HARGA,konfigurasi.TAG_JUMLAH,konfigurasi.TAG_DISKON,konfigurasi.TAG_TANGGAL,konfigurasi.TAG_NAMAK,konfigurasi.TAG_TOTAL},
                new int[]{R.id.editTextId, R.id.namab, R.id.harga, R.id.jumlah, R.id.diskon, R.id.tanggal, R.id.namak, R.id.total});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity2.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilKASIR.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi.TAG_IDKASIR).toString();
        intent.putExtra(konfigurasi.EMP_ID,empId);
        startActivity(intent);
    }
}