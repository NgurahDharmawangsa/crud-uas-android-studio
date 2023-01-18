package com.example.app_kasir_uas;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class TampilKASIR extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private Spinner editTextNamab;
    private EditText editTextHarga;
    private Spinner editTextJumlah;
    private EditText editTextDiskon;
    private EditText editTextTanggal;
    private Spinner editTextNamak;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_kasir);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);
        //id = "2";

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNamab = (Spinner) findViewById(R.id.editTextNamab);
        editTextHarga = (EditText) findViewById(R.id.editTextHarga);
        editTextJumlah = (Spinner) findViewById(R.id.editTextJumlah);
        editTextDiskon = (EditText) findViewById(R.id.editTextDiskon);
        editTextTanggal = (EditText) findViewById(R.id.editTextTanggal);
        editTextNamak = (Spinner) findViewById(R.id.editTextNamak);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilKASIR.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String namab = c.getString(konfigurasi.TAG_NAMAB);
            String harga = c.getString(konfigurasi.TAG_HARGA);
            String jumlah = c.getString(konfigurasi.TAG_JUMLAH);
            String diskon = c.getString(konfigurasi.TAG_DISKON);
            String tanggal = c.getString(konfigurasi.TAG_TANGGAL);
            String namak = c.getString(konfigurasi.TAG_NAMAK);


            //editTextNamab.setText(namab);
            editTextHarga.setText(harga);
            //editTextJumlah.setText(jumlah);
            editTextDiskon.setText(diskon);
            editTextTanggal.setText(tanggal);
            //ediTextNamak.setText(namak);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){

        final String namab = editTextNamab.getSelectedItem().toString().trim();
        final String harga = editTextHarga.getText().toString().trim();
        final String jumlah = editTextJumlah.getSelectedItem().toString().trim();
        final String diskon = editTextDiskon.getText().toString().trim();
        final String tanggal = editTextTanggal.getText().toString().trim();
        final String namak = editTextNamak.getSelectedItem().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilKASIR.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilKASIR.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_IDKASIR,id);
                hashMap.put(konfigurasi.KEY_NAMAB,namab);
                hashMap.put(konfigurasi.KEY_HARGA,harga);
                hashMap.put(konfigurasi.KEY_JUMLAH,jumlah);
                hashMap.put(konfigurasi.KEY_DISKON,diskon);
                hashMap.put(konfigurasi.KEY_TANGGAL,tanggal);
                hashMap.put(konfigurasi.KEY_NAMAK,namak);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilKASIR.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilKASIR.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Yakin Menghapus Data Ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilKASIR.this,MainActivity2.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}