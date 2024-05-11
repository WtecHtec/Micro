package com.example.wtechtec.microa.com.ifo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class DJkdIfo implements Parcelable {
    private String id,userid,jjrname, jjrphone,jjraddress,sjrname,sjrphone, sjraddress, qhaddress,bzly,xf,zt,xdsj;

    public DJkdIfo(String id, String userid, String jjrname, String jjrphone, String jjraddress, String sjrname,
                   String sjrphone, String sjraddress, String qhaddress, String bzly, String xf, String zt, String xdsj) {
        super();
        this.id = id;
        this.userid = userid;
        this.jjrname = jjrname;
        this.jjrphone = jjrphone;
        this.jjraddress = jjraddress;
        this.sjrname = sjrname;
        this.sjrphone = sjrphone;
        this.sjraddress = sjraddress;
        this.qhaddress = qhaddress;
        this.bzly = bzly;
        this.xf = xf;
        this.zt = zt;
        this.xdsj = xdsj;
    }

    protected DJkdIfo(Parcel in) {
        id = in.readString();
        userid = in.readString();
        jjrname = in.readString();
        jjrphone = in.readString();
        jjraddress = in.readString();
        sjrname = in.readString();
        sjrphone = in.readString();
        sjraddress = in.readString();
        qhaddress = in.readString();
        bzly = in.readString();
        xf = in.readString();
        zt = in.readString();
        xdsj = in.readString();
    }

    public static final Creator<DJkdIfo> CREATOR = new Creator<DJkdIfo>() {
        @Override
        public DJkdIfo createFromParcel(Parcel in) {
            return new DJkdIfo(in);
        }

        @Override
        public DJkdIfo[] newArray(int size) {
            return new DJkdIfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJjrname() {
        return jjrname;
    }

    public void setJjrname(String jjrname) {
        this.jjrname = jjrname;
    }

    public String getJjrphone() {
        return jjrphone;
    }

    public void setJjrphone(String jjrphone) {
        this.jjrphone = jjrphone;
    }

    public String getJjraddress() {
        return jjraddress;
    }

    public void setJjraddress(String jjraddress) {
        this.jjraddress = jjraddress;
    }

    public String getSjrname() {
        return sjrname;
    }

    public void setSjrname(String sjrname) {
        this.sjrname = sjrname;
    }

    public String getSjrphone() {
        return sjrphone;
    }

    public void setSjrphone(String sjrphone) {
        this.sjrphone = sjrphone;
    }

    public String getSjraddress() {
        return sjraddress;
    }

    public void setSjraddress(String sjraddress) {
        this.sjraddress = sjraddress;
    }

    public String getQhaddress() {
        return qhaddress;
    }

    public void setQhaddress(String qhaddress) {
        this.qhaddress = qhaddress;
    }

    public String getBzly() {
        return bzly;
    }

    public void setBzly(String bzly) {
        this.bzly = bzly;
    }

    public String getXf() {
        return xf;
    }

    public void setXf(String xf) {
        this.xf = xf;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXdsj() {
        return xdsj;
    }

    public void setXdsj(String xdsj) {
        this.xdsj = xdsj;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userid);
        dest.writeString(jjrname);
        dest.writeString(jjrphone);
        dest.writeString(jjraddress);
        dest.writeString(sjrname);
        dest.writeString(sjrphone);
        dest.writeString(sjraddress);
        dest.writeString(qhaddress);
        dest.writeString(bzly);
        dest.writeString(xf);
        dest.writeString(zt);
        dest.writeString(xdsj);
    }
}
