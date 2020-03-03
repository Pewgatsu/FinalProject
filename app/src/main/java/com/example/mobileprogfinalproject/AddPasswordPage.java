package com.example.mobileprogfinalproject;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;



public class AddPasswordPage extends AppCompatActivity {


    private TextInputLayout titleField, accountField, usernameField, passwordField, websiteField, notesField;
    private Button addButton, cancelButton;
    private String inputTitle, inputAccount, inputUsername, inputPassword, inputWebsite, inputNotes;
    private DatabaseHelper database;
    private Accounts accounts;
    private Passwords accountPasswords;
    private int accountID;
    private Bundle extras;
    private ImageView generateButton;
    private String generatedPassword;
    private PasswordGenerator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_addpassword);

        database = new DatabaseHelper(this);
        generator = new PasswordGenerator();
        initializeWidgets();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatedPassword = generator.generatePassword();
                passwordField.getEditText().setText(generatedPassword);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStrings();
                getAccountID();

                if(validateFields()){
                    accounts = new Accounts(accountID,null,null,null);

                    accountPasswords = new Passwords(0,inputTitle,inputAccount,inputUsername,inputPassword,inputWebsite,inputNotes,0);

                    if(database.createPasswordAccount(accounts,accountPasswords)){
                        clearFields();
                        Toast.makeText(AddPasswordPage.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(AddPasswordPage.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    private void getAccountID(){

        extras = getIntent().getExtras();
        if(extras != null){
            accountID = extras.getInt("ACCOUNT_ID");
        }

    }

    private void setStrings(){

        inputTitle = titleField.getEditText().getText().toString();
        inputAccount = accountField.getEditText().getText().toString();
        inputUsername = usernameField.getEditText().getText().toString();
        inputPassword = passwordField.getEditText().getText().toString();
        inputWebsite = websiteField.getEditText().getText().toString();
        inputNotes = notesField.getEditText().getText().toString();

    }

    private void initializeWidgets(){


        titleField = findViewById(R.id.inputTitleField);
        accountField = findViewById(R.id.inputAccountField);
        usernameField = findViewById(R.id.inputUsernameField);
        passwordField = findViewById(R.id.inputPasswordField);
        websiteField = findViewById(R.id.inputWebsiteField);
        notesField = findViewById(R.id.inputNotesField);
        addButton = findViewById(R.id.addPasswordButton);
        cancelButton = findViewById(R.id.cancelButton);

        generateButton = findViewById(R.id.generateButton);

    }

    private void clearFields(){
        titleField.getEditText().setText("");
        accountField.getEditText().setText("");
        usernameField.getEditText().setText("");
        passwordField.getEditText().setText("");
        websiteField.getEditText().setText("");
        notesField.getEditText().setText("");
    }

    private boolean validateTitle(){

        if(inputTitle.isEmpty()){
            titleField.setError(getString(R.string.fieldError));
            return false;
        }else{
            titleField.setError(null);
            titleField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername(){

        if(inputUsername.isEmpty()){
            usernameField.setError(getString(R.string.fieldError));
            return false;
        }else{
            usernameField.setError(null);
            usernameField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){

        if(inputPassword.isEmpty()){
            passwordField.setError(getString(R.string.fieldError));
            return false;
        }else{
            passwordField.setError(null);
            passwordField.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFields(){
        if(!validateTitle()){
            return false;
        }else{
            return true;
        }
    }



}
