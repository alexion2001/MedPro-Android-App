package com.ensias.hygieia.model;

public class Patient {
    private String name;
    private String address;
    private String tel;
    private String email;
    private String birthday;


    public Patient(){
        //needed for firebase
    }

    public Patient(String name, String address, String tel, String email, String birthday) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.birthday = birthday;
    }
    public Patient(String name,  String tel, String email, String birthday) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return address;
    }

    public void setAdresse(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String dateNaissance) {
        this.birthday = dateNaissance;
    }

}
