package com.example.projectfoodrecipie;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button btnsign;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = (EditText) findViewById(R.id.username1);
        EditText password  = (EditText) findViewById(R.id.password1);
        Button btnsign = (Button)  findViewById(R.id.btnsign);
        DBHelper DB = new DBHelper(this);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter username/password", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser == true) {
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                        if (checkuserpass == true) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "The credentials are not correct", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "The account does not exists", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}