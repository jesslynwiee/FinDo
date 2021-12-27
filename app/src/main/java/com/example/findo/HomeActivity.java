package com.example.findo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findo.adapter.ItemListAdapter;
import com.example.findo.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;

    ImageView iv_photo;

    private ArrayList<Product> mproducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //just testing bitmap
        ImageButton btn_photo = findViewById(R.id.photo);
        iv_photo = findViewById(R.id.testphoto);

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }else{
                    String[] permissions = new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    };
                    ActivityCompat.requestPermissions(HomeActivity.this, permissions, CAMERA_PERMISSION_CODE);
                }
            }
        });

        RecyclerView rvProducts = findViewById(R.id.recyclerview_home);

        //dummy data
        mproducts.clear();
        mproducts.add(new Product(1, 1, "Sepatu", 2, 2, 2, "Adidas", "Jakarta", "hallo hallo", new String[]{"https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg", "https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg"}));
        mproducts.add(new Product(2, 1, "Sepatu aDIDAS", 2, 2, 2, "Adidas", "Jakarta", "hallo hallo", new String[]{"https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg", "https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg"}));
        mproducts.add(new Product(3, 1, "Sepatu aDIDASs", 2, 2, 2, "Adidas", "Jakarta", "hallo hallo", new String[]{"https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg", "https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg"}));
        mproducts.add(new Product(4, 1, "Sepatu aDIDASss", 2, 2, 2, "Adidas", "Jakarta", "hallo hallo", new String[]{"https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg", "https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg"}));
        mproducts.add(new Product(5, 1, "Sepatu aDIDASsss", 2, 2, 2, "Adidas", "Jakarta", "hallo hallo", new String[]{"https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg", "https://static.republika.co.id/uploads/images/inpicture_slide/google-_150902081143-333.jpg"}));

        ItemListAdapter adapter = new ItemListAdapter(mproducts);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap finalPhoto = (Bitmap) bundle.get("data");
            Intent intent = new Intent(this, ArResultActivity.class);
            intent.putExtra("data", finalPhoto);
            startActivity(intent);
        }
    }
}