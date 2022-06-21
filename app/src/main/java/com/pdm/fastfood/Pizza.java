package com.pdm.fastfood;

import android.os.Parcel;
import android.os.Parcelable;

public class Pizza implements Parcelable {
    private int type;
    private int srcImg;
    private int price;
    public Pizza(int type, int srcImg, int price) {
        this.type = type;
        this.srcImg = srcImg;
        this.price = price;
    }

    protected Pizza(Parcel in) {
        type = in.readInt();
        srcImg = in.readInt();
        price = in.readInt();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSrcImg() {
        return this.srcImg;
    }

    public void setSrcImg(int srcImg) {
        this.srcImg = srcImg;
    }

    public int getPrice() {return this.price;}

    public void setPrice(int price) {this.price = price;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeInt(srcImg);
        parcel.writeInt(price);
    }
}