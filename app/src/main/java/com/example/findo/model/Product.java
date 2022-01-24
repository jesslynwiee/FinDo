package com.example.findo.model;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Comparator;

public class Product {
    private Integer id;
    private String name;
    private Integer sold;
    private Integer price;
    private Integer stock;
    private String description;
    private ArrayList<String> images;
    private String brand;
    private String shippingFrom;
    private Integer productCategoryId;

    public Product() {
    }

    public Product(DataSnapshot productSnapshot) {
        this.id = Integer.parseInt(productSnapshot.getKey());
        this.name = productSnapshot.child("name").getValue().toString();
        this.sold = Integer.parseInt(productSnapshot.child("sold").getValue().toString());
        this.price = Integer.parseInt(productSnapshot.child("price").getValue().toString());
        ArrayList<String> productImages = new ArrayList<>();
        for (DataSnapshot productImageSnapshot : productSnapshot.child("images").getChildren()) {
            productImages.add(productImageSnapshot.getValue().toString());
        }
        this.stock = Integer.parseInt(productSnapshot.child("stock").getValue().toString());
        this.description = productSnapshot.child("description").getValue().toString();
        this.images = productImages;
        this.brand = productSnapshot.child("brand").getValue().toString();
        this.shippingFrom = productSnapshot.child("shippingFrom").getValue().toString();
        this.productCategoryId = Integer.parseInt(productSnapshot.child("product_category_id").getValue().toString());
    }

    public Product(Integer id, String name, Integer price, ArrayList<String> image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = image;
    }

    public Product(Integer id, String name, Integer sold, Integer price, Integer stock, String description, ArrayList<String> image, String gender) {
        this.id = id;
        this.name = name;
        this.sold = sold;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.images = image;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getShippingFrom() {
        return shippingFrom;
    }

    public void setShippingFrom(String shippingFrom) {
        this.shippingFrom = shippingFrom;
    }

    public static Comparator<Product> COMPARATORPRICEASCENDING = new Comparator<Product>() {
        // This is where the sorting happens.
        public int compare(Product o1, Product o2) {
            return o1.getPrice() - o2.getPrice();
        }
    };

    public static Comparator<Product> COMPARATORPRICEDESCENDING = new Comparator<Product>() {
        // This is where the sorting happens.
        public int compare(Product o1, Product o2) {
            return o2.getPrice() - o1.getPrice();
        }
    };

    public static Comparator<Product> COMPARATORSOLDDESCENDING = new Comparator<Product>() {
        // This is where the sorting happens.
        public int compare(Product o1, Product o2) {
            return o2.getSold() - o1.getSold();
        }
    };
}
