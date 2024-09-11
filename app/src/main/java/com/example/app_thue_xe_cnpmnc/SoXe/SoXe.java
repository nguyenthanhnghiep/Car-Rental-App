package com.example.app_thue_xe_cnpmnc.SoXe;

public class SoXe {
    private String SoXeID;
    private String XeID;
    private String NgayThue;
    private String NgayTra;
    private String HopDongID;

    public String getSoXeID() {
        return SoXeID;
    }

    public void setSoXeID(String soXeID) {
        SoXeID = soXeID;
    }

    public String getXeID() {
        return XeID;
    }

    public void setXeID(String xeID) {
        XeID = xeID;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(String ngayThue) {
        NgayThue = ngayThue;
    }

    public String getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String ngayTra) {
        NgayTra = ngayTra;
    }

    public String getHopDongID() {
        return HopDongID;
    }

    public void setHopDongID(String hopDongID) {
        HopDongID = hopDongID;
    }

    public SoXe(String soXeID, String xeID, String ngayThue, String ngayTra, String hopDongID) {
        SoXeID = soXeID;
        XeID = xeID;
        NgayThue = ngayThue;
        NgayTra = ngayTra;
        HopDongID = hopDongID;
    }
}

