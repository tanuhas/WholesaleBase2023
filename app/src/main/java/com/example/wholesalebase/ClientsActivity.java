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

public class ClientsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ClientAdapter clientAdapter;

    FloatingActionButton floatingActionButton;
    FloatingActionButton BackButton;
    FloatingActionButton Exit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       FirebaseRecyclerOptions<ClientsModel> options =
                new FirebaseRecyclerOptions.Builder<ClientsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Clients"), ClientsModel.class)
                        .build();

        clientAdapter=new ClientAdapter(options);
        recyclerView.setAdapter(clientAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddClientActivity.class));
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
        Exit=(FloatingActionButton) findViewById(R.id.ExitButton);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent logoutIntent = new Intent(ClientsActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        clientAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clientAdapter.stopListening();
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
        FirebaseRecyclerOptions<ClientsModel> options =
                new FirebaseRecyclerOptions.Builder<ClientsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Clients").orderByChild("name").startAt(str).endAt(str + "\uf8ff"), ClientsModel.class)
                        .build();

        clientAdapter = new ClientAdapter(options);
        clientAdapter.startListening();
        recyclerView.setAdapter(clientAdapter);
    }

}