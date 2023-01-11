package com.example.healthcareapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername , edPassword;
    Button Loginbutton;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername  = findViewById(R.id.editTextUserName);
        edPassword  = findViewById(R.id.editTextpassword);
        Loginbutton= findViewById(R.id.loginbtn);
        tv  = findViewById(R.id.Register);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = edUsername.getText().toString();
                String Password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "BeaconInfo" , null ,1 );
                if (Username.length() == 0 || Password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Enter credentials properly", Toast.LENGTH_LONG).show();

                } else {
                    if(db.login(Username , Password)==1){
                        SharedPreferences sharedPreferences = getSharedPreferences("shares_prefrnces__" , Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username ",Username);
//                        editor.putString("Password",Password);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    }else{
                        Toast.makeText(getApplicationContext(), "Enter credentials properly", Toast.LENGTH_LONG).show();
                    }


                }
            }

        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}