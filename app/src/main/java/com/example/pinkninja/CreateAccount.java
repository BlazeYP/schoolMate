package com.example.pinkninja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

public class CreateAccount extends Activity implements View.OnClickListener {

    TextInputLayout username, password, email, confirmPassword;
    Button signUp;
    String emailInput, usernameInput, passwordInput, cnfmPasswordInput;
    DatabaseHelper customerDB;
    CustomerCreateAccountBean customerCreateBean = new CustomerCreateAccountBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intialise();
        customerDB = new DatabaseHelper(this);
    }

    private void intialise() {
        setContentView(R.layout.create_account);

        //variable initialization
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signUpBtn) {
            if (validatefields()) {
                customerCreateBean.setUserName(usernameInput);
                customerCreateBean.setEmail(emailInput);
                customerCreateBean.setPassword(passwordInput);
                if(!customerDB.isUserPresent(customerCreateBean)) {
                    if(customerDB.addUser(customerCreateBean)){
                        Toast.makeText(this,"New User added successfully",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this,"You have already registered",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean validatefields() {
        boolean x,y,z;
        x = validateEmail();
        y = validateUserName();
        z = validatePassword();
        return (x && y && z);
    }

    private boolean validatePassword() {
        passwordInput = password.getEditText().getText().toString().trim();
        cnfmPasswordInput = confirmPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field is mandatory");
        }
        if (cnfmPasswordInput.isEmpty()) {
            confirmPassword.setError("Field is mandatory");
        }
        if (passwordInput.isEmpty() || cnfmPasswordInput.isEmpty()) {
            return false;
        } else if (passwordInput.equals(cnfmPasswordInput)) {
            password.setError(null);
            confirmPassword.setError(null);
            return true;
        } else {
            password.setError("Password fields mismatch");
            confirmPassword.setError("Password fields mismatch");
            return false;
        }
    }

    private boolean validateUserName() {
        usernameInput = username.getEditText().getText().toString().trim();
        if(usernameInput.isEmpty()){
            username.setError("Field is mandatory");
            return false;
        }
        else if(usernameInput.length()> 20){
            username.setError("Username to long");
            return false;
        }
        else {
            username.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
            emailInput = email.getEditText().getText().toString().trim();
            if(emailInput.isEmpty()){
                email.setError("Field is mandatory");
                return false;
            }
            else {
                email.setError(null);
                return true;
            }
    }
}
