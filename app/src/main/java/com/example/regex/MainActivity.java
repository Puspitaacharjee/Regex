package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInputs();
            }
        });
    }

    // Validation logic
    private void validateInputs() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!isValidUsername(username)) {
            editTextUsername.setError("Invalid Username. Use only letters and numbers.");
            return;
        }

        if (!isValidEmail(email)) {
            editTextEmail.setError("Invalid Email Address");
            return;
        }

        if (!isValidPassword(password)) {
            editTextPassword.setError("Password must be at least 8 characters long, contain one number, one uppercase letter, and one special character.");
            return;
        }

        // If all validations pass
        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
    }

    // Username validation (Alphanumeric characters only)
    private boolean isValidUsername(String username) {
        String usernameRegex = "^[a-zA-Z0-9]{3,20}$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    // Email validation
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Password validation (At least 8 characters, one number, one uppercase, and one special character)
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
