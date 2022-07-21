package com.taak.pro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class Enterpassword extends AppCompatActivity {
    EditText editText;
    Button btn;
    String password;

    PasscodeView passcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterpassword);
        btn = findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgets();
            }
        });

//        editText = findViewById(R.id.edit1);

//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text=editText.getText().toString();
//                if(text.equals(password)){
//                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(getApplicationContext(),"password worng",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


        passcodeView = findViewById(R.id.passcodeview);
        SharedPreferences settings = getSharedPreferences("hello", 0);
        password = settings.getString("password", "");
        // to set length of password as here
        // we have set the length as 5 digits
        passcodeView.setPasscodeLength(password.length())
                // to set pincode or passcode
                .setLocalPasscode(password)

                // to set listener to it to check whether
                // passwords has matched or failed
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        // to show message when Password is incorrect
                        Toast.makeText(getApplicationContext(), "Password is wrong!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        // here is used so that when password
                        // is correct user will be
                        // directly navigated to next activity
                        Intent intent_passcode = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent_passcode);
                    }
                });

    }

    public void forgets(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(Enterpassword.this);
        View mView = getLayoutInflater().inflate(R.layout.forgetpassowrd, null);
        Button btn_cancel = (Button) mView.findViewById(R.id.btn2);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER_HORIZONTAL;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}