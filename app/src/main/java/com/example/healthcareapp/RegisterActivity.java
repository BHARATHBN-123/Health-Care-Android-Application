package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername , edPassword,edConfirmPassword , email;
    Button Signupbutton;
    TextView tv;
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextRegUserName);
        email = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegpassword);
        edConfirmPassword = findViewById(R.id.editTextRegConfirmpassword);
        tv = findViewById(R.id.Signin);
        Signupbutton = findViewById(R.id.Signupbtn);
        Signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = edUsername.getText().toString();
                String  Email = email.getText().toString();
                String Password = edPassword.getText().toString();
                String ConfirmPassword = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "BeaconInfo" , null ,1 );
                if(Username.length()==0 || Email.length()==0 || Password.length()==0 || ConfirmPassword.length()==0 ){
                    Toast.makeText(getApplicationContext(), "Fill all the credentials", Toast.LENGTH_LONG).show();
                } else if(!Password.equals(ConfirmPassword)){
                    Toast.makeText(getApplicationContext(), "Password doesnot match", Toast.LENGTH_LONG).show();
                }else if (!PasswordValidation(Password)){
                    Toast.makeText(RegisterActivity.this, "Password is weak", Toast.LENGTH_SHORT).show();

                }
                else{
                    db.register(Username,Password,Email);
                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
                }

            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
            }
        });

    }
    public  static  boolean PasswordValidation(String Password){
//        boolean pass = false;
        return Password.matches(PASSWORD_PATTERN);
    }
}