package com.example.medtermexam;

public class StudentArchive {
    private String id;
    private String lastname, firstname, mi, course, year, gender;
    private  String[] docsSubmit;

    public StudentArchive(String id, String lastname, String firstname, String mi, String course, String year, String gender, String[] docsSubmit) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.mi = mi;
        this.course = course;
        this.year = year;
        this.gender = gender;
        this.docsSubmit = docsSubmit;
    }

    public String getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMi() {
        return mi;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getGender() {
        return gender;
    }

    public String[] getDocsSubmit() {
        return docsSubmit;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDocsSubmit(String[] docsSubmit) {
        this.docsSubmit = docsSubmit;
    }
}
