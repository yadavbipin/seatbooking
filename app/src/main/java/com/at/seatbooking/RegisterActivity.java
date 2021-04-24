package com.at.seatbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText userName = (EditText) findViewById(R.id.etNewName);
        final EditText password = (EditText) findViewById(R.id.etNewPassword);
        final EditText email = (EditText) findViewById(R.id.etNewEmail);
        Button btnRegister = (Button) findViewById(R.id.btnNewRegister);
        TextView success = (TextView)findViewById(R.id.textView);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
                String newUser  = userName.getText().toString();
                String newPassword = password.getText().toString();
                String newEmail = email.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                //stores 3 new instances of sharedprefs. Both the user and password's keys are the same as the input.
                //Must be done this way because sharedprefs is stupid and inefficient. You cannot store Arrays easily
                //so I use strings instead.
                editor.putString(newUser,newUser);
                editor.commit();
                editor.putString(newPassword, newPassword);
                editor.commit();
                editor.putString(newUser + newPassword + "data", newUser + "\n" + newEmail);
                editor.commit();
                success.setTextColor(Color.GREEN);
                success.setText("You are registered successfully");
                Intent displayScreen = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(displayScreen);
            }
        });

    }
}
