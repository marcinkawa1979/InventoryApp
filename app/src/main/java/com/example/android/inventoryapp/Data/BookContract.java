package com.example.android.inventoryapp.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BookContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private BookContract(){}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.inventoryapp/books_inventory/ is a valid path for
     * looking at book data. content://com.example.android.inventoryapp/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_BOOKS = "books_inventory";

    /**
            * Inner class that defines constant values for the books database table.
            * Each entry in the table represents a single book.
     */
    public static final class BookEntry implements BaseColumns{

        /** The content URI to access the book data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of books.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single book.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;


        /** Name of database table for books*/
        public static final String TABLE_NAME = "books_inventory";

        /**
         * Unique ID number for the book (only for use in the database table).
         *
         * Type: INTEGER
         */
        public  static final String _ID = BaseColumns._ID;

        /**
         * Title of the book.
         *
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_TITLE = "Title";

        /**
         * Author name of the book.
         *
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_AUTHOR = "Author";

        /**
         * Price of the book.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_BOOK_PRICE = "Price" ;

        /**
         * Quantity of available books.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_BOOK_QUANTITY = "Quantity";

        /**
         * Supplier of the book.
         *
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_SUPPLIER = "Supplier";

        /**
         * Supplier telephone number.
         *
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_SUPPLIER_PHONE = "Supplier_phone_number";

    }

}
