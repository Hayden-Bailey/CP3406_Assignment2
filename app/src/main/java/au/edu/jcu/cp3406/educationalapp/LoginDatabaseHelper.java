package au.edu.jcu.cp3406.educationalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class LoginDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = LoginDatabaseHelper.class.getSimpleName();

    private static final String TABLE_NAME = "loginDetails";
    private static final String COL0 = "id";
    private static final String COL1 = "email";
    private static final String COL2 = "password";


    public LoginDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT NOT NULL, " +
                COL2 + " TEXT NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addLoginDetails(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, email);
        contentValues.put(COL2, password);

//        Log.i(TAG, "addLoginDetails adding: email - " + email + " password - " + password + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;

    }

    public boolean checkEmails(String email) {
        boolean emailExists = false;
        String dbEmail;
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            dbEmail = cursor.getString(1);
            if (dbEmail.equals(email)) {
                emailExists = true;
                break;
            }
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return emailExists;
    }

    public boolean checkPassword(String email, String inputPassword) {
        String dbPassword = null;
        boolean passwordsMatch = false;
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor.getString(1).equals(email)) {
                dbPassword = cursor.getString(2);
                break;
            }
        }
        cursor.close();
        db.close();

        assert dbPassword != null;
        passwordsMatch = dbPassword.equals(inputPassword);
        return passwordsMatch;
    }

}
