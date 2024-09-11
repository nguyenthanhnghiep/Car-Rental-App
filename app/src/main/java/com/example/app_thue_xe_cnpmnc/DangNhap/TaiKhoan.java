package com.example.app_thue_xe_cnpmnc.DangNhap;

public class TaiKhoan {


    public TaiKhoan(int idtk, String tentk, String matkhau) {
        this.idtk = idtk;
        this.tentk = tentk;
        this.matkhau = matkhau;
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int idtk;
    public String tentk;
    public String matkhau;

}
