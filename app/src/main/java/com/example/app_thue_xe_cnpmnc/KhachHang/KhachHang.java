package com.example.app_thue_xe_cnpmnc.KhachHang;

public class KhachHang {


    public KhachHang(int idkhachhang, String hoten, String cnnd, String blx, String coquan, String email) {
        this.idkhachhang = idkhachhang;
        this.hoten = hoten;
        this.cnnd = cnnd;
        this.blx = blx;
        this.coquan = coquan;
        this.email = email;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getCnnd() {
        return cnnd;
    }

    public void setCnnd(String cnnd) {
        this.cnnd = cnnd;
    }

    public String getBlx() {
        return blx;
    }

    public void setBlx(String blx) {
        this.blx = blx;
    }

    public String getCoquan() {
        return coquan;
    }

    public void setCoquan(String coquan) {
        this.coquan = coquan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int idkhachhang;
    public String hoten;
    public String cnnd;
    public String blx;
    public String coquan;
    public String email;
}
