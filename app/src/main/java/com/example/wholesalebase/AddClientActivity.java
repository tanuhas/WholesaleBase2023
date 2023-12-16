package com.example.wholesalebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddClientActivity extends AppCompatActivity {

    EditText name, phone, adres;
    Button btnAdd,btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        name = (EditText) findViewById(R.id.txtName);
        phone = (EditText)findViewById(R.id.txtPhone);
        adres = (EditText)findViewById(R.id.txtAdres);

        btnAdd= (Button)findViewById(R.id.btnAdd);
        btnBack= (Button)findViewById(R.id.btnBack);
        //обработка кнопочек
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //переход на предыдущую строчку
                finish();
            }
        });
    }
    private void insertData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("phone", phone.getText().toString());
        map.put("adres", adres.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Clients").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddClientActivity.this, "Данные успешно добавлены!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddClientActivity.this, "Ошибка...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll()
    {
        name.setText("");
        phone.setText("");
        adres.setText("");
    }
}