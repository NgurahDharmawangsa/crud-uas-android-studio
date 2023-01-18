package com.example.app_kasir_uas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    Spinner txtNamab, txtJumlah, txtNamak;
    EditText  txtHarga, txtDiskon, txtTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengambil id dari activity_main2.xml untuk di pakai di variabel di sebelah kiri
        txtNamab = (Spinner) findViewById(R.id.txtNamab);
        txtHarga = (EditText) findViewById(R.id.txtHarga);
        txtJumlah = (Spinner) findViewById(R.id.txtJumlah);
        txtDiskon = (EditText) findViewById(R.id.txtDiskon);
        txtTanggal = (EditText) findViewById(R.id.txtTanggal);
        txtNamak = (Spinner) findViewById(R.id.txtNamak);

        Button btnTampilData = findViewById(R.id.btnTampilData);

        //mensetting button btnTampilData agar ketika diklik
        //dapat menampilkan MainActivity2
        btnTampilData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tampil = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(tampil);
            }

        });

    }

    public void btnSimpan(View view) {
        //mengambil isi dari variabel yang dikanan untuk disimpan ke variabel yang dikiri
        String namab = txtNamab.getSelectedItem().toString();
        String harga = txtHarga.getText().toString();
        String jumlah = txtJumlah.getSelectedItem().toString();
        String diskon = txtDiskon.getText().toString();
        String tanggal = txtTanggal.getText().toString();
        String namak = txtNamak.getSelectedItem().toString();

        int harga_int, jumlah_int, diskon_int, total;

        harga_int = Integer.parseInt(harga);
        jumlah_int = Integer.parseInt(jumlah);
        diskon_int = Integer.parseInt(diskon);

        diskon_int = ((harga_int * jumlah_int) * diskon_int) / 100;
        total = (harga_int * jumlah_int) -  diskon_int;

        //mengeksekusi class object dengan parameternya
        simpanData simpen = new simpanData(this);
        simpen.execute(namab, harga, jumlah, String.valueOf(diskon_int),tanggal, namak, String.valueOf(total));
    }
}