package com.example.app_thue_xe_cnpmnc.SoXe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_thue_xe_cnpmnc.R;

import java.util.List;

public class SoXeAdapter extends ArrayAdapter<SoXe> {

    public SoXeAdapter(Context context, List<SoXe> soXeList) {
        super(context, 0, soXeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SoXe soXe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.soxe_adapter, parent, false);
        }

        TextView tvSoXeID = convertView.findViewById(com.example.app_thue_xe_cnpmnc.R.id.tvSoXeID);
        TextView tvXeID = convertView.findViewById(R.id.tvXeID);
        TextView tvNgayThue = convertView.findViewById(R.id.tvNgayThue);
        TextView tvNgayTra = convertView.findViewById(R.id.tvNgayTra);
        TextView tvHopDongID = convertView.findViewById(R.id.tvHopDongId);

        tvSoXeID.setText("Số Xe ID: " + soXe.getSoXeID());
        tvXeID.setText("Xe ID: " + soXe.getXeID());
        tvNgayThue.setText("Ngày Thuê: " + soXe.getNgayThue());
        tvNgayTra.setText("Ngày Trả: " + soXe.getNgayTra());
        tvHopDongID.setText("Hợp Đồng ID: " + soXe.getHopDongID());

        return convertView;
    }
}

