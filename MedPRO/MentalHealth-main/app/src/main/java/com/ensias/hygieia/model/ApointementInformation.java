package com.ensias.hygieia.model;

public class ApointementInformation {
    private  String patientName,time,doctorId,doctorName,patientId,type,apointementType;
    private long slot;


    public String getApointementType() {
        return apointementType;
    }

    public void setApointementType(String apointementType) {
        this.apointementType = apointementType;
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

    public ApointementInformation(String patientName, String time, String doctorId, String doctorName, long slot) {
        this.patientName = patientName;
        this.time = time;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.slot = slot;
    }
    public ApointementInformation(String patientName, String time, String doctorId, String doctorName, String patientId, String type, String apointementType, long slot) {
        this.patientName = patientName;
        this.time = time;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.slot = slot;
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
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public long getSlot() {
        return slot;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }
}
