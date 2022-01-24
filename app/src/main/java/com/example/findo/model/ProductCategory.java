package com.example.findo.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductCategory {
    private Integer id;
    private String name;
    private ArrayList<Product> product;

    public ProductCategory(DataSnapshot categorySnapshot, String name) {
        this.id = Integer.parseInt(categorySnapshot.getKey());
        this.name = categorySnapshot.child("name").getValue().toString();
    }

    public ProductCategory(DataSnapshot categorySnapshot) {
        this.id = Integer.parseInt(categorySnapshot.getKey());
        this.name = categorySnapshot.child("name").getValue().toString();
        ArrayList<Product> productList = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://findo-d605f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("product");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    if (Integer.parseInt(productSnapshot.child("product_category_id").getValue().toString()) == Integer.parseInt(categorySnapshot.getKey())) {
                        productList.add(new Product(productSnapshot));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.d("fdatabase", "onDataChange: " + error.getMessage());
            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);
        this.product = productList;
    }

    public ProductCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
}
