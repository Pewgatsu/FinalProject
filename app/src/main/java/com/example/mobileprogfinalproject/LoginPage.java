package com.example.mobileprogfinalproject;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.google.android.material.textfield.TextInputLayout;


public class LoginPage extends AppCompatActivity {

    private Accounts currentAccount;
    private DatabaseHelper database;

    private Toolbar toolbar;
    private Button loginButton, registerButton;
    private Intent intent;
    private TextInputLayout usernameField,passwordField;
    private String inputUsername, inputPassword;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        database = new DatabaseHelper(this);
        initializeWidgets();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStrings();

                if(validateFields()){

                    currentAccount = database.validateLogin(new Accounts(0, inputUsername, inputPassword, null));

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
