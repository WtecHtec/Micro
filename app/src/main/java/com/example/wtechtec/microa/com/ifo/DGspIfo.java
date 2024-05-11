package com.example.wtechtec.microa.com.ifo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WtecHtec on 2018/1/7.
 */

public class DGspIfo implements Parcelable {
    private String id,userid,spname, spnumber,shaddress,shphone,shsj, bzly, xf,zt,xdsj,xx;
    public DGspIfo(String id, String userid, String spname, String spnumber, String shaddress, String shphone,
                   String shsj, String bzly, String xf, String zt, String xdsj, String xx) {
        super();
        this.id = id;
        this.userid = userid;
        this.spname = spname;
        this.spnumber = spnumber;
        this.shaddress = shaddress;
        this.shphone = shphone;
        this.shsj = shsj;
        this.bzly = bzly;
        this.xf = xf;
        this.zt = zt;
        this.xdsj = xdsj;
        this.xx = xx;
    }

    protected DGspIfo(Parcel in) {
        id = in.readString();
        userid = in.readString();
        spname = in.readString();
        spnumber = in.readString();
        shaddress = in.readString();
        shphone = in.readString();
        shsj = in.readString();
        bzly = in.readString();
        xf = in.readString();
        zt = in.readString();
        xdsj = in.readString();
        xx = in.readString();
    }

    public static final Creator<DGspIfo> CREATOR = new Creator<DGspIfo>() {
        @Override
        public DGspIfo createFromParcel(Parcel in) {
            return new DGspIfo(in);
        }

        @Override
        public DGspIfo[] newArray(int size) {
            return new DGspIfo[size];
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

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public String getSpnumber() {
        return spnumber;
    }

    public void setSpnumber(String spnumber) {
        this.spnumber = spnumber;
    }

    public String getShaddress() {
        return shaddress;
    }

    public void setShaddress(String shaddress) {
        this.shaddress = shaddress;
    }

    public String getShphone() {
        return shphone;
    }

    public void setShphone(String shphone) {
        this.shphone = shphone;
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

    public String getXdsj() {
        return xdsj;
    }

    public void setXdsj(String xdsj) {
        this.xdsj = xdsj;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userid);
        dest.writeString(spname);
        dest.writeString(spnumber);
        dest.writeString(shaddress);
        dest.writeString(shphone);
        dest.writeString(shsj);
        dest.writeString(bzly);
        dest.writeString(xf);
        dest.writeString(zt);
        dest.writeString(xdsj);
        dest.writeString(xx);
    }
}
