package com.example.app_thue_xe_cnpmnc.HoaDon;

public class HoaDon {



    public HoaDon(int idhoadon, int idhopong, String ngaylaphopdong, int tongtien, int dathanhtoan) {
        this.idhoadon = idhoadon;
        this.idhopong = idhopong;
        this.ngaylaphopdong = ngaylaphopdong;
        this.tongtien = tongtien;
        this.dathanhtoan = dathanhtoan;
    }

    public int getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(int idhoadon) {
        this.idhoadon = idhoadon;
    }

    public int getIdhopong() {
        return idhopong;
    }

    public void setIdhopong(int idhopong) {
        this.idhopong = idhopong;
    }

    public String getNgaylaphopdong() {
        return ngaylaphopdong;
    }

    public void setNgaylaphopdong(String ngaylaphopdong) {
        this.ngaylaphopdong = ngaylaphopdong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getDathanhtoan() {
        return dathanhtoan;
    }

    public void setDathanhtoan(int dathanhtoan) {
        this.dathanhtoan = dathanhtoan;
    }

    public int idhoadon;
    public int idhopong;
    public String ngaylaphopdong;
    public int tongtien;
    public int dathanhtoan;
}
