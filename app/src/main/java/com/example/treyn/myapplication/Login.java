package com.example.treyn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void registerAccount(View v){
        Intent i;
        i = new Intent(Login.this, Register.class);
        Login.this.startActivity(i);
    }

    public void logIn(View v){
        EditText user = (EditText)findViewById(R.id.userEditText);
        String uname = user.getText().toString();
        EditText pass = (EditText)findViewById(R.id.passEditText);
        String pword = pass.getText().toString();

        String password = helper.searchPass(uname);
        if(pword.equals(password)){
            Intent i = new Intent(Login.this, HomeScreen.class);
            startActivity(i);
        }else{
            Toast fail = Toast.makeText(Login.this, "Wrong Username or Password!", Toast.LENGTH_SHORT);
            fail.show();
        }
    }
}
