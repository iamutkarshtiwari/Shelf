package com.example.utkarshtiwari.booklisting.utils;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.utkarshtiwari.booklisting.models.Book;

public class BookLoader extends AsyncTaskLoader<List<Employee>> {
    
    public static final String dataSource;
    
    public BookLoader(Context context, String dataSource) {
		super(context);
        this.dataSource = dataSource;
    }

    @Override 
    public List<Book> loadInBackground() {
    	JSONParser jsonParser = new JSONParse(this.dataSource);
        return jsonParser.getResponseData();
    }
} 