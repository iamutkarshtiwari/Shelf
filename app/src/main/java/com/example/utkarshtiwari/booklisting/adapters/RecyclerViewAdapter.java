package com.example.utkarshtiwari.booklisting.adapters;

/**
 * Created by utkarshtiwari on 11/10/17.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utkarshtiwari.booklisting.models.Book;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.utkarshtiwari.booklisting.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<BookViewHolder> {

    ArrayList<Book> items;
    Activity activity;

    public RecyclerViewAdapter(Activity activity, ArrayList<Book> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, final int position) {

        // Downloads the product image from url
        Picasso.with(activity)
                .load(items.get(position).getPhotoURL())
                .fit()
                .error(activity.getResources().getDrawable(R.drawable.image_not_found))
                .into(holder.bookImage);
;
        holder.bookName.setText(activity.getResources().getString(R.string.product_price, "$", price));
        holder.bookAuthor.setText(items.get(position).getName());
//        String description
        holder.bookDescription.setText(String.format("%s", items.get(position).getLikesCount()));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Updates the adapter item list with new list
     * @param newData list
     */
    public void updateAdapterData(ArrayList<Book> newData) {
        this.items = newData;
         notifyDataSetChanged();
    }
}