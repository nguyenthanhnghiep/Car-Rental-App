package com.example.app_thue_xe_cnpmnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app_thue_xe_cnpmnc.Xe.XemXe;

public class TrangNV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_nv);
    }

    public void DatXe_NV_onclick(View view) {
        Intent intent = new Intent(TrangNV.this, XemXe.class);
        startActivity(intent);
    }
}