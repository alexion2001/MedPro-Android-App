package com.ensias.hygieia.model;

public class ApointementInformation {
    private int id;
    private  String patientName,date,doctorId,doctorName,patientId,type,apointementType;


    public String getApointementType() {
        return apointementType;
    }

    public void setApointementType(String apointementType) {
        this.apointementType = apointementType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public ApointementInformation(){
    }

    public ApointementInformation(String patientName, String time, String doctorId, String doctorName) {
        this.patientName = patientName;
        this.date = time;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }
    public ApointementInformation(int id, String patientName, String time, String doctorId, String doctorName, String patientId, String type, String apointementType) {
        this.id = id;
        this.patientName = patientName;
        this.date = time;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.apointementType = apointementType;
        this.patientId = patientId;
        this.type = type;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTime() {
        return date;
    }

    public void setTime(String time) {
        this.date = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
