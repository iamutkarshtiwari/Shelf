package com.example.utkarshtiwari.booklisting.utils;

/**
 * Created by utkarshtiwari on 10/10/17.
 */

import com.example.utkarshtiwari.booklisting.models.Book;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class JSONParser {

    private String dataSource;

    public JSONParser(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Parses the JSON string and generates list of products
     *
     * @return ArrayList of Products
     */
    public ArrayList<Book> getResponseData() {
        ArrayList<Book> itemList = new ArrayList<Book>();
        String responseJSON = HttpGetRequest.getResponseString(this.dataSource);
        Gson gson = new Gson();

        // Return empty list if null response
        if (responseJSON == null) {
            return itemList;
        }

        try {
            JSONObject jsonObject = new JSONObject(responseJSON);

            if (jsonObject.getJSONArray("items").length() == 0) {
                return itemList;
            }

            JSONArray jsonArray = jsonObject.getJSONArray("items");

            // Generate product arraylist from json data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject bookObject = jsonArray.getJSONObject(i);
                Book book= gson.fromJson(bookObject.toString(), Book.class);
                itemList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Book>();
        }
        return itemList;
    }


}
