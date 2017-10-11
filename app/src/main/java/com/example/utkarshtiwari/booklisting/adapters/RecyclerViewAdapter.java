package com.example.utkarshtiwari.booklisting.adapters;

/**
 * Created by utkarshtiwari on 11/10/17.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utkarshtiwari.booklisting.R;
import com.example.utkarshtiwari.booklisting.models.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
                .load(items.get(position).getVolumeInfo().getImageLinks().getSmallThumbnail())
                .fit()
                .error(activity.getResources().getDrawable(R.drawable.image_not_found))
                .into(holder.bookImage);
        ;
        holder.bookName.setText(items.get(position).getVolumeInfo().getTitle());
        String authList = "";
        if (items.get(position).getVolumeInfo().getAuthors() != null) {
            for (String auth : items.get(position).getVolumeInfo().getAuthors()) {
                authList += auth + ", ";
            }
            holder.bookAuthor.setText(authList.substring(0, authList.length() - 2));
        } else {
            holder.bookAuthor.setText(activity.getResources().getString(R.string.not_mentioned));
        }

        if (items.get(position).getVolumeInfo().getDescription() != null) {
            String desc = items.get(position).getVolumeInfo().getDescription();
            holder.bookDescription.setText(String.format("%s.", desc.split("\\.")[0]));
        } else {
            holder.bookDescription.setText(activity.getResources().getString(R.string.no_desc));
        }
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
     *
     * @param newData list
     */
    public void updateAdapterData(ArrayList<Book> newData) {
        this.items = newData;
        notifyDataSetChanged();
    }
}