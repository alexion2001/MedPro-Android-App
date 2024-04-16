package com.ensias.hygieia.fireStoreApi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ensias.hygieia.model.UploadImage;

public class UploadImageHelper {
    private static DatabaseHelper databaseHelper;

    public static void init(DatabaseHelper dbHelper) {
        databaseHelper = dbHelper;
    }

    public static void addImage(UploadImage user) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", user.getuserName());
        values.put("imgURI", user.getImgURI());
        database.insert("uploadImage", null, values);
    }

    public static void updateImage(String imgURI, String email) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imgURI", imgURI);
        database.update("uploadImage", values, "userName = ?", new String[]{email});
    }

    public static UploadImage getImage(String email) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String[] columns = {"imgURI", "userName"};
        String selection = "userName = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("uploadImage", columns, selection, selectionArgs, null, null, null);
        UploadImage user = null;
        if (cursor.moveToFirst()) {
            String imgURI = cursor.getString(cursor.getColumnIndex("imgURI"));
            user = new UploadImage(email, imgURI);
        }
        cursor.close();
        return user;
    }
}
