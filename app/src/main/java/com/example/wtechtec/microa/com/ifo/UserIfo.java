package com.example.wtechtec.microa.com.ifo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WtecHtec on 2018/1/2.
 */

public class UserIfo   implements Parcelable {
    private  String Nick;
    private String  Userid;
    private  String Password;
    private  String xx;
    private  String hm;

    public UserIfo( String userid, String password) {

        Userid = userid;
        Password = password;
    }
    public UserIfo(String nick, String userid, String password) {
        Nick = nick;
        Userid = userid;
        Password = password;
    }

    protected UserIfo(Parcel in) {
        Nick = in.readString();
        Userid = in.readString();
        Password = in.readString();
        xx=in.readString();
        hm=in.readString();
    }

    public static final Creator<UserIfo> CREATOR = new Creator<UserIfo>() {
        @Override
        public UserIfo createFromParcel(Parcel in) {
            return new UserIfo(in);
        }

        @Override
        public UserIfo[] newArray(int size) {
            return new UserIfo[size];
        }
    };

    public UserIfo(String xx, String userid, String hm, String password) {
        this.xx = xx;
        Userid = userid;
        this.hm = hm;
        Password = password;
    }

    public String getHm() {
        return hm;
    }

    public void setHm(String hm) {
        this.hm = hm;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nick);
        dest.writeString(Userid);
        dest.writeString(Password);
        dest.writeString(xx);
        dest.writeString(hm);
    }
}
