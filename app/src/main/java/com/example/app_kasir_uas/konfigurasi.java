package com.example.app_kasir_uas;

public class konfigurasi {
    //menyimpan text text yang berwara hijau ini ke dalam variabel bertipe string
    public static final String URL_GET_ALL = "http://192.168.43.182/Uasppb/tampilsemuakasir.php";
    public static final String URL_ADD_DATA = "http://192.168.43.182/Uasppb/tambahkasir.php";
    public static final String URL_GET_EMP = "http://192.168.43.182/Uasppb/tampilkasir.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.182/Uasppb/updatekasir.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.182/Uasppb/hapuskasir.php?id=";
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_IDKASIR = "id_kasir";
    public static final String TAG_NAMAB = "namab";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_JUMLAH = "jumlah";
    public static final String TAG_DISKON = "diskon";
    public static final String TAG_TANGGAL = "tanggal";
    public static final String TAG_NAMAK = "namak";
    public static final String TAG_TOTAL = "total";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_IDKASIR = "id_kasir";
    public static final String KEY_NAMAB = "namab";
    public static final String KEY_HARGA = "harga";
    public static final String KEY_JUMLAH = "jumlah";
    public static final String KEY_DISKON = "diskon";
    public static final String KEY_TANGGAL = "tanggal";
    public static final String KEY_NAMAK = "namak";
    public static final String KEY_TOTAL = "total";


    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "id_kasir";

}
