package com.example.demo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class orderProduct {
    String idProduct;
    String nameProduct;
    String priceProduct;
    int sizeProduct;
    String colorProduct;
    int quantityProduct;

    public orderProduct(String idProduct, String nameProduct, String priceProduct, int sizeProduct, String colorProduct, int quantityProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.sizeProduct = sizeProduct;
        this.colorProduct = colorProduct;
        this.quantityProduct = quantityProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(int sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public String getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(String colorProduct) {
        this.colorProduct = colorProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
}
