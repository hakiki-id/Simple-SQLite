package com.example.acer.cobasqlite;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 2016-04-23.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private  static final  int DATABASE_VERSION = 1;
    private static  final  String DATABASE_NAME = "kotakmanajer";
    private  static  final  String TABLE_CONTACT = "kontak";

    //key COLOUMN
    private static final String KEY_NPM = "npm";
    private static final String KEY_NAMA= "nama";
    private static final String KEY_NO_HP = "phone_number";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db ) {
        String CREATE_CONTACT_TABLE = "create table " +
                TABLE_CONTACT + "(" + KEY_NPM +" Integer Primary Key,"+
                KEY_NAMA +" Text,"+KEY_NO_HP+" Text"+")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old rable if existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        //buat tabel lagi
        onCreate(db);
    }//Upgradding database



    void TambahKontak(Kontak2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, contact.getNama()); //contact name
        values.put(KEY_NO_HP, contact.getNo_hp()); // ngisi no. hp

        //tambahno neng database
        db.insert(TABLE_CONTACT,null,values);
        db.close();
    }


    Kontak2 getContact (int npm) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACT, new String[]{KEY_NPM,KEY_NAMA,KEY_NO_HP
        }, KEY_NPM + " =?", new String[]{String.valueOf(npm)},null,null,null,null);

        if (cursor != null) cursor.moveToFirst();

        Kontak2 contact ;
        contact = new Kontak2(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return contact;
    }

    public List<Kontak2> getAllContact() {
        List<Kontak2> contactList = new ArrayList<Kontak2>();

        String selectQuery = "SELECT * FROM " + TABLE_CONTACT;

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //mengulang sebanyak data yang ada
        if (cursor.moveToFirst()) {
            do{
                Kontak2 contact  = new Kontak2();
                contact.setNpm(Integer.parseInt(cursor.getString(0)));
                contact.setNama(cursor.getString(1));
                contact.setNo_hp(cursor.getString(2));
                //tambahkan kotak ke lish
                contactList.add(contact);

            }while (cursor.moveToNext());
        }

        return contactList;
    }
//get ALL CONTACT

    public int updateContact (Kontak2 contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, contact.getNama());
        values.put(KEY_NO_HP, contact.getNo_hp());

        //update baris
        return  db.update(TABLE_CONTACT, values, KEY_NPM+"=?", new String[]
                {String.valueOf(contact.getNpm())});
    }

    //deleting singgle contack
    public void deleteContact ( Kontak2 contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT, KEY_NPM+"=?",new String[]{String.valueOf(contact.getNpm())});
        db.close();
    }

    //get contact count
    public int getContactCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return  cursor.getCount();
    }
}