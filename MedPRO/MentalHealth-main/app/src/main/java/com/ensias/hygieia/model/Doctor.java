package com.ensias.hygieia.model;

public class Doctor {
    private String name;
    private String address;

    private String birthday;
    private String tel;
    private String email;
    private String specialitate;

    public Doctor(){
        //needed for firebase
    }

    public Doctor(String name, String address, String tel, String email,String birthday, String specialitate) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.specialitate = specialitate;
        this.birthday = birthday;
    }
    public Doctor(String name, String address, String tel) {
        this.name = name;
        this.address = address;
        this.tel = tel;

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

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getSpecialitate() {
        return specialitate;
    }

    public void setSpecialitate(String specialitate) {
        this.specialitate = specialitate;
    }
}
