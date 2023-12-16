package com.example.wholesalebase.ui.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wholesalebase.R;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private String categoryName;
    private ImageView productImage;
    private EditText productArt, productName, productQuantity, productWarehouse, productPrice;
    private Button addNewProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        init();

    }
    private void init()
    {
        categoryName=getIntent().getExtras().get("category").toString();
        productArt=findViewById(R.id.product_art);
        productName=findViewById(R.id.product_name);
        productQuantity=findViewById(R.id.product_quantity);
        productWarehouse=findViewById(R.id.product_warehouse);
        productPrice=findViewById(R.id.product_price);
        addNewProductButton=findViewById(R.id.btn_add_new_product);
    }
}