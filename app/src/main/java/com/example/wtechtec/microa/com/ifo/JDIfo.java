package com.example.wtechtec.microa.com.ifo;

/**
 * Created by WtecHtec on 2018/1/7.
 */

public class JDIfo {
    private int id;
    private String d_id;
    private String stu_number;
    private String pho_number;
    private String type;
    private String zt;
    private String xxxm;
private  String jdtime;

    public String getXxxm() {
        return xxxm;
    }
    public void setXxxm(String xxxm) {
        this.xxxm = xxxm;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getD_id() {
        return d_id;
    }
    public void setD_id(String d_id) {
        this.d_id = d_id;
    }
    public String getStu_number() {
        return stu_number;
    }
    public void setStu_number(String stu_number) {
        this.stu_number = stu_number;
    }
    public String getPho_number() {
        return pho_number;
    }
    public void setPho_number(String pho_number) {
        this.pho_number = pho_number;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getZt() {
        return zt;
    }
    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getJdtime() {
        return jdtime;
    }

    public void setJdtime(String jdtime) {
        this.jdtime = jdtime;
    }

    public JDIfo(int id, String d_id, String stu_number, String pho_number, String type, String zt, String xxxm,String jdtime) {
        super();
        this.id = id;
        this.d_id = d_id;
        this.stu_number = stu_number;
        this.pho_number = pho_number;
        this.type = type;
        this.zt = zt;
        this.xxxm = xxxm;
        this.jdtime = jdtime;
    }


}
