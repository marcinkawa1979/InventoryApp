package com.example.android.inventoryapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.android.inventoryapp.Data.BookContract.*;

public class BookDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = BookDbHelper.class.getSimpleName();

    //Name of the database file
    public static final String DATABASE_NAME = "books.db";

    //Version of database
    public static final int DATABASE_VERSION = 1;


    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /** Create a String that contains the SQL statement to create the books table
         * _ID, Title, Author,
         */
         String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BookEntry.TABLE_NAME
                + "(" + BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +","
                + BookEntry.COLUMN_BOOK_TITLE + " TEXT NOT NULL" + ","
                + BookEntry.COLUMN_BOOK_AUTHOR + " TEXT NOT NULL" +","
                + BookEntry.COLUMN_BOOK_PRICE + " INTEGER NOT NULL"+ ","
                + BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL DEFAULT 0" +","
                + BookEntry.COLUMN_BOOK_SUPPLIER + " TEXT" + ","
                + BookEntry.COLUMN_BOOK_SUPPLIER_PHONE + " TEXT" + ")";

         db.execSQL(SQL_CREATE_BOOKS_TABLE);

        Log.d(LOG_TAG, "Database creating...");
        Log.d(LOG_TAG, "Table " + DATABASE_NAME + " ver." + DATABASE_VERSION + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
}
