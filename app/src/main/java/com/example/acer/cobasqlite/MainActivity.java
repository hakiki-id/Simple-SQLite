package com.example.acer.cobasqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void masukandata(View view){
        DatabaseHandler db = new DatabaseHandler(this);
        /*Crud
        * Operation*/

        Log.d("Masukan data", "Masukan Data");
        db.TambahKontak(new Kontak2(10,"Baidawi","0999999999"));

        //Memabaca Kontak
        Log.d("Membaca Data", "Reading All Contack");
        List<Kontak2> contacts = db.getAllContact();

        for (Kontak2 cn:contacts){
            String log = "ID :" + cn.getNpm()+"\n"
                    +"NAMA : "+ cn.getNama() + "\n"
                    +"NO HP : " + cn.getNo_hp() + "\n"+
                    "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

            Log.d("CONTACT",log);
        }
    }
}
