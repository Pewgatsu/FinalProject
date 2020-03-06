package com.example.mobileprogfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.res.ComplexColorCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private ContentValues contentValues;
    private Cursor res;
    private SQLiteDatabase db;
    private Passwords passwords;

    private static final String PASSWORDS_TABLE = "Password_table";
    private static final String PASSWORDS_ID = "PASSWORD_ID";
    private static final String PASSWORDS_TITLE = "Title";
    private static final String PASSWORDS_ACCOUNT = "ACCOUNT";
    private static final String PASSWORDS_USERNAME = "USERNAME";
    private static final String PASSWORDS_PASSWORD = "PASSWORD";
    private static final String PASSWORDS_WEBSITE = "WEBSITE";
    private static final String PASSWORDS_NOTES = "NOTES";
    private static final String ACCOUNTS_ACCOUNT_ID = "ACCOUNT_ID";
    private static final String SQL_CREATE_PASSWORD_TABLE = "CREATE TABLE " + PASSWORDS_TABLE + " (PASSWORD_ID integer primary key autoincrement, TITLE TEXT, ACCOUNT TEXT not null, USERNAME TEXT not null," +
            " PASSWORD TEXT not null, WEBSITE TEXT not null, NOTES not null, ACCOUNT_ID integer, FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS_TABLE (ACCOUNT_ID))";



    private static final String DATABASE_NAME = "Accounts.db";
    private static final String ACCOUNTS_TABLE = "Accounts_table";
    private static final String ACCOUNTS_ID = "ACCOUNT_ID";
    private static final String ACCOUNTS_USERNAME = "ACCOUNT_USERNAME";
    private static final String ACCOUNTS_FULLNAME = "ACCOUNT_FULLNAME";
    private static final String ACCOUNTS_PASSWORD = "ACCOUNT_PASSWORD";
    private static final String ACCOUNTS_EMAIL = "ACCOUNT_EMAIL";
    private static final String SQL_CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS_TABLE + " (ACCOUNT_ID integer primary key autoincrement,ACCOUNT_USERNAME TEXT not null, ACCOUNT_FULLNAME TEXT NOT NULL," +
            " ACCOUNT_PASSWORD text not null, ACCOUNT_EMAIL text not null)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ";
    private static final String SQL_SELECT_TABLE = "SELECT * FROM ";



    private static final String SETTINGS_TABLE = "Settings_table";
    private static final String SQL_CREATE_SETTINGS_TABLE = "CREATE TABLE " + SETTINGS_TABLE + " (SETTINGS_ID integer primary key ,NOTIF_SETTINGS integer not null)";






    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQL_CREATE_ACCOUNTS_TABLE);
            db.execSQL(SQL_CREATE_PASSWORD_TABLE);
            db.execSQL(SQL_CREATE_SETTINGS_TABLE);
        }catch(Exception e){
            try{
                throw new IOException(e);
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES + ACCOUNTS_TABLE);
        db.execSQL(SQL_DELETE_ENTRIES + PASSWORDS_TABLE);
        db.execSQL(SQL_DELETE_ENTRIES + SETTINGS_TABLE);
        onCreate(db);
    }


    public Cursor getSettings(){
        db = this.getWritableDatabase();
        res = db.rawQuery("select NOTIF_SETTINGS FROM SETTINGS_TABLE",null);
        return res;
    }

    public void setSettings(boolean state){
        db = this.getWritableDatabase();

        if(state){
            contentValues = new ContentValues();
            contentValues.put("NOTIF_SETTINGS",1);

        }else{
            contentValues = new ContentValues();
            contentValues.put("NOTIF_SETTINGS",0);
        }
        db.insert("SETTINGS_TABLE",null,contentValues);
    }


    public Cursor getProfileDetails(){
        db = this.getWritableDatabase();
        res = db.rawQuery("select ACCOUNT_FULLNAME, ACCOUNT_USERNAME, ACCOUNT_EMAIL FROM "+ACCOUNTS_TABLE,null);
        return res;
    }


    public Passwords getPasswords(int id){
        db =this.getWritableDatabase();
        String[] query = {PASSWORDS_ID,  PASSWORDS_TITLE, PASSWORDS_ACCOUNT, PASSWORDS_USERNAME, PASSWORDS_PASSWORD, PASSWORDS_WEBSITE, PASSWORDS_NOTES, ACCOUNTS_ID};
        res = db.query(PASSWORDS_TABLE, query, PASSWORDS_ID+"=?",new String[]{String.valueOf(id)},null, null,null,null);
        if(res != null){
            res.moveToFirst();
        }


        return new Passwords(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getInt(7));
    }

    public int editPasswords(Passwords passwords){
            db = this.getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put(PASSWORDS_TITLE,passwords.getTitle());
            contentValues.put(PASSWORDS_ACCOUNT,passwords.getAccount());
            contentValues.put(PASSWORDS_USERNAME,passwords.getUsername());
            contentValues.put(PASSWORDS_PASSWORD,passwords.getPassword());
            contentValues.put(PASSWORDS_WEBSITE,passwords.getWebsite());
            contentValues.put(PASSWORDS_NOTES,passwords.getNotes());

            return db.update(PASSWORDS_TABLE,contentValues,PASSWORDS_ID+"=?",new String[]{String.valueOf(passwords.getID())});
    }

    public void deleteAccount(int id){
             db = this.getWritableDatabase();
             db.delete(ACCOUNTS_TABLE, ACCOUNTS_ID+"= ?", new String[]{String.valueOf(id)});
             db.close();
    }

    public void deletePassword(int id){
            db = this.getWritableDatabase();
            db.delete(PASSWORDS_TABLE,PASSWORDS_ID+"=?",new String[]{String.valueOf(id)});
            db.close();
    }



    public List<Passwords> getAllPasswords(int id){
        List<Passwords> passwordsList = new ArrayList<>();
        db = this.getWritableDatabase();
        res = db.rawQuery(SQL_SELECT_TABLE +PASSWORDS_TABLE+" where account_id = ? ", new String[]{String.valueOf(id)});
        //res = db.rawQuery(SQL_SELECT_TABLE + PASSWORDS_TABLE +"where"+ " ORDER BY "+PASSWORDS_ID,null);
        if(res.moveToFirst()){
            do{
                passwords = new Passwords();
                passwords.setID(res.getInt(0));
                passwords.setTitle(res.getString(1));
                passwords.setAccount(res.getString(2));
                passwords.setUsername(res.getString(3));
                passwords.setPassword(res.getString(4));
                passwords.setWebsite(res.getString(5));
                passwords.setNotes(res.getString(6));
                passwords.setAccount_ID(res.getInt(7));

                passwordsList.add(passwords);
            }while(res.moveToNext());
        }
        db.close();
        return passwordsList;
    }



    public boolean createPasswordAccount(Accounts account, Passwords accountPassword){
        try{
            db = this.getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put(PASSWORDS_TITLE,accountPassword.getTitle());
            contentValues.put(PASSWORDS_ACCOUNT,accountPassword.getAccount());
            contentValues.put(PASSWORDS_USERNAME,accountPassword.getUsername());
            contentValues.put(PASSWORDS_PASSWORD,accountPassword.getPassword());
            contentValues.put(PASSWORDS_WEBSITE,accountPassword.getWebsite());
            contentValues.put(PASSWORDS_NOTES,accountPassword.getNotes());
            contentValues.put(ACCOUNTS_ACCOUNT_ID,account.getID());
            long result = db.insert(PASSWORDS_TABLE,null,contentValues);
            if(result == -1){
                return false;
            }else{
                db.close();
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean createAccount(Accounts account){
        try{
            db = this.getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put(ACCOUNTS_USERNAME,account.getUsername());
            contentValues.put(ACCOUNTS_FULLNAME,account.getFullname());
            contentValues.put(ACCOUNTS_PASSWORD,account.getPassword());
            contentValues.put(ACCOUNTS_EMAIL,account.getEmail());

            long result = db.insert(ACCOUNTS_TABLE,null,contentValues);
            if(result == -1){
                return false;
            }else{
                db.close();
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmail(String email){
        db = this.getWritableDatabase();
        res = db.rawQuery("select * from Accounts_table where account_email=?", new String[]{email});
        if(res.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUsername(String username){
        db = this.getWritableDatabase();
        res = db.rawQuery("select * from Accounts_table where account_username = ?",new String[]{username});
        if(res.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public Accounts validateLogin(Accounts account){
        db = this.getWritableDatabase();
        res = db.rawQuery(SQL_SELECT_TABLE +ACCOUNTS_TABLE+" where account_username = ? and account_password = ?", new String[] {account.getUsername(), account.getPassword()});

        if(res != null && res.moveToFirst() && res.getCount() > 0){
            Accounts newAccount = new Accounts(res.getInt(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4));

            if(account.getPassword().equals(newAccount.getPassword())){
                return newAccount;
            }
        }
        return null;
    }


}

