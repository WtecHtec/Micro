package com.example.wtechtec.microa.com.ifo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jayce on 2018/1/7.
 */

public class JZgcIfo implements Parcelable {
    private String id;
    private String jzName;
    private String daiyu;
    private String gxTime;
    private String zpNumber;
    private String endtime;
    private String workstation;
    private  String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected JZgcIfo(Parcel in) {
        id = in.readString();
        jzName = in.readString();
        daiyu = in.readString();
        gxTime = in.readString();
        zpNumber = in.readString();
        endtime = in.readString();
        workstation = in.readString();
        phoneNumber=in.readString();
    }

    public static final Creator<JZgcIfo> CREATOR = new Creator<JZgcIfo>() {
        @Override
        public JZgcIfo createFromParcel(Parcel in) {
            return new JZgcIfo(in);
        }

        @Override
        public JZgcIfo[] newArray(int size) {
            return new JZgcIfo[size];
        }
    };

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJzName() {
        return jzName;
    }

    public void setJzName(String jzName) {
        this.jzName = jzName;
    }

    public String getDaiyu() {
        return daiyu;
    }

    public void setDaiyu(String daiyu) {
        this.daiyu = daiyu;
    }

    public String getGxTime() {
        return gxTime;
    }

    public void setGxTime(String gxTime) {
        this.gxTime = gxTime;
    }

    public String getZpNumber() {
        return zpNumber;
    }

    public void setZpNumber(String zpNumber) {
        this.zpNumber = zpNumber;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public JZgcIfo(String id, String jzName, String daiyu, String gxTime, String zpNumber, String endtime, String workstation,String phoneNumber) {
        this.id = id;
        this.jzName = jzName;
        this.daiyu = daiyu;
        this.gxTime = gxTime;
        this.zpNumber = zpNumber;
        this.endtime = endtime;
        this.workstation = workstation;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(jzName);
        dest.writeString(daiyu);
        dest.writeString(gxTime);
        dest.writeString(zpNumber);
        dest.writeString(endtime);
        dest.writeString(workstation);
        dest.writeString( phoneNumber);
    }
}
