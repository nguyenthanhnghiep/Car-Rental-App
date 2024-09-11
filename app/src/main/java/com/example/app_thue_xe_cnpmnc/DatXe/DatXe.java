package com.example.app_thue_xe_cnpmnc.DatXe;

public class DatXe {
    public int DatXeID;
    public int KhachHangID;
    public int LoaiXeID;
    public String NgayDat;
    public String NgayNhanXe;
    public String NgayTraXe;
    public String TrangThai;

    public int getDatXeID() {
        return DatXeID;
    }

    public void setDatXeID(int datXeID) {
        DatXeID = datXeID;
    }

    public int getKhachHangID() {
        return KhachHangID;
    }

    public void setKhachHangID(int khachHangID) {
        KhachHangID = khachHangID;
    }

    public int getLoaiXeID() {
        return LoaiXeID;
    }

    public void setLoaiXeID(int loaiXeID) {
        LoaiXeID = loaiXeID;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }

    public String getNgayNhanXe() {
        return NgayNhanXe;
    }

    public void setNgayNhanXe(String ngayNhanXe) {
        NgayNhanXe = ngayNhanXe;
    }

    public String getNgayTraXe() {
        return NgayTraXe;
    }

    public void setNgayTraXe(String ngayTraXe) {
        NgayTraXe = ngayTraXe;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public DatXe(int datXeID, int khachHangID, int loaiXeID, String ngayDat, String ngayNhanXe, String ngayTraXe, String trangThai) {
        DatXeID = datXeID;
        KhachHangID = khachHangID;
        LoaiXeID = loaiXeID;
        NgayDat = ngayDat;
        NgayNhanXe = ngayNhanXe;
        NgayTraXe = ngayTraXe;
        TrangThai = trangThai;
    }
}

