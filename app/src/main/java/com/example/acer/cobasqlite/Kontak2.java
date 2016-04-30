package com.example.acer.cobasqlite;

/**
 * Created by ACER on 2016-04-23.
 */
public class Kontak2 {
    private int npm ;
    private String nama ;
    private String no_hp ;

    public Kontak2(int npm, String nama, String no_hp) {
        this.npm = npm;
        this.nama = nama;
        this.no_hp = no_hp;
    }

    public Kontak2() {

    }

    public int getNpm() {
        return npm;
    }

    public void setNpm(int npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}
