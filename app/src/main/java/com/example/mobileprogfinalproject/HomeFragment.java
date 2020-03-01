package com.example.mobileprogfinalproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {


    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Passwords> passwordsList;
    private DatabaseHelper database;
    private TextView displayText;
    private int accountID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    initializeWidgets();


    database = new DatabaseHelper(getActivity());
    passwordsList = database.getAllPasswords(accountID);



    floatingActionButton.show();


        if(passwordsList.isEmpty()){
            displayText.setVisibility(View.VISIBLE);
        }else{
            displayText.setVisibility(View.GONE);
            displayList(passwordsList);

        }


    }

    private void initializeWidgets(){
        accountID = ((HomePage)getActivity()).retrieveAccountID();
        displayText = getActivity().findViewById(R.id.noItemText);
        recyclerView = getActivity().findViewById(R.id.passwordList);
        floatingActionButton = ((HomePage)getActivity()).getFloatingActionButton();
    }

    private void displayList(List<Passwords> passwordsList){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(getActivity(),passwordsList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();

       List<Passwords> passwordsList = database.getAllPasswords(accountID);
       if(passwordsList.isEmpty()){
           displayText.setVisibility(View.VISIBLE);
       }else{
           displayText.setVisibility(View.GONE);
           displayList(passwordsList);
        }

    }

}
