package com.example.librarycard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartPage extends AppCompatActivity {

    private Button login,createAccount, forgotPassword;
    AlertDialog.Builder alertdiaglogbuilder;
    EditText emailEditText,passwordEditText;
    String emailText, passwordText;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        login = findViewById(R.id.logInButtonId);
        createAccount = findViewById(R.id.createAccountId);
        forgotPassword = findViewById(R.id.forgotPasswordId);

        emailEditText = findViewById(R.id.emailAddressId);
        passwordEditText = findViewById(R.id.passwordId);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailText = emailEditText.getText().toString();
                passwordText = passwordEditText.getText().toString();

                Boolean result = databaseHelper.matchPassword(emailText,passwordText);

                if(result == true){

                    SharedPreferences sharedPreferences2 = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    editor.putString("email",emailText);
                    editor.commit();

                    Intent intent = new Intent(StartPage.this,HomeNav.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Email and password does not match",Toast.LENGTH_SHORT).show();
                }

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartPage.this,CreateAccount.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartPage.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        alertdiaglogbuilder = new AlertDialog.Builder(StartPage.this);
        alertdiaglogbuilder.setTitle("Exit!");
        alertdiaglogbuilder.setIcon(R.drawable.exit);
        alertdiaglogbuilder.setMessage("Do you want to exit!");
        alertdiaglogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertdiaglogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertdiaglogbuilder.create();
        alertDialog.show();
    }
}
