package com.ensias.hygieia.fireStoreApi;

import com.ensias.hygieia.model.User;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserHelper {
    private static SQLiteDatabase database;

    public static void init(SQLiteDatabase db) {
        database = db;
        //createTable();
    }

//    private static void createTable() {
//        String createQuery = "CREATE TABLE IF NOT EXISTS users ("
//                + "email TEXT PRIMARY KEY,"
//                + "password TEXT,"
//                + "salt TEXT,"
//                + "type TEXT"
//                + ");";
//        database.execSQL(createQuery);
//    }

    public static void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("salt", user.getSalt());
        values.put("type", user.getType());
        database.insert("users", null, values);
    }

    public static void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());
        values.put("salt", user.getSalt());
        values.put("type", user.getType());
        database.update("users", values, "email = ?", new String[]{user.getEmail()});
    }

    public static void updateUserType(String type, String email) {
        ContentValues values = new ContentValues();
        values.put("type", type);
        database.update("users", values, "email = ?", new String[]{email});
    }
    public static User getUser(String email) {
        String[] columns = {"email", "password", "salt", "type"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query("users", columns, selection, selectionArgs, null, null, null);
        User user = null;
        if (cursor.moveToFirst()) {
            String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String salt = cursor.getString(cursor.getColumnIndex("salt"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            user = new User(password, salt, userEmail, type);
        }
        cursor.close();
        return user;
    }
}


//public class UserHelper {
//    static FirebaseFirestore db = FirebaseFirestore.getInstance();
//    static CollectionReference UsersRef = db.collection("User");
//
//    public static void addUser(String name, String address, String tel,String type, String birthday){
//        User user = new User(name,address,tel,FirebaseAuth.getInstance().getCurrentUser().getEmail(),type, birthday);
//        UsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(user);
//
//    }
//}
