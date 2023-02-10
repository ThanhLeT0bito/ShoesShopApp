package com.example.demo;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class cartProduct {
    String idProduct;
    String nameProduct;
    String priceProduct;
    String imgProduct;
    int sizeProduct;
    String colorProduct;
    int quantityProduct;

    public cartProduct() {
    }

    public cartProduct(String idProduct, String nameProduct, String priceProduct, String imgProduct, int sizeProduct, String colorProduct, int quantityProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.imgProduct = imgProduct;
        this.sizeProduct = sizeProduct;
        this.colorProduct = colorProduct;
        this.quantityProduct = quantityProduct;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("idProduct", idProduct);
        result.put("nameProduct", nameProduct);
        result.put("priceProduct", priceProduct);
        result.put("imgProduct", imgProduct);
        result.put("sizeProduct", sizeProduct);
        result.put("colorProduct", colorProduct);
        result.put("quantityProduct", quantityProduct);

        return result;
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

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
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
