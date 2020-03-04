package com.example.mobileprogfinalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import com.google.android.material.textfield.TextInputLayout;

public class DetailsPage extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseHelper database;
    private Passwords passwords;
    private Intent intent;
    private int id;
    private TextInputLayout titleField, accountField, usernameField, passwordField, websiteField, notesField;
    private FloatingActionButton fab;
    private AlertDialog.Builder confirmDeleteBuilder;
    private AlertDialog confirmDelete;



    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.app_bar_details);

        initializeWidgets();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getAccountID();

        database = new DatabaseHelper(this);
        passwords = database.getPasswords(id);
        getSupportActionBar().setTitle(passwords.getTitle());
        setStrings();
        disableFields();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();

            }
        });
    }



    private void getAccountID(){
        intent = getIntent();
        id = intent.getIntExtra("ID",0);
    }


    private void disableFields(){
        titleField.getEditText().setKeyListener(null);
        accountField.getEditText().setKeyListener(null);
        usernameField.getEditText().setKeyListener(null);
        passwordField.getEditText().setKeyListener(null);
        websiteField.getEditText().setKeyListener(null);
        notesField.getEditText().setKeyListener(null);

    }

    private void setStrings(){
        titleField.getEditText().setText(passwords.getTitle());
        accountField.getEditText().setText(passwords.getAccount());
        usernameField.getEditText().setText(passwords.getUsername());
        passwordField.getEditText().setText(passwords.getPassword());
        websiteField.getEditText().setText(passwords.getWebsite());
        notesField.getEditText().setText(passwords.getNotes());
    }

    private void initializeWidgets(){


        toolbar = findViewById(R.id.detailsToolbar);
        fab = findViewById(R.id.detailsFab);

        titleField = findViewById(R.id.detailsTitleField);
        accountField = findViewById(R.id.detailsAccountField);
        usernameField = findViewById(R.id.detailsUsernameField);
        passwordField = findViewById(R.id.detailsPasswordField);
        websiteField = findViewById(R.id.detailsWebsiteField);
        notesField = findViewById(R.id.detailsNotesField);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit){
            intent = new Intent(this, EditPage.class);
            intent.putExtra("ID",id);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void goToMain(){
        intent = new Intent(getApplicationContext(),HomePage.class);
        startActivity(intent);
        finish();
    }

    private void delete(){
        confirmDeleteBuilder = new AlertDialog.Builder(this);
        confirmDeleteBuilder.setMessage("Are you sure you want to delete this note?");


        confirmDeleteBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        confirmDeleteBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.deletePassword(id);

                goToMain();
            }
        });

        confirmDelete = confirmDeleteBuilder.create();
        confirmDelete.show();


    }
}

