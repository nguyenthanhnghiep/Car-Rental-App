package com.example.app_thue_xe_cnpmnc.HopDong2;

public class HopDong {




    public int getIddatxe() {
        return iddatxe;
    }

    public void setIddatxe(int iddatxe) {
        this.iddatxe = iddatxe;
    }



    public int getIdhopdong() {
        return idhopdong;
    }

    public void setIdhopdong(int idhopdong) {
        this.idhopdong = idhopdong;
    }

    public String getNgaylaphopdong() {
        return ngaylaphopdong;
    }

    public void setNgaylaphopdong(String ngaylaphopdong) {
        this.ngaylaphopdong = ngaylaphopdong;
    }

    public String getDiaDiemNhanXe() {
        return DiaDiemNhanXe;
    }

    public void setDiaDiemNhanXe(String diaDiemNhanXe) {
        DiaDiemNhanXe = diaDiemNhanXe;
    }

    public String getDiaDiemTraXe() {
        return DiaDiemTraXe;
    }

    public void setDiaDiemTraXe(String diaDiemTraXe) {
        DiaDiemTraXe = diaDiemTraXe;
    }

    public int getTienCoc() {
        return TienCoc;
    }

    public void setTienCoc(int tienCoc) {
        TienCoc = tienCoc;
    }

    public int getTienConLai() {
        return TienConLai;
    }

    public void setTienConLai(int tienConLai) {
        TienConLai = tienConLai;
    }

    public String getTrangThaiHopDong() {
        return TrangThaiHopDong;
    }

    public void setTrangThaiHopDong(String trangThaiHopDong) {
        TrangThaiHopDong = trangThaiHopDong;
    }

    public String DiaDiemTraXe;
    public int TienCoc;
    public int TienConLai;
    public String TrangThaiHopDong;

    public HopDong(String diaDiemTraXe, int tienCoc, int tienConLai, String trangThaiHopDong, int idhopdong, int iddatxe, String ngaylaphopdong, String diaDiemNhanXe) {
        DiaDiemTraXe = diaDiemTraXe;
        TienCoc = tienCoc;
        TienConLai = tienConLai;
        TrangThaiHopDong = trangThaiHopDong;
        this.idhopdong = idhopdong;
        this.iddatxe = iddatxe;
        this.ngaylaphopdong = ngaylaphopdong;
        DiaDiemNhanXe = diaDiemNhanXe;
    }

    public int idhopdong;
    public int iddatxe;
    public String ngaylaphopdong;

    public String DiaDiemNhanXe;


}
