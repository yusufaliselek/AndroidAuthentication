package com.example.authenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextView createAccount;
    private EditText editTextEmail, editTextUserPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private String userEmail;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccount = findViewById(R.id.createAccount);
        editTextEmail = (EditText) findViewById(R.id.email_login);
        editTextUserPassword = (EditText) findViewById(R.id.password_login);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmail = editTextEmail.getText().toString();
                userPassword = editTextUserPassword.getText().toString();
                if(userEmail.isEmpty() || userPassword.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Lütfen gerekli alanları doldurunuz!",Toast.LENGTH_SHORT).show();

                }else{

                    loginFunc();
                }
            }
        });
    }

    public void hesapOlustur(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void loginFunc() {

        mAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(LoginActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            buttonLogin.setEnabled(false);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    public void resetPassword(View view) {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }
}