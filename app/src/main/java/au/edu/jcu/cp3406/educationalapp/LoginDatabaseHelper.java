package au.edu.jcu.cp3406.educationalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LoginDatabaseHelper extends SQLiteOpenHelper {

    public LoginDatabaseHelper( Context context) {
        super(context, TABLE_NAME, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
