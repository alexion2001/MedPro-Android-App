package com.ensias.hygieia.fireStoreApi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ensias.hygieia.model.Patient;

public class PatientHelper {
    private static SQLiteDatabase database;

    public static void init(SQLiteDatabase db) {
        database = db;
    }


    public static void addPatient(Patient user) {
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("name", user.getName());
        values.put("tel", user.getTel());
        values.put("birthday", user.getBirthday());
        database.insert("patient", null, values);
    }

    public static void updatePatient(Patient user) {
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("name", user.getName());
        values.put("tel", user.getTel());
        values.put("birthday", user.getBirthday());
        database.update("patient", values, "email = ?", new String[]{user.getEmail()});
    }

    public static Patient getPatient(String email) {
        String[] columns = {"email", "name", "tel", "birthday"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("patient", columns, selection, selectionArgs, null, null, null);
        Patient user = null;
        if (cursor.moveToFirst()) {
            String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));

            user = new Patient(userEmail, name,tel,  birthday);
        }
        cursor.close();
        return user;
    }

    public static Patient getMyPatients(String email) {
        String[] columns = {"email", "name", "tel", "birthday"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("patient", columns, selection, selectionArgs, null, null, null);
        Patient user = null;
        if (cursor.moveToFirst()) {
            String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));

            user = new Patient(name,  tel, userEmail, birthday);
        }
        cursor.close();
        return user;
    }
}
