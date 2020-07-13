package com.example.pinkninja;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ExistingStudent extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll_detail_form);
    }
}
