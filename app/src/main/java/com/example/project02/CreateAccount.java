package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.UserDao;

import java.util.List;

public class CreateAccount extends AppCompatActivity {

    UserDao mUserDao; 
    List<User> users;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    int numTries = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mUsernameEditText = (EditText) findViewById(R.id.usernameEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);

        mUserDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();
    }

    public void accept(View view){
        users = mUserDao.getUsers();
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        Boolean numAlphabets = false;
        Boolean oneNumber = false;
        int threeLetters = 0;

        boolean passNumAlphabets = false;
        boolean passOneNumber = false;
        int passThreeLetters = 0;

        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!users.isEmpty()) {
            for(User u: users){
                if(u.getUsername().equals(username)){
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        for (int i = 0; i < username.length(); i++) {
            char n = username.charAt(i);
            if (Character.isDigit(n)) {
                oneNumber = true;
            }
            if (Character.isAlphabetic(n)) {
                threeLetters++;
            }
            if (threeLetters >= 3) {
                numAlphabets = true;
            }
        }

        for (int i = 0; i < password.length(); i++) {
            char n = password.charAt(i);
            if (Character.isDigit(n)) {
                passOneNumber = true;
            }
            if (Character.isAlphabetic(n)) {
                passThreeLetters++;
            }
            if (passThreeLetters >= 3) {
                passNumAlphabets = true;
            }
        }

        if (threeLetters < 3 || passThreeLetters < 3) {
            Toast.makeText(this, "Username/password needs at least three alphabetical letters", Toast.LENGTH_SHORT).show();
            numTries--;
            if(numTries <= 0){
                Toast.makeText(this, "Out of tries", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (!oneNumber || !passOneNumber) {
            Toast.makeText(this, "Username/password needs at least one number", Toast.LENGTH_SHORT).show();
            numTries--;
            if(numTries <= 0){
                Toast.makeText(this, "Out of tries", Toast.LENGTH_SHORT).show();
                //TODO: out of tries, force quit
            }
            return;
        }
        if (username.equals("admin2")) {
            Toast.makeText(this, "Username 'admin2' is reserved", Toast.LENGTH_SHORT).show();
            numTries--;
            if(numTries <= 0){
                Toast.makeText(this, "Out of tries", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (numAlphabets && oneNumber && passNumAlphabets && passOneNumber) {
            Toast.makeText(this, "Username accepted", Toast.LENGTH_SHORT).show();
            mUserDao.insert(new User(username, password, false));
            return;
        }
    }
}
