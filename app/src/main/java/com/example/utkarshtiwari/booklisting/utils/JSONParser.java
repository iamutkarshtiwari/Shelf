package com.example.utkarshtiwari.booklisting.utils;

/**
 * Created by utkarshtiwari on 10/10/17.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;mport org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class JSONParser {

    private String dataSource;

    public JSONParser(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Loads JSON from the asset file
     *
     * @param dataSource name of the asset file
     * @return JSON string
     */
    public String loadJSONFromAsset(String dataSource) {
        String json = null;
        try {
            InputStream is = HomeActivity.getContext().getAssets().open(dataSource);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Parses the JSON string and generates list of products
     *
     * @return ArrayList of Products
     */
    public ArrayList<Product> getResponseData() {
        ArrayList<Product> itemList = new ArrayList<Product>();
        String responseJSON = loadJSONFromAsset(this.dataSource);

        // Return empty list if null response
        if (responseJSON == null) {
            return itemList;
        }

        try {
            JSONObject jsonObject = new JSONObject(responseJSON);

            // Return empty list if response result is not "ok"
            if (!jsonObject.getString("result").equalsIgnoreCase("ok")) {
                return itemList;
            }

            JSONArray jsonArray = jsonObject.getJSONArray("data");

            // Generate product arraylist from json data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productObject = jsonArray.getJSONObject(i);

                String id = productObject.getString("id");
                String productName = productObject.getString("name");
                String isSoldOut = productObject.getString("status");
                long likesCount = productObject.getLong("num_likes");
                long commentsCount = productObject.getLong("num_comments");
                long price = productObject.getLong("price");
                String photoURL = productObject.getString("photo");

                Product product = new Product(id, productName, isSoldOut, likesCount, commentsCount, price, photoURL);
                itemList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
