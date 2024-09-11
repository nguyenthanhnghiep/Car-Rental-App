package com.example.app_thue_xe_cnpmnc.LoaiXe;

public class LoaiXe {
    public int getLoaiXeID() {
        return LoaiXeID;
    }

    public void setLoaiXeID(int loaiXeID) {
        LoaiXeID = loaiXeID;
    }

    public String getTenLoaiXe() {
        return TenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        TenLoaiXe = tenLoaiXe;
    }

    public int LoaiXeID;
    public String TenLoaiXe;
    public LoaiXe(int loaiXeID, String tenLoaiXe) {
        LoaiXeID = loaiXeID;
        TenLoaiXe = tenLoaiXe;
    }


}
