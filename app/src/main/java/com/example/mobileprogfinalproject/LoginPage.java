package com.example.mobileprogfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;


public class LoginPage extends AppCompatActivity {

    private Accounts currentAccount;
    private DatabaseHelper database;

    private Toolbar toolbar;
    private ImageButton  registerButton,loginButton;
    private Intent intent;
    private TextInputLayout usernameField,passwordField;
    private String inputUsername, inputPassword;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);

        database = new DatabaseHelper(this);
        initializeWidgets();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStrings();

                if(validateFields()){

                    currentAccount = database.validateLogin(new Accounts(0, inputUsername,null, inputPassword, null));

                    if(currentAccount != null){
                        intent = new Intent(getApplicationContext(),HomePage.class);
                        intent.putExtra("ACCOUNT_ID",currentAccount.getID());
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginPage.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(intent);
            }
        });

    }


    private void initializeWidgets(){
        usernameField = findViewById(R.id.loginUsernameField);
        passwordField = findViewById(R.id.loginPasswordField);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
    }

    private void setStrings(){
        inputUsername = usernameField.getEditText().getText().toString();
        inputPassword = passwordField.getEditText().getText().toString();
    }

    private boolean validateUsername(){

        if(inputUsername.isEmpty()){
            usernameField.setError("Field can't be empty");
            return false;
        }else{
            usernameField.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){

        if(inputPassword.isEmpty()){
            passwordField.setError("Field can't be empty");
            return false;
        }else{
            passwordField.setError(null);
            passwordField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFields(){

        if(!validateUsername() | !validatePassword()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }





}
