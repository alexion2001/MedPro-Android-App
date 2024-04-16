package com.ensias.hygieia.fireStoreApi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ensias.hygieia.model.Doctor;

public class DoctorHelper {
    private static SQLiteDatabase database;

    public static void init(SQLiteDatabase db) {
        database = db;
    }



    public static void addDoctor(Doctor user) {
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("name", user.getName());
        values.put("tel", user.getTel());
        values.put("address", user.getAdresse());
        values.put("birthday", user.getBirthday());
        values.put("specialitate", user.getSpecialitate());

        database.insert("doctor", null, values);
    }

    public static void updateDoctor(Doctor user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("tel", user.getTel());
        values.put("address", user.getAdresse());

        database.update("doctor", values, "email = ?", new String[]{user.getEmail()});
    }

    public static Doctor getDoctor(String email) {
        String[] columns = {"email", "name", "tel", "address", "birthday", "specialitate"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("doctor", columns, selection, selectionArgs, null, null, null);
        Doctor user = null;
        if (cursor.moveToFirst()) {
            String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String specialitate = cursor.getString(cursor.getColumnIndex("specialitate"));

            user = new Doctor(name, address, tel, userEmail, birthday, specialitate);
        }
        cursor.close();
        return user;
    }
}

//public class DoctorHelper {
//    static FirebaseFirestore db = FirebaseFirestore.getInstance();
//    static CollectionReference DoctorRef = db.collection("Doctor");
//
//    public static void addDoctor(String name, String birthday, String tel,String specialitate){
//        Doctor doctor = new Doctor(name,"address",tel, FirebaseAuth.getInstance().getCurrentUser().getEmail(), birthday, specialitate);
//
//        DoctorRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(doctor);
//
//    }
//}
