package com.example.treyn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View v){
        EditText email = (EditText)findViewById(R.id.emailEditText);
        EditText username = (EditText)findViewById(R.id.userEditText);
        EditText password = (EditText)findViewById(R.id.passEditText);

        String emailInput = email.getText().toString();
        String userInput = username.getText().toString();
        String passInput = password.getText().toString();

        User u = new User();
        u.setEmail(emailInput);
        u.setUsername(userInput);
        u.setPassword(passInput);

        helper.insertUser(u);

        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }
}
