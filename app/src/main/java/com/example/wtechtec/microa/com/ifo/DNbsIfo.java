package com.example.wtechtec.microa.com.ifo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WtecHtec on 2018/1/7.
 */

public class DNbsIfo  implements Parcelable{

    private String id;
    private String userid;
    private String dnsw;
    private String bsaddress;
    private String bsphon;
    private String bssj;
    private String bsly ;
    private String xf;
    private String zt;
    private String xdsj;
    private String xx;

    protected DNbsIfo(Parcel in) {
        id = in.readString();
        userid = in.readString();
        dnsw = in.readString();
        bsaddress = in.readString();
        bsphon = in.readString();
        bssj = in.readString();
        bsly = in.readString();
        xf = in.readString();
        zt = in.readString();
        xdsj = in.readString();
        xx = in.readString();
    }

    public static final Creator<DNbsIfo> CREATOR = new Creator<DNbsIfo>() {
        @Override
        public DNbsIfo createFromParcel(Parcel in) {
            return new DNbsIfo(in);
        }

        @Override
        public DNbsIfo[] newArray(int size) {
            return new DNbsIfo[size];
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
    public String getDnsw() {
        return dnsw;
    }
    public void setDnsw(String dnsw) {
        this.dnsw = dnsw;
    }
    public String getBsaddress() {
        return bsaddress;
    }
    public void setBsaddress(String bsaddress) {
        this.bsaddress = bsaddress;
    }
    public String getBsphon() {
        return bsphon;
    }
    public void setBsphon(String bsphon) {
        this.bsphon = bsphon;
    }
    public String getBssj() {
        return bssj;
    }
    public void setBssj(String bssj) {
        this.bssj = bssj;
    }
    public String getBsly() {
        return bsly;
    }
    public void setBsly(String bsly) {
        this.bsly = bsly;
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
    public DNbsIfo(String id, String userid, String dnsw, String bsaddress, String bsphon, String bssj, String bsly,
                   String xf, String zt, String xdsj, String xx) {
        super();
        this.id = id;
        this.userid = userid;
        this.dnsw = dnsw;
        this.bsaddress = bsaddress;
        this.bsphon = bsphon;
        this.bssj = bssj;
        this.bsly = bsly;
        this.xf = xf;
        this.zt = zt;
        this.xdsj = xdsj;
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
        dest.writeString(dnsw);
        dest.writeString(bsaddress);
        dest.writeString(bsphon);
        dest.writeString(bssj);
        dest.writeString(bsly);
        dest.writeString(xf);
        dest.writeString(zt);
        dest.writeString(xdsj);
        dest.writeString(xx);
    }
}
