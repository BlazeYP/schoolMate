package com.example.pinkninja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class NewStudentEnrollment extends Activity implements AdapterView.OnItemSelectedListener {
    TextInputLayout student_name,father_name,mother_name;
    Spinner selectArea, selectSchool,selectPassedClass,selectEntryClass;
    RadioGroup gender;
    DatabaseHelper db = new DatabaseHelper(this);
    ArrayList<LocationBean> locationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment_form);
        Initialise();
    }

    private void Initialise() {
        student_name = findViewById(R.id.student_name);
        father_name = findViewById(R.id.father_name);
        mother_name = findViewById(R.id.mother_name);
        gender = findViewById(R.id.student_gender);
        selectArea =findViewById(R.id.school_city_area);
        selectPassedClass =findViewById(R.id.passed_class);
        selectEntryClass = findViewById(R.id.entry_class);

        // Setting up location dropdown
        locationList = db.getLocations();
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locationList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectArea.setAdapter(aa);
        selectArea.setOnItemSelectedListener(this);

        // Setting up School dropdown

        aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locationList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectArea.setAdapter(aa);
        selectArea.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.school_city_area:

                break;
            case R.id.passed_class:

                break;
            case R.id.entry_class:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()){
            case R.id.school_city_area:

                break;
            case R.id.passed_class:

                break;
            case R.id.entry_class:

                break;
        }
    }
}
