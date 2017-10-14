package com.example.utkarshtiwari.booklisting.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by utkarshtiwari on 10/10/17.
 */

public class Book implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    private String id;
    private Info volumeInfo;
    private SaleInfo saleInfo;

    protected Book(Parcel in) {
        id = in.readString();
        volumeInfo = (Info) in.readValue(Info.class.getClassLoader());
    }

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

    public void setSaleInfo(SaleInfo info) {
        this.saleInfo = info;
    }

    public SaleInfo getSaleInfo() {
        return this.saleInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeValue(volumeInfo);
    }

    public class Info {
        String title;
        List<String> authors;
        String description;
        ImageLinks imageLinks;
        String language;

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

        public void setLang(String lang) {
            this.language = lang;
        }

        public String getLang() {
            return this.language;
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

    public class SaleInfo {
        ListPrice listPrice;

        public void setListPrice(ListPrice lp) {
            this.listPrice = lp;
        }

        public ListPrice getListPrice() {
            return this.listPrice;
        }
    }

    public class ListPrice {
        float amount;
        String currencyCode;

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }
}