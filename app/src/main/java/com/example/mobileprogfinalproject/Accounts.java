package com.example.mobileprogfinalproject;

public class Accounts {

    private int id;
    private String username;
    private String password;
    private String email;


    //constructors
    public Accounts(){
        id = 0;
        username = null;
        password = null;
        email = null;
    }

    public Accounts(int id){
        this.id = id;
    }

    public Accounts(int id, String username){
        this.id = id;
        this.username = username;
    }

    public Accounts(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Accounts(int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    // mutators

    public void setID(int id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //accessors

    public int getID(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }




}
