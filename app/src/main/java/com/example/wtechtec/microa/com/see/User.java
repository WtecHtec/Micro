package com.example.wtechtec.microa.com.see;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuetao on 2017/9/6.
 */

public class User implements Parcelable{
    private  String  name;
    private String sign;
    private String time;
 private  String itemid;

    public User(String title, String image, String context, String url,String itemid) {
        this.context = context;
        this.image = image;
        this.itemid = itemid;

        this.title = title;
        this.url = url;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User(String title, String image, String context, String url) {
        this.title = title;
        this.image = image;
        this.context = context;
        this.url = url;
    }

    private  String  title;
    private String image;
    private String context;
    private  String  url;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public User(String name, String msg) {
        this.name = name;
        this.sign = msg;
    }

    public User(String name, String sign, String time ) {
        this.time = time;
        this.sign = sign;
        this.name = name;
    }

    protected User(Parcel in) {
        name = in.readString();
        sign = in.readString();
        time=in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sign);
        dest.writeString(time);
    }
}
