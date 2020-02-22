package com.example.mobileprogfinalproject;

public class Passwords {

    private int id;
    private String title;
    private String account;
    private String username;
    private String password;
    private String website;
    private String notes;

    public Passwords(){
        this.title = null;
        this.username = null;
        this.password = null;
        this.website = null;
        this.notes = null;
    }

    public Passwords(int id){
        this.id = id;
    }

    public Passwords(int id, String account){
        this.id = id;
        this.account = account;
    }

    public Passwords(int id, String account, String title){
        this.id = id;
        this.title = title;
        this.account = account;
    }

    public Passwords(int id, String account, String title, String username){
        this.id = id;
        this.title = title;
        this.username = username;
    }

    public Passwords(int id, String account, String title, String username, String password){
        this.id = id;
        this.title = title;
        this.account = account;
        this.username = username;
        this.password = password;
    }

    public Passwords(int id, String account, String title, String username, String password, String website){
        this.id = id;
        this.title = title;
        this.account = account;
        this.username = username;
        this.password = password;
        this.website = website;
    }

    public Passwords(int id, String account, String title, String username, String password, String website, String notes){
        this.id = id;
        this.title = title;
        this.account = account;
        this.username = username;
        this.password = password;
        this.website = website;
        this.notes = notes;
    }

    //mutators
    public void setID(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    //accessors
    public int getID(){
        return id;
    }

    public String getAccount(){
        return account;
    }

    public String getTitle(){
        return title;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getWebsite() {
        return website;
    }

    public String getNotes(){
        return notes;
    }







}
