package com.example.utkarshtiwari.booklisting.models;

import java.util.List;

/**
 * Created by utkarshtiwari on 10/10/17.
 */

public class Book {

    private String id;
    private Info volumeInfo;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Info getVolumeInfo() {
        return this.volumeInfo;
    }

    public void setVolumeInfo(Info volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public class Info {
        String title;
        List<String> authors;
        String description;
        ImageLinks imageLinks;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getAuthors() {
            return this.authors;
        }

        public void setAuthors(List<String> authors) {
            this.authors = authors;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String desc) {
            this.description = desc;
        }

        public ImageLinks getImageLinks() {
            return this.imageLinks;
        }

        public void setImageLinks(ImageLinks imageLinks) {
            this.imageLinks = imageLinks;
        }

    }

    public class ImageLinks {
        String smallThumbnail;
        String thumbnail;

        public String getSmallThumbnail() {
            return this.smallThumbnail;
        }

        public void setSmallThumbnail(String smallThumbnail) {
            this.smallThumbnail = smallThumbnail;
        }

        public String getThumbnail() {
            return this.thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }


}
