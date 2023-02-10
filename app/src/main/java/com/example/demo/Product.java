package com.example.demo;

public class Product {
    String idProduct;
    String nameProduct;
    String priceProduct;
    String descriptionProduct;
    String imgProduct;
    String colorProduct;
    int quantityProduct;
    int quantitySoldOutProduct;

    public Product() {
    }

    public Product(String idProduct, String nameProduct, String priceProduct, String descriptionProduct, String imgProduct, String colorProduct, int quantityProduct, int quantitySoldOutProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.descriptionProduct = descriptionProduct;
        this.imgProduct = imgProduct;
        this.colorProduct = colorProduct;
        this.quantityProduct = quantityProduct;
        this.quantitySoldOutProduct = quantitySoldOutProduct;
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

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
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

    public int getQuantitySoldOutProduct() {
        return quantitySoldOutProduct;
    }

    public void setQuantitySoldOutProduct(int quantitySoldOutProduct) {
        this.quantitySoldOutProduct = quantitySoldOutProduct;
    }
}
