package com.example.utkarshtiwari.booklisting.adapters;

/**
 * Created by utkarshtiwari on 11/10/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utkarshtiwari.booklisting.R;


public class BookViewHolder extends RecyclerView.ViewHolder {

    public ImageView bookImage;
    public TextView bookName, bookAuthor, bookDescription;

    public BookViewHolder(View itemView) {
        super(itemView);

        bookImage = (ImageView) itemView.findViewById(R.id.imageView);
        bookName = (TextView) itemView.findViewById(R.id.name);
        bookAuthor = (TextView) itemView.findViewById(R.id.author);
        bookDescription = (TextView) itemView.findViewById(R.id.desc);
    }
}
