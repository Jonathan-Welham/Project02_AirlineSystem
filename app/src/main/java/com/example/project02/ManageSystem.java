package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.UserDao;

import java.util.List;

public class ManageSystem extends AppCompatActivity {

    UserDao mUserDao;
    List<User> users;


    EditText mUsernameEditText;
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mUsernameEditText = findViewById(R.id.usernameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        final Button acceptBtn = (Button) findViewById(R.id.acceptUsernameBtn);

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

        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(username.equals("admin2") && password.equals("admin2")){
            Toast.makeText(this, "Admin accepted", Toast.LENGTH_SHORT).show();
            mUserDao.insert(new User(username, password, true));
            Intent intent = new Intent(this, AdminPage.class);
            startActivity(intent);
            return;
        }
        else{
            Toast.makeText(this, "Incorrect login", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
