package com.example.utkarshtiwari.booklisting.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utkarshtiwari on 10/10/17.
 */

public class Book {

    private String id;
    private Info volumeInfo;


    private  class Info {
        String title;
        List<String> authors;
        String description;
        ImageLinks imageLinks;
    }

    private class ImageLinks {
        String smallThumbnail;
        String thumbnail;
    }


}
