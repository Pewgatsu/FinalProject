package com.example.mobileprogfinalproject;

import android.os.Build;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordGenerator {


    private SecureRandom random;
    private final int PASSWORD_LENGTH = 15;
    private String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "!", "@", "#", "$", "%"};
    private String generatedPassword;
    private final String ALGORITHM = "SHA1PRNG";

    public String generatePassword(){


        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                random = SecureRandom.getInstanceStrong();
            }else{
                random = SecureRandom.getInstance(ALGORITHM);
            }
        }catch(NoSuchAlgorithmException e) {
            e.getMessage();
        }catch(Exception e){
            e.getMessage();
        }

        StringBuilder stringBuilder = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int indexRandom = random.nextInt(symbols.length);
            stringBuilder.append(symbols[indexRandom]);
        }
        return stringBuilder.toString();
    }
}
