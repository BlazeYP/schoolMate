package com.example.pinkninja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RoleDialogOpener.RoleDialogListner {

    private static EditText username, password;
    private static TextView resetPassword, signUpText;
    private Button signIn;
    DatabaseHelper customerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialise();
        createTextLink();
        customerDB = new DatabaseHelper(this);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, passcode;
                name = username.getText().toString();
                passcode = password.getText().toString();
                CustomerCreateAccountBean customerBean = new CustomerCreateAccountBean(name,passcode,null);
                if(customerDB.isUserPresent(customerBean)){
                    Toast.makeText(getApplicationContext(),"Logged In successfully!",Toast.LENGTH_LONG).show();
                    RoleDialogOpener roleDialogOpener = new RoleDialogOpener();
                    roleDialogOpener.show(getSupportFragmentManager(),"Role Assignment");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect credentials !",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createTextLink() {
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }

            @Override
            public void onClick(@NonNull View s) {
                switch (s.getId()) {
                    case R.id.resetPassword:
                        Intent openActivity = new Intent("com.example.pinkninja.RESETPASSWORD");
                        startActivity(openActivity);
                        break;
                    case R.id.signUpText:
                        openActivity = new Intent("com.example.pinkninja.CREATEACCOUNT");
                        startActivity(openActivity);
                        break;

                }
            }

        };
        SpannableString str = new SpannableString(resetPassword.getText());
        str.setSpan(span1, 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        resetPassword.setText(str);
        resetPassword.setMovementMethod(LinkMovementMethod.getInstance());
        str = new SpannableString(signUpText.getText());
        str.setSpan(span1, 18, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpText.setText(str);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void Initialise() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        resetPassword = findViewById(R.id.resetPassword);
        signUpText = findViewById(R.id.signUpText);
    }

    @Override
    public void getSelectedRole(String role) {
        switch (role){
            case "Student":
                StudentStatusDialogOpener studentStatusDialogOpener = new StudentStatusDialogOpener();
                studentStatusDialogOpener.show(getSupportFragmentManager(),null);
                break;
            case "School Owner":
                Intent openActivity = new Intent("com.example.pinkninja.SCHOOLREGISTRATION");
                startActivity(openActivity);
                break;
            case "Parent":
                openActivity = new Intent ("com.example.pinkninja.PARENTPORTAL");
                startActivity(openActivity);
                break;
        }
    }
}