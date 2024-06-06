package com.ensias.hygieia.model;

public class Patient {
    private String name;
    private String tel;
    private String email;
    private String birthday;


    public Patient(){
    }

    public Patient(String email, String name, String tel,  String birthday) {
        this.name = name;
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
