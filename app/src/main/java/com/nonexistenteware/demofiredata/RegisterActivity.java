package com.nonexistenteware.demofiredata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button createBtn, backBtn;
    EditText neweMail, newPass;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        createBtn = (Button) findViewById(R.id.btn_reg);
        backBtn = (Button) findViewById(R.id.btn_back);
        neweMail = (EditText) findViewById(R.id.new_user_mail);
        newPass = (EditText) findViewById(R.id.new_user_pass);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = neweMail.getText().toString();
                String pass = newPass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your mail address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;

                }

                auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    //error
                                } if (pass.length() < 6) {
                                    Toast.makeText(getApplicationContext(), "Password should be more than 6 digit", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent usrCreate = new Intent(RegisterActivity.this, DashboardActivity.class);
                                    startActivity(usrCreate);
                                    finish();
                                }
                            }
                        });
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backInt = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(backInt);
            }
        });
    }
}
