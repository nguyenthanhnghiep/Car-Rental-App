package com.example.app_thue_xe_cnpmnc.DatXe;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_thue_xe_cnpmnc.R;

import java.util.ArrayList;

public class DatxeAdapter extends BaseAdapter {
    Context context;
    ArrayList<DatXe> arrayDatXe;

    public DatxeAdapter(Context context, ArrayList<DatXe> arrayDatXe) {
        this.context = context;
        this.arrayDatXe = arrayDatXe;
    }

    @Override
    public int getCount() {
        return arrayDatXe.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayDatXe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView tvDatXeID, tvKhachHangID, tvLoaiXeID, tvNgayDat, tvNgayNhanXe, tvNgayTraXe, tvTrangThai;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.datxe_adapter, null);
            viewHolder.tvDatXeID = view.findViewById(R.id.tvDatXeID);
            viewHolder.tvKhachHangID = view.findViewById(R.id.tvKhachHangID);
            viewHolder.tvLoaiXeID = view.findViewById(R.id.tvLoaiXeID);
            viewHolder.tvNgayDat = view.findViewById(R.id.tvNgayDat);
            viewHolder.tvNgayNhanXe = view.findViewById(R.id.tvNgayNhanXe);
            viewHolder.tvNgayTraXe = view.findViewById(R.id.tvNgayTraXe);
            viewHolder.tvTrangThai = view.findViewById(R.id.tvTrangThai);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        DatXe datXe = arrayDatXe.get(position);
        viewHolder.tvDatXeID.setText("ID Đặt Xe: "+datXe.getDatXeID());
        viewHolder.tvKhachHangID.setText("ID Khách Hàng: "+datXe.getKhachHangID());
        viewHolder.tvLoaiXeID.setText("ID Loại Xe: " + datXe.getLoaiXeID());
        viewHolder.tvNgayDat.setText("Ngày Đặt: "+datXe.getNgayDat());
        viewHolder.tvNgayNhanXe.setText("Ngày Nhận: "+datXe.getNgayNhanXe());
        viewHolder.tvNgayTraXe.setText("Ngày Trả Xe: "+datXe.getNgayTraXe());
        viewHolder.tvTrangThai.setText("Trạng Thái: "+datXe.getTrangThai());

        return view;
    }
}

