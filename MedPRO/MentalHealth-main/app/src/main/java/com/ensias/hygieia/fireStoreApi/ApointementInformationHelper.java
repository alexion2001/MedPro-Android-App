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
        values.put("date", user.getTime());
        values.put("doctorId", user.getDoctorId());
        values.put("doctorName", user.getDoctorName());
        values.put("patientId", user.getDoctorId());
        values.put("type", user.getType());
        values.put("apointementType", user.getApointementType());

        database.insert("appointmentInfo", null, values);
    }

    public static void updateAppType(String type, int id) {
        ContentValues values = new ContentValues();
        values.put("status", type);
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        database.update("appointmentInfo", values,selection, selectionArgs );
    }
    public static void updateAppointmentInformation(ApointementInformation user) {
        ContentValues values = new ContentValues();
        values.put("patientName", user.getPatientName());
        values.put("date", user.getTime());
        values.put("doctorId", user.getDoctorId());
        values.put("doctorName", user.getDoctorName());
        values.put("patientId", user.getDoctorId());
        values.put("status", user.getType());
        values.put("apointementType", user.getApointementType());

        database.update("appointmentInformation", values, "doctorId = ?", new String[]{user.getDoctorId()});
    }

    public static List<ApointementInformation> getAppointmentInformation(String email) {
        String[] columns = {"id", "patientName", "date", "doctorId", "doctorName", "patientId", "status", "appointementType"};
        String selection = "doctorId = ? AND status = \"In_Asteptare\"";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("appointmentInfo", columns, selection, selectionArgs, null, null, null);
        List<ApointementInformation> appointments = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
            String type = cursor.getString(cursor.getColumnIndex("status"));
            String appointementType = cursor.getString(cursor.getColumnIndex("appointementType"));
            ApointementInformation appointment = new ApointementInformation(id, patientName, date, doctorId, doctorName, patientId, type, appointementType);
            appointments.add(appointment);
        }

        cursor.close();
        return appointments;
    }

    public static List<ApointementInformation> getAcceptedAppointmentInformation(String email) {
        String[] columns = {"id", "patientName", "date", "doctorId", "doctorName", "patientId", "status", "appointementType"};
        String selection = "doctorId = ? AND status = \"Acceptat\"";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("appointmentInfo", columns, selection, selectionArgs, null, null, null);
        List<ApointementInformation> appointments = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
            String type = cursor.getString(cursor.getColumnIndex("status"));
            String appointementType = cursor.getString(cursor.getColumnIndex("appointementType"));
            ApointementInformation appointment = new ApointementInformation(id, patientName, date, doctorId, doctorName, patientId, type, appointementType);
            appointments.add(appointment);
        }

        cursor.close();
        return appointments;
    }

    public static List<ApointementInformation> getConfirmedAppointmentInformation(String email) {
        String[] columns = {"patientName", "date", "doctorId", "doctorName", "patientId", "status", "appointementType"};
        String selection = "doctorId = ? AND status = \"Acceptat\")";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("appointmentInformation", columns, selection, selectionArgs, null, null, null);
        List<ApointementInformation> appointments = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
            String type = cursor.getString(cursor.getColumnIndex("status"));
            String appointementType = cursor.getString(cursor.getColumnIndex("appointementType"));
            ApointementInformation appointment = new ApointementInformation(id, patientName, date, doctorId, doctorName, patientId, type, appointementType);
            appointments.add(appointment);
        }
        cursor.close();
        return appointments;
    }

//
//    public static List<ApointementInformation> getAppointmentAcceptedInformation(String email) {
//        String[] columns = {"patientName", "date", "doctorId", "doctorName", "patientId", "status", "appointementType"};
//        String selection = "doctorId = ? AND status = \"In_Asteptare\"";
//        String[] selectionArgs = {email};
//        Cursor cursor = database.query("appointmentInfo", columns, selection, selectionArgs, null, null, null);
//        List<ApointementInformation> appointments = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
//            String date = cursor.getString(cursor.getColumnIndex("date"));
//            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
//            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
//            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
//            String type = cursor.getString(cursor.getColumnIndex("status"));
//            String appointementType = cursor.getString(cursor.getColumnIndex("appointementType"));
//            ApointementInformation appointment = new ApointementInformation(patientName, date, doctorId, doctorName, patientId, type, appointementType);
//            appointments.add(appointment);
//        }
//
//        cursor.close();
//        return appointments;
//    }

//    public static List<ApointementInformation> getPatientAppointmentInformation(String email) {
//        String[] columns = {"patientName", "time", "doctorId", "doctorName", "patientId", "type", "apointementType", "slot"};
//        String selection = "patientId = ?";
//        String[] selectionArgs = {email};
//        Cursor cursor = database.query("appointmentInformation", columns, selection, selectionArgs, null, null, null);
//        List<ApointementInformation> appointments = new ArrayList<>();
//
//        while (cursor.moveToNext()) {
//            String patientName = cursor.getString(cursor.getColumnIndex("patientName"));
//            String date = cursor.getString(cursor.getColumnIndex("time"));
//            String doctorId = cursor.getString(cursor.getColumnIndex("doctorId"));
//            String doctorName = cursor.getString(cursor.getColumnIndex("doctorName"));
//            String patientId = cursor.getString(cursor.getColumnIndex("patientId"));
//            String type = cursor.getString(cursor.getColumnIndex("type"));
//            String apointementType = cursor.getString(cursor.getColumnIndex("apointementType"));
//            ApointementInformation appointment = new ApointementInformation(patientName, date, doctorId, doctorName, patientId, type, apointementType);
//            appointments.add(appointment);
//        }

//        cursor.close();
//        return appointments;
//    }

}