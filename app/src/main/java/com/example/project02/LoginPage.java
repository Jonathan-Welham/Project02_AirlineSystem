package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.UserDao;

import java.util.List;

public class LoginPage extends AppCompatActivity {

    UserDao mUserDao;
    List<User> users;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mUsernameEditText = (EditText) findViewById(R.id.usernameEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);

        mUserDao = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDAO();

    }

    // button to log in
    public void accept(View view){

        users = mUserDao.getUsers();
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(users.isEmpty()){
            Toast.makeText(this, "No accounts logged, try creating an account", Toast.LENGTH_SHORT).show();
            return;
        }


        try {
            for(User user : users){
                user.setLoggedIn(false);
                mUserDao.update(user);
                if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                    Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT).show();
                    user.setLoggedIn(true);
                    mUserDao.update(user);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "No account with that username/password", Toast.LENGTH_SHORT).show();
        }
    }
}
