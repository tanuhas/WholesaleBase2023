package com.example.wholesalebase.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wholesalebase.Model.Users;
import com.example.wholesalebase.Prevalent.Prevalent;
import com.example.wholesalebase.ProductsActivity;
import com.example.wholesalebase.R;
import com.example.wholesalebase.ui.Admin.AdminCategoryActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity
{
    private Button loginBtn;
    private EditText usernameInput,postInput, phoneInput, passwordInput;
    private ProgressDialog loadinBar;
    private TextView AdminLink, NotAdminLink;

    private static String parentDbName = "Users";
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn=(Button) findViewById(R.id.login_btn);
        phoneInput=(EditText) findViewById(R.id.login_phone_input);
        passwordInput=(EditText) findViewById(R.id.login_password_input);
        loadinBar= new ProgressDialog(this);
        checkBoxRememberMe = (CheckBox)findViewById(R.id.login_checkbox);
        Paper.init(this);

        AdminLink=(TextView)findViewById(R.id.admin_panel_link);
        NotAdminLink=(TextView)findViewById(R.id.not_admin_panel_link);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                loginBtn.setText("Вход");
                parentDbName="Admins";
            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                loginBtn.setText("Войти");
                parentDbName="Users";
            }
        });
    }

    private void loginUser() {
        String phone = phoneInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Введите номер", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else{
            loadinBar.setTitle("Вход в приложение");
            loadinBar.setMessage("Пожалуйста, подождите");
            loadinBar.setCanceledOnTouchOutside(false);
            loadinBar.show();

            ValidateUser(phone, password);
        }
    }

    private void ValidateUser(String phone, String password) {

        if(checkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersDate = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                        if(usersDate.getPhone().equals(phone))
                        {
                            if(usersDate.getPassword().equals(password))
                            {
                                //проверка на админа
                               if (parentDbName.equals("Users"))
                               {
                                   loadinBar.dismiss();
                                   Toast.makeText(LoginActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                                   Intent homeIntent = new Intent(LoginActivity.this, ProductsActivity.class);
                                   startActivity(homeIntent);
                               }
                               else if(parentDbName.equals("Admins"))
                               {
                                   loadinBar.dismiss();
                                   Toast.makeText(LoginActivity.this, "Успешный вход, заведующий!", Toast.LENGTH_SHORT).show();

                                   Intent homeIntent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                   startActivity(homeIntent);
                               }
                            }
                            else
                            {
                                loadinBar.dismiss();
                                Toast.makeText(LoginActivity.this, "Пароль неверный...", Toast.LENGTH_SHORT).show();

                            }
                        }
                }
                else
                {
                    loadinBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Аккаунт с номером" + phone + "не существует", Toast.LENGTH_SHORT).show();

                    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(registerIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}