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

    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Passwords> passwordsList;
    DatabaseHelper database;
    TextView displayText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    floatingActionButton = ((HomePage)getActivity()).getFloatingActionButton();
    displayText = getActivity().findViewById(R.id.noItemText);
    database = new DatabaseHelper(getActivity());
    passwordsList = database.getAllPasswords();
    recyclerView = getActivity().findViewById(R.id.passwordList);

    floatingActionButton.show();


        if(passwordsList.isEmpty()){
            displayText.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "WAHAHA", Toast.LENGTH_SHORT).show();
        }else{
            displayText.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "XD", Toast.LENGTH_SHORT).show();
            displayList(passwordsList);

        }


    }
    private void displayList(List<Passwords> passwordsList){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(getActivity(),passwordsList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();

       List<Passwords> passwordsList = database.getAllPasswords();
       if(passwordsList.isEmpty()){
           displayText.setVisibility(View.VISIBLE);
       }else{
           displayText.setVisibility(View.GONE);
           displayList(passwordsList);
        }

    }




}
