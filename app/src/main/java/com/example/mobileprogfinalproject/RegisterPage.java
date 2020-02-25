package com.example.mobileprogfinalproject;

import android.accounts.Account;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterPage extends AppCompatActivity {

    private Accounts account;
    private ImageButton createButton;
    private DatabaseHelper database;
    private TextInputLayout emailField, nameField, usernameField, passwordField, confirmpasswordField;
    private String username, fullname, email, password, confirmPassword;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_register);

        database = new DatabaseHelper(this);
        initializeWidgets();


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStrings();

                account = new Accounts(0,username,fullname,password,email);

                if(confirmField(createButton)){
                    database.createAccount(account);
                    Toast.makeText(RegisterPage.this, "Account Created!", Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            }
        });




    }


    private void setStrings(){
        fullname = nameField.getEditText().getText().toString();
        email = emailField.getEditText().getText().toString();
        username = usernameField.getEditText().getText().toString().trim();
        password = passwordField.getEditText().getText().toString();
        confirmPassword = confirmpasswordField.getEditText().getText().toString();
    }

    private void initializeWidgets(){

       nameField = findViewById(R.id.regNameField);
       createButton = findViewById(R.id.createButton);
       emailField = findViewById(R.id.emailField);
       usernameField = findViewById(R.id.regUsernameField);
       passwordField = findViewById(R.id.regPasswordField);
       confirmpasswordField = findViewById(R.id.regConfirmPassword);
    }

    private void clearFields(){
        nameField.getEditText().setText("");
        emailField.getEditText().setText("");
        usernameField.getEditText().setText("");
        passwordField.getEditText().setText("");
        confirmpasswordField.getEditText().setText("");
    }

    private boolean validateEmail(){

        if(email.isEmpty()){
            emailField.setError("Field can't be empty");
            return false;
        }else if(!database.checkEmail(email)){
            emailField.setError("Email already registered");
            return false;
        }else{
            emailField.setError(null);
            emailField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername(){


        if(username.isEmpty()){
            usernameField.setError("Field can't be empty");
            return false;
        }else if(!database.checkUsername(username)){
            usernameField.setError("Username already taken");
            return false;
        }else if(username.length() > 15){
            usernameField.setError("Username too long!");
            return false;
        }else{
            usernameField.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){


        if(password.isEmpty()){
            passwordField.setError("Field can't be empty");
            return false;
        }else{
            passwordField.setError(null);
            passwordField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword(){

        password = passwordField.getEditText().getText().toString();

        if(confirmPassword.isEmpty()){
            confirmpasswordField.setError("Field can't be empty");
            return false;
        }else if(!confirmPassword.equals(password)){
            confirmpasswordField.setError("Password does not match");
            return false;
        }
        else{
            confirmpasswordField.setError(null);
            confirmpasswordField.setErrorEnabled(false);
            return true;
        }
    }

    public boolean confirmField(View v){
        if(!validateEmail() | !validateUsername() | !validatePassword() | !validateConfirmPassword()){
            return false;
        }else{
            return true;
        }
    }
}
