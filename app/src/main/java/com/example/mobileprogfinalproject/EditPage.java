package com.example.mobileprogfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class EditPage extends AppCompatActivity {

    private DatabaseHelper database;
    private Passwords accountPassword;
    private Intent intent;
    private TextInputLayout editTitle, editAccount, editUsername, editPassword, editWebsite, editNote;
    private String newTitle, newAccount, newUsername, newPassword, newWebsite, newNotes;
    private int id;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeWidgets();

        getAccountID();

        database = new DatabaseHelper(this);
        accountPassword = database.getPasswords(id);


        setStrings();

    }

    private boolean validateTitle(){
        String inputTitle = editTitle.getEditText().getText().toString();

        if(inputTitle.isEmpty()){
            editTitle.setError(getString(R.string.fieldError));
            return false;
        }else{
            editTitle.setError(null);
            editTitle.setErrorEnabled(false);
            return true;
        }
    }


    private void getStrings(){
        newTitle = editTitle.getEditText().getText().toString();
        newAccount = editAccount.getEditText().getText().toString();
        newUsername = editUsername.getEditText().getText().toString();
        newPassword = editPassword.getEditText().getText().toString();
        newWebsite = editWebsite.getEditText().getText().toString();
        newNotes = editNote.getEditText().getText().toString();
    }

    private void setNewDetails(){
        accountPassword.setTitle(newTitle);
        accountPassword.setAccount(newAccount);
        accountPassword.setUsername(newUsername);
        accountPassword.setPassword(newPassword);
        accountPassword.setWebsite(newWebsite);
        accountPassword.setNotes(newNotes);
    }


    private void getAccountID(){
        intent = getIntent();
        id = intent.getIntExtra("ID",0);
    }


    private void setStrings(){
        editTitle.getEditText().setText(accountPassword.getTitle());
        editAccount.getEditText().setText(accountPassword.getAccount());
        editUsername.getEditText().setText(accountPassword.getUsername());
        editPassword.getEditText().setText(accountPassword.getPassword());
        editWebsite.getEditText().setText(accountPassword.getWebsite());
        editNote.getEditText().setText(accountPassword.getNotes());
    }

    private void initializeWidgets(){
        editTitle = findViewById(R.id.editTitleField);
        editAccount = findViewById(R.id.editAccountField);
        editUsername = findViewById(R.id.editUsernameField);
        editPassword = findViewById(R.id.editPasswordField);
        editWebsite = findViewById(R.id.editWebsiteField);
        editNote = findViewById(R.id.editNotesField);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getItemId() == R.id.save) {
            if(validateTitle()){
                getStrings();
                setNewDetails();
                database.editPasswords(accountPassword);
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            }

        }else if(item.getItemId() == R.id.delete){
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }
}
