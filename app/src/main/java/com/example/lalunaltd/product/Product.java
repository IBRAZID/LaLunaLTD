package com.example.lalunaltd.product;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private String name;
    private String description;
    private String category;
    private String image;

    public Product() {
    }

    public Product(String name, String description, String category, String image) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.image = image;
    }


    protected Product(Parcel in) {
        name = in.readString();
        description = in.readString();
        category = in.readString();
        image = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeString(image);
    }

}
