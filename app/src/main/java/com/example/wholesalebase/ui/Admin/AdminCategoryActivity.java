package com.example.wholesalebase.ui.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wholesalebase.ClientsActivity;
import com.example.wholesalebase.OrdersActivity;
import com.example.wholesalebase.PostsActivity;
import com.example.wholesalebase.ProductsActivity;
import com.example.wholesalebase.R;
import com.example.wholesalebase.WarehousesActivity;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView warehouses, products, clients, posts, orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        init();
//вот здесь меняю странички перехода для кажой сферы
        warehouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminCategoryActivity.this, WarehousesActivity.class);
                intent.putExtra("category", "warehouses");
                startActivity(intent);
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminCategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "products");
                startActivity(intent);
            }
        });

        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminCategoryActivity.this, ClientsActivity.class);
                intent.putExtra("category", "clients");
                startActivity(intent);
            }
        });

        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminCategoryActivity.this, PostsActivity.class);
                intent.putExtra("category", "posts");
                startActivity(intent);
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminCategoryActivity.this, OrdersActivity.class);
                intent.putExtra("category", "orders");
                startActivity(intent);
            }
        });
    }
    private void init()
    {
        warehouses=findViewById(R.id.warehouses);
        products=findViewById(R.id.products);
        clients=findViewById(R.id.clients);
        posts=findViewById(R.id.posts);
        orders=findViewById(R.id.orders);
    }
}