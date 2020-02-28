package com.example.mobileprogfinalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Passwords> passwordsList;
    DatabaseHelper database;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        initializeWidgets();
    }



    private void initializeWidgets(){

        database = new DatabaseHelper(this);
        passwordsList = database.getPasswordAccounts();

        recyclerView = findViewById(R.id.listOfPasswords);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,passwordsList);
        recyclerView.setAdapter(adapter);

    }
}
