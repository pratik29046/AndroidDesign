package com.taak.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Createpassword extends AppCompatActivity {
    EditText editText1, editText2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpassword);
        editText1 = findViewById(R.id.edit1);
        editText2 = findViewById(R.id.edit2);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                if (text1.equals("") || text2.equals("")) {
                    Toast.makeText(getApplicationContext(), "No password enter", Toast.LENGTH_SHORT).show();

                } else {
                    if (text1.equals(text2)) {
                        SharedPreferences settings = getSharedPreferences("hello", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password", text1);
                        editor.apply();


                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "password not match", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }
}