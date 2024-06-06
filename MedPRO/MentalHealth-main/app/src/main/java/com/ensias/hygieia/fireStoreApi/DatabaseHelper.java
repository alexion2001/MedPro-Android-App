package com.ensias.hygieia.fireStoreApi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedPro";
    private static final int DATABASE_VERSION = 3;  // Incremented version

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "onCreate called");

        String createQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "email TEXT PRIMARY KEY,"
                + "password TEXT,"
                + "salt TEXT,"
                + "type TEXT"
                + ");";
        db.execSQL(createQuery);

        String createPatientQuery = "CREATE TABLE IF NOT EXISTS patient ("
                + "email TEXT PRIMARY KEY,"
                + "name TEXT,"
                + "tel TEXT,"
                + "birthday TEXT,"
                + "address TEXT"
                + ");";
        db.execSQL(createPatientQuery);

        String createDoctorQuery = "CREATE TABLE IF NOT EXISTS doctor ("
                + "email TEXT PRIMARY KEY,"
                + "name TEXT,"
                + "tel TEXT,"
                + "birthday TEXT,"
                + "address TEXT,"
                + "specialitate TEXT"
                + ");";
        db.execSQL(createDoctorQuery);

        String createApointementInformationQuery = "CREATE TABLE IF NOT EXISTS appointmentInfo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "patientName TEXT,"
                + "date TEXT,"
                + "doctorId TEXT,"
                + "doctorName TEXT,"
                + "patientId TEXT,"
                + "status TEXT,"
                + "appointementType TEXT"
                + ");";
        db.execSQL(createApointementInformationQuery);

        String createImageQuery = "CREATE TABLE IF NOT EXISTS uploadImage ("
                + "userName TEXT PRIMARY KEY,"
                + "imgURI TEXT"
                + ");";
        db.execSQL(createImageQuery);

        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='uploadImage';";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            String createUploadImageQuery = "CREATE TABLE uploadImage ("
                    + "userName TEXT PRIMARY KEY,"
                    + "imgURI TEXT"
                    + ");";
            db.execSQL(createUploadImageQuery);
        }
        cursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS appointmentInfo");
            onCreate(db);
        }
    }

}
