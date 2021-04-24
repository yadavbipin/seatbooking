package com.at.seatbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        TextView error = findViewById(R.id.textView2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etName.getText().toString();
                String password = etPassword.getText().toString();
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                //String savedPassword = preferences.getString(password, "");
                //String savedUserName = preferences.getString(user, "");

                String userDetails = preferences.getString(user + password + "data","No information on that user.");
                Log.d("user details",userDetails);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display",userDetails);
                editor.commit();
                if(userDetails.equals("No information on that user.")){
                    error.setTextColor(Color.RED);
                    error.setText("You are not registered! please register!");
                    etName.setText("");
                    etPassword.setText("");
                }else {
                    Intent displayScreen = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(displayScreen);
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setText("");
                Intent registerScreen = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerScreen);

            }
        });
    }


}