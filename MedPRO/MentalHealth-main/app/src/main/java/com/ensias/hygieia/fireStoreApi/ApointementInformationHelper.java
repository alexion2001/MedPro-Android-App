package com.ensias.hygieia.fireStoreApi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ensias.hygieia.model.ApointementInformation;
import com.ensias.hygieia.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class ApointementInformationHelper {
    private static SQLiteDatabase database;

    public static void init(SQLiteDatabase db) {
        database = db;
    }


    public static void addAppointmentInformation(ApointementInformation user) {
        ContentValues values = new ContentValues();
        values.put("patientName", user.getPatientName());
        values.put("time", user.getTime());
        values.put("doctorId", user.getDoctorId());
        values.put("doctorName", user.getDoctorName());
        values.put("patientId", user.getDoctorId());
        values.put("type", user.getType());
        values.put("apointementType", user.getApointementType());
        values.put("slot", user.getSlot());

        database.insert("appointmentInformation", null, values);
    }

    public static void updateAppointmentInformation(ApointementInformation user) {
        ContentValues values = new ContentValues();
        values.put("patientName", user.getPatientName());
        values.put("time", user.getTime());
        values.put("doctorId", user.getDoctorId());
        values.put("doctorName", user.getDoctorName());
        values.put("patientId", user.getDoctorId());
        values.put("type", user.getType());
        values.put("apointementType", user.getApointementType());
        values.put("slot", user.getSlot());

        database.update("appointmentInformation", values, "doctorId = ?", new String[]{user.getDoctorId()});
    }

    public static List<ApointementInformation> getAppointmentInformation(String email) {
        String[] columns = {"patientName", "time", "doctorId", "doctorName", "patientId", "type", "apointementType", "slot"};
        String selection = "doctorId = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("appointmentInformation", columns, selection, selectionArgs, null, null, null);
        List<ApointementInformation> appointments = new ArrayList<>();

        while (cursor.moveToNext()) {
            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String apointementType = cursor.getString(cursor.getColumnIndex("apointementType"));
            long slot = cursor.getLong(cursor.getColumnIndex("slot"));
            ApointementInformation appointment = new ApointementInformation(patientName, time, doctorId, doctorName, patientId, type, apointementType, slot);
            appointments.add(appointment);
        }

        cursor.close();
        return appointments;
    }

    public static List<ApointementInformation> getConfirmedAppointmentInformation(String email) {
        String[] columns = {"patientName", "time", "doctorId", "doctorName", "patientId", "type", "apointementType", "slot"};
        String selection = "doctorId = ? AND type = :?";
        String[] selectionArgs = {email, "In asteptare"};
        Cursor cursor = database.query("appointmentInformation", columns, selection, selectionArgs, null, null, null);
        List<ApointementInformation> appointments = new ArrayList<>();

        while (cursor.moveToNext()) {
            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String apointementType = cursor.getString(cursor.getColumnIndex("apointementType"));
            long slot = cursor.getLong(cursor.getColumnIndex("slot"));
            ApointementInformation appointment = new ApointementInformation(patientName, time, doctorId, doctorName, patientId, type, apointementType, slot);
            appointments.add(appointment);
        }

        cursor.close();
        return appointments;
    }
    public static List<ApointementInformation> getPatientAppointmentInformation(String email) {
        String[] columns = {"patientName", "time", "doctorId", "doctorName", "patientId", "type", "apointementType", "slot"};
        String selection = "patientId = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("appointmentInformation", columns, selection, selectionArgs, null, null, null);
        List<ApointementInformation> appointments = new ArrayList<>();

        while (cursor.moveToNext()) {
            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String apointementType = cursor.getString(cursor.getColumnIndex("apointementType"));
            long slot = cursor.getLong(cursor.getColumnIndex("slot"));
            ApointementInformation appointment = new ApointementInformation(patientName, time, doctorId, doctorName, patientId, type, apointementType, slot);
            appointments.add(appointment);
        }

        cursor.close();
        return appointments;
    }

}