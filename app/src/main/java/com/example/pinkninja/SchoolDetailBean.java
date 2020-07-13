package com.example.pinkninja;

public class SchoolDetailBean {
    private String schoolDecode,city,classes, affiliation,medium;
    private int pinCode,schoolCode,numberOfSection;

    public SchoolDetailBean() {
    }

    public SchoolDetailBean(String schoolDecode, String city, String classes, String affiliation,
                            int numberOfSection, String medium, int pinCode, int schoolCode) {
        this.schoolDecode = schoolDecode;
        this.city = city;
        this.classes = classes;
        this.affiliation = affiliation;
        this.numberOfSection = numberOfSection;
        this.medium = medium;
        this.pinCode = pinCode;
        this.schoolCode = schoolCode;
    }

    public String getSchoolDecode() {
        return schoolDecode;
    }

    public void setSchoolDecode(String schoolDecode) {
        this.schoolDecode = schoolDecode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public int getNumberOfSection() {
        return numberOfSection;
    }

    public void setNumberOfSection(int numberOfSection) {
        this.numberOfSection = numberOfSection;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public int getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(int schoolCode) {
        this.schoolCode = schoolCode;
    }
}
