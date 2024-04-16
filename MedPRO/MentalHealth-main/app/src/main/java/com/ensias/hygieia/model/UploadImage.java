package com.ensias.hygieia.model;

public class UploadImage {
    private String userName;
    private String imgURI;
    public UploadImage(){
    }

    public UploadImage(String imgName, String imgURI) {
        this.userName = imgName;
        this.imgURI = imgURI;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getImgURI() {
        return imgURI;
    }

    public void setImgURI(String imgURI) {
        this.imgURI = imgURI;
    }
}



