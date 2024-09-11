package com.example.app_thue_xe_cnpmnc.Xe;

public class Xe {


    public int getIdxe() {
        return idxe;
    }

    public void setIdxe(int idxe) {
        this.idxe = idxe;
    }

    public int getIdloaixe() {
        return idloaixe;
    }

    public void setIdloaixe(int idloaixe) {
        this.idloaixe = idloaixe;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public String getAnhxe() {
        return anhxe;
    }

    public void setAnhxe(String anhxe) {
        this.anhxe = anhxe;
    }

    public Xe(int idloaixe, String trangthai, int giatien, String anhxe, int idxe,String tenxe) {
        this.idloaixe = idloaixe;
        this.trangthai = trangthai;
        this.giatien = giatien;
        this.anhxe = anhxe;
        this.idxe = idxe;
        this.tenxe = tenxe;
    }

    public int idloaixe;
    public String trangthai;
    public int giatien;
public String anhxe;
    public int idxe;

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public String tenxe;
}
