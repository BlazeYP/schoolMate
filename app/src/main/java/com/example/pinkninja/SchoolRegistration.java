package com.example.pinkninja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;

public class SchoolRegistration extends Activity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    TextInputLayout schoolName,city,pinCode,numberOfSection;
    Spinner classesUpto,affiliation,medium;
    Button next;
    SchoolDetailBean schoolDetailBean = new SchoolDetailBean();
    String errorMsg = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Initialise();
    }

    private void Initialise() {
        setContentView(R.layout.school_registration_form);
        schoolName = findViewById(R.id.school_name);
        city = findViewById(R.id.school_city);
        pinCode = findViewById(R.id.pincode);
        classesUpto = findViewById(R.id.class_upto);
        affiliation = findViewById(R.id.affiliation);
        medium = findViewById(R.id.medium);
        numberOfSection = findViewById(R.id.number_of_sec);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        //For Spinner items
        classesUpto.setOnItemSelectedListener(this);
        affiliation.setOnItemSelectedListener(this);
        medium.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                if(validateFields()) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(this);
                    if (!databaseHelper.isSchoolPresent(schoolDetailBean)) {
                        if (databaseHelper.addSchool(schoolDetailBean)) {
                            if (!databaseHelper.isLocationPresent(schoolDetailBean))
                                if (databaseHelper.addLocation(schoolDetailBean.getPinCode(),
                                        schoolDetailBean.getCity())) {
                                    Toast.makeText(this, "Location registered successfully", Toast.LENGTH_SHORT).show();
                                }
                            Toast.makeText(this, "School registered successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Error occurred in registering", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this, "School was already registered", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private boolean validateFields() {
        boolean x1,x2,x3,x4,x5,x6,x7;
        x1 = validateSchoolName();
        x2 = validateCity();
        x3 = validatePincode();
        x4 = validateClassesField();
        x5 = validateSectionField();
        x6 = validateAffliationField();
        x7 = validateMediumField();
        if(!errorMsg.isEmpty()) {
            Toast.makeText(SchoolRegistration.this, errorMsg, Toast.LENGTH_LONG).show();
            errorMsg = "";
        }
        return (x1 && x2 && x3 && x4 && x5 && x6 && x7);
    }

    private boolean validateMediumField() {
        if(schoolDetailBean.getMedium().equalsIgnoreCase("Please Select")){
            errorMsg +="\n Medium field is mandatory!";
            return false;
        }
        return true;
    }

    private boolean validateAffliationField() {
        if(schoolDetailBean.getAffiliation().equalsIgnoreCase("Please Select")){
            errorMsg +="\n Affiliation field is mandatory!";
            return false;
        }
        return true;
    }

    private boolean validateSectionField() {
        try{
            int section = Integer.parseInt(numberOfSection.getEditText().getText().toString().trim());
            if(section < 0) {
                numberOfSection.setError("Number of section can't be negative");
                return false;
            }
            else if(section == 0) {
                numberOfSection.setError("Number of section can't be 0");
                return false;
            }
            else {
                numberOfSection.setError(null);
                schoolDetailBean.setNumberOfSection(section);
                return true;
            }
        }catch(NumberFormatException e){
            numberOfSection.setError("Field is mandatory!");
            return false;
        }catch (Exception e){
            numberOfSection.setError("Field is mandatory!");
            return false;
        }
    }

    private boolean validateClassesField() {
        if(schoolDetailBean.getClasses().equalsIgnoreCase("Please Select")){
            errorMsg +="\n Classes field is mandatory!";
            return false;
        }
        return true;
    }

    private boolean validatePincode() {
        String pincCodeInput = pinCode.getEditText().getText().toString().trim();
        if(pincCodeInput.isEmpty()){
            pinCode.setError("Field is mandatory");
            return false;
        }
        else {
            pinCode.setError(null);
            try {
                schoolDetailBean.setPinCode(Integer.parseInt(pincCodeInput));
            }catch (NumberFormatException e){
                Toast.makeText(this,"Parsing error",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

    private boolean validateCity() {
        String cityInput = city.getEditText().getText().toString().trim();
        if(cityInput.isEmpty()){
            city.setError("Field is mandatory");
            return false;
        }
        else {
            city.setError(null);
            schoolDetailBean.setCity(cityInput);
            return true;
        }
    }

    private boolean validateSchoolName() {
        String schoolNameInput = schoolName.getEditText().getText().toString().trim();
        if(schoolNameInput.isEmpty()){
            schoolName.setError("Field is mandatory");
            return false;
        }
        else {
            schoolName.setError(null);
            schoolDetailBean.setSchoolDecode(schoolNameInput);
            return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.class_upto:
                schoolDetailBean.setClasses(parent.getItemAtPosition(position).toString());
                break;
            case R.id.affiliation:
                schoolDetailBean.setAffiliation(parent.getItemAtPosition(position).toString());
                break;
            case R.id.medium:
                schoolDetailBean.setMedium(parent.getItemAtPosition(position).toString());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
