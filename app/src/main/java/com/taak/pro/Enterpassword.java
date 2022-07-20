package com.taak.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Enterpassword extends AppCompatActivity {
    EditText editText;
    Button btn;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterpassword);
        SharedPreferences settings = getSharedPreferences("hello", 0);
        password = settings.getString("password", "");

        editText = findViewById(R.id.edit1);
        btn = findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text=editText.getText().toString();
                if(text.equals(password)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"password worng",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}