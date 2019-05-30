package com.example.camilan_oop;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private  String mName;
    private int mAmount;
    private int mQuantity;

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    String idFnb, menu, kategori, harga;

    public MenuItem(String idFnb, String menu, String kategori, String harga ) {
        this.idFnb = idFnb;
        this.menu = menu;
        this.kategori = kategori;
        this.harga = harga;
        this.mQuantity = 1;
    }


    public String getIdFnb() {
        return idFnb;
    }

    public String getMenu() {
        return menu;
    }

    public String getKategori() {
        return kategori;
    }

    public String getHarga(){
        return harga;
    }
    public int getmQuantity(){
        return mQuantity;
    }

    public void addToQuantity(){
        this.mQuantity += 1;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 0){
            this.mQuantity -= 1;
        }
    }


}
