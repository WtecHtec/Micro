package com.example.wtechtec.microa.com.ifo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class DLkdIfo implements Parcelable  {

    private String id,userid,qhdz, qhh,shdz,shhm,shsj, bzly, xf,zt,xdsj;

    public DLkdIfo(String id, String userid, String qhdz, String qhh, String shdz, String shhm, String shsj, String bzly,
                   String xf, String zt,String xdsj) {

        this.id = id;
        this.userid = userid;
        this.qhdz = qhdz;
        this.qhh = qhh;
        this.shdz = shdz;
        this.shhm = shhm;
        this.shsj = shsj;
        this.bzly = bzly;
        this.xf = xf;
        this.zt = zt;
        this.xdsj = xdsj;
    }

    protected DLkdIfo(Parcel in) {
        id = in.readString();
        userid = in.readString();
        qhdz = in.readString();
        qhh = in.readString();
        shdz = in.readString();
        shhm = in.readString();
        shsj = in.readString();
        bzly = in.readString();
        xf = in.readString();
        zt = in.readString();
        xdsj = in.readString();
    }

    public static final Creator<DLkdIfo> CREATOR = new Creator<DLkdIfo>() {
        @Override
        public DLkdIfo createFromParcel(Parcel in) {
            return new DLkdIfo(in);
        }

        @Override
        public DLkdIfo[] newArray(int size) {
            return new DLkdIfo[size];
        }
    };

    public String getXdsj() {
        return xdsj;
    }

    public void setXdsj(String xdsj) {
        this.xdsj = xdsj;
    }

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

    public String getQhdz() {
        return qhdz;
    }

    public void setQhdz(String qhdz) {
        this.qhdz = qhdz;
    }

    public String getQhh() {
        return qhh;
    }

    public void setQhh(String qhh) {
        this.qhh = qhh;
    }

    public String getShdz() {
        return shdz;
    }

    public void setShdz(String shdz) {
        this.shdz = shdz;
    }

    public String getShhm() {
        return shhm;
    }

    public void setShhm(String shhm) {
        this.shhm = shhm;
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userid);
        dest.writeString(qhdz);
        dest.writeString(qhh);
        dest.writeString(shdz);
        dest.writeString(shhm);
        dest.writeString(shsj);
        dest.writeString(bzly);
        dest.writeString(xf);
        dest.writeString(zt);
        dest.writeString(xdsj);
    }
}
