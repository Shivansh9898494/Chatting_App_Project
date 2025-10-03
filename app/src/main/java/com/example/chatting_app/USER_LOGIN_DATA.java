package com.example.chatting_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Manages a local SQLite database to store the current user's login session.
 * This ensures the user stays logged in even after closing the app.
 */
public class USER_LOGIN_DATA extends SQLiteOpenHelper {

    private static final String TAG = "UserLoginDB"; // For logging
    private static final String DATABASE_NAME = "UserSession.db";
    private static final int DATABASE_VERSION = 2; // Incremented version due to schema change
    private static final String TABLE_NAME = "LOGIN_DATA";

    // --- Column Definitions ---
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USERNAME = "USERNAME";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String COLUMN_DOCUMENT_ID = "DOCUMENT_ID";
    private static final String COLUMN_LOGIN_STATUS = "LOGIN_STATUS";
    private static final String COLUMN_AVATAR_INDEX = "AVATAR_INDEX";

    public USER_LOGIN_DATA(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Corrected SQL query that includes ALL the defined columns.
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_DOCUMENT_ID + " TEXT, " +
                COLUMN_AVATAR_INDEX + " INTEGER, " +
                COLUMN_LOGIN_STATUS + " INTEGER DEFAULT 0)"; // Use INTEGER for boolean (0=false, 1=true)

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This will delete the old table and create a new one on version change.
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean saveLoginData(String username, String password, String documentId, int avatarIndex, boolean status) {
        SQLiteDatabase db = this.getWritableDatabase();

        // 1. Clear any existing login data to ensure only one record exists.
        db.delete(TABLE_NAME, null, null);
        Log.d(TAG, "Cleared old session data.");

        // 2. Insert the new user's data.
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_DOCUMENT_ID, documentId);
        values.put(COLUMN_AVATAR_INDEX, avatarIndex);// Convert boolean to INTEGER
        values.put(COLUMN_LOGIN_STATUS, status); // Mark user as logged in

        long result = db.insert(TABLE_NAME, null, values);



        db.close();

        if (result == -1) {
            Log.e(TAG, "bhosdi Failed to save login data for user: " + username);
            return false;
        } else {
            Log.d(TAG, "bhosdi Successfully saved login data for user: " + username);
            return true;
        }
    }




    public ContactModel getLoggedInUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_LOGIN_STATUS + " = ?", new String[]{"1"}, null, null, null, "1");

        ContactModel loggedInUser = null;

        if (cursor != null && cursor.moveToFirst()) {
            int usernameColIndex = cursor.getColumnIndex(COLUMN_USERNAME);
            int passwordColIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
            int docIdColIndex = cursor.getColumnIndex(COLUMN_DOCUMENT_ID);
            int avatarIndexColIndex = cursor.getColumnIndex(COLUMN_AVATAR_INDEX);
            int loginStatusColIndex = cursor.getColumnIndex(COLUMN_LOGIN_STATUS);


            String username = cursor.getString(usernameColIndex);
            String password = cursor.getString(passwordColIndex);
            String docId = cursor.getString(docIdColIndex);
            int avatarIndex = cursor.getInt(avatarIndexColIndex);
            Boolean loggedIn = cursor.getInt(loginStatusColIndex) == 1;

            loggedInUser = new ContactModel(username, password,avatarIndex, docId, loggedIn);
            Log.d(TAG, "Found logged in user: " + username);
            cursor.close();
        } else {
            Log.d(TAG, "No logged in user found in the database.");
        }

        db.close();
        return loggedInUser;
    }



}
