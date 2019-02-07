package com.example.android.inventoryapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.Data.BookContract.*;

/**
 * {@link BookCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of book data as its data source. This adapter knows
 * how to create list items for each row of book data in the {@link Cursor}.
 */
public class BookCursorAdapter extends CursorAdapter {


    /**
     * Constructs a new {@link BookCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public BookCursorAdapter(Context context, Cursor c){
        super(context, c, 0 /* flags*/);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param viewGroup  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    /**
     * This method binds the book data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current book can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView tvTitle =  view.findViewById(R.id.title_text_view);
        TextView tvAuthor = view.findViewById(R.id.author_text_view);
        TextView tvPrice =  view.findViewById(R.id.price_text_view);
        final TextView tvQuantity =  view.findViewById(R.id.quantity_text_view);
        ImageButton ibSell =  view.findViewById(R.id.sold_button);

        // Extract properties from cursor
        final String title = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_BOOK_TITLE));
        final String author = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_BOOK_AUTHOR));
        final int intPrice = cursor.getInt(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_BOOK_PRICE));
        final int intQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_BOOK_QUANTITY));

        final String price = intPrice + " €";
        final String quantity = context.getString(R.string.show_quantity_prefix) + intQuantity;

        // Populate fields with extracted properties
        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvPrice.setText(price);
        tvQuantity.setText(quantity);

        final int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
        String currentQuantity = cursor.getString(quantityColumnIndex);
        final int quantityIntCurrent = Integer.valueOf(currentQuantity);

        final int productId = cursor.getInt(cursor.getColumnIndex(BookEntry._ID));

        //Sell button which decrease quantity in storage
        ibSell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (quantityIntCurrent > 0) {
                    int newQuantity = quantityIntCurrent - 1;
                    Uri quantityUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, productId);

                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_BOOK_TITLE, title);
                    values.put(BookEntry.COLUMN_BOOK_AUTHOR, author);
                    values.put(BookEntry.COLUMN_BOOK_PRICE, price);
                    values.put(BookEntry.COLUMN_BOOK_QUANTITY, newQuantity);

                    ContentResolver resolver = view.getContext().getContentResolver();
                    resolver.update(quantityUri, values, null, null);
                } else {
                    Toast.makeText(context, R.string.message_book_sold, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

