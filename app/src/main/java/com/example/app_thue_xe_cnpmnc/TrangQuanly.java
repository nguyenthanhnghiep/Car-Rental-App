package com.example.app_thue_xe_cnpmnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_thue_xe_cnpmnc.DangNhap.DangKy;
import com.example.app_thue_xe_cnpmnc.HopDong2.XemHopDong;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.QuanLy.Barchart_activity;
import com.example.app_thue_xe_cnpmnc.QuanLy.ChonXe;
import com.example.app_thue_xe_cnpmnc.Xe.ThemXe;

public class TrangQuanly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_quanly);
    }

    public void ThemXe(View view) {
        Intent intent = new Intent(TrangQuanly.this, ThemXe.class);
        startActivity(intent);
    }
    public void onlcick_LoaiXe(View view) {
        Intent intent = new Intent(TrangQuanly.this, LoaiXe_Activity.class);
        startActivity(intent);
    }
    public void TrangChonXe_onclick(View view) {
        Intent intent = new Intent(TrangQuanly.this, ChonXe.class);
        startActivity(intent);
    }

    public void DoanhThu(View view) {
        Intent intent = new Intent(TrangQuanly.this, Barchart_activity.class);
        startActivity(intent);
    }

    public void  onlcick_ThemNhanVien(View view) {
        Intent intent = new Intent(TrangQuanly.this, DangKy.class);
        startActivity(intent);
    }

}