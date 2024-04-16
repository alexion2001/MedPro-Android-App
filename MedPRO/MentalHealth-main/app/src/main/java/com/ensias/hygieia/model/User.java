package com.ensias.hygieia.model;

public class User {


    private String email;
    private String type;

    private String password;
    private String salt;

    public User(){
    }

    public User(String password, String salt, String email,String type) {

        this.salt = salt;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String password) {
        this.salt = salt;
    }

}
