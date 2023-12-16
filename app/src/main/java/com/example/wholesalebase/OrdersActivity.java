package com.example.wholesalebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wholesalebase.ui.Users.MainActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class OrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;

    FloatingActionButton floatingActionButton;
    FloatingActionButton BackButton;

    FloatingActionButton Exit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OrdersModel> options =
                new FirebaseRecyclerOptions.Builder<OrdersModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Orders"), OrdersModel.class)
                        .build();

        orderAdapter=new OrderAdapter(options);
        recyclerView.setAdapter(orderAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddOrderActivity.class));
            }
        });
            //обработка кнопки для перехода предыдущую станицу
        BackButton= (FloatingActionButton) findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //ТУУУУТ!!!!!!!!!!!
        Exit=(FloatingActionButton) findViewById(R.id.ExitButton);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent logoutIntent = new Intent(OrdersActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        orderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orderAdapter.stopListening();
    }

    //@SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item= menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<OrdersModel> options =
                new FirebaseRecyclerOptions.Builder<OrdersModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Orders").orderByChild("name").startAt(str).endAt(str + "\uf8ff"), OrdersModel.class)
                        .build();

        orderAdapter = new OrderAdapter(options);
        orderAdapter.startListening();
        recyclerView.setAdapter(orderAdapter);
    }

}