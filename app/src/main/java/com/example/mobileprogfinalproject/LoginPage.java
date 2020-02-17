package com.example.mobileprogfinalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class LoginPage extends AppCompatActivity {

    Toolbar toolbar;
    Button loginButton, registerButton;
    Intent intent;
    EditText username,password;
    String inputUsername, inputPassword;
    final String USERNAME = "james";
    final String PASSWORD = "james123";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);


        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        username = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Signing Up...",Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputUsername = username.getText().toString();
                inputPassword = password.getText().toString();
                if(inputUsername.equals(USERNAME) && inputPassword.equals(PASSWORD)){
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getApplicationContext(),UserPage.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuAbout:{
                intent = new Intent(getApplicationContext(),AboutPage.class);
                startActivity(intent);
                break;
            }
            case R.id.menuHelp:{
                Toast.makeText(getApplicationContext(),"Help Menu", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }




}
