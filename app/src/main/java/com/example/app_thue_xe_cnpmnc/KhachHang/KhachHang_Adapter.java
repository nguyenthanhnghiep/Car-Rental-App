package com.example.app_thue_xe_cnpmnc.KhachHang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_thue_xe_cnpmnc.R;

import java.util.ArrayList;

public class KhachHang_Adapter extends BaseAdapter {
    Context context;
    ArrayList<KhachHang> arrayKhachHang;

    public KhachHang_Adapter(Context context, ArrayList<KhachHang> arrayKhachHang) {
        this.context = context;
        this.arrayKhachHang = arrayKhachHang;
    }

    @Override
    public int getCount() {
        return arrayKhachHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayKhachHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView tvIDKhachHang, tvHoTen, tvCnnd, tvBLX, tvCoQuan, tvEmail;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.khachhang_adapter, null);
            viewHolder.tvIDKhachHang = view.findViewById(R.id.idkhachhang);
            viewHolder.tvHoTen = view.findViewById(R.id.hoten);
            viewHolder.tvCnnd = view.findViewById(R.id.cnnd);
            viewHolder.tvBLX = view.findViewById(R.id.BLX);
            viewHolder.tvCoQuan = view.findViewById(R.id.Coquan);
            viewHolder.tvEmail = view.findViewById(R.id.Email);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        KhachHang khachHang = arrayKhachHang.get(position);
        viewHolder.tvIDKhachHang.setText("id khách hàng: "+String.valueOf(khachHang.getIdkhachhang()));
        viewHolder.tvHoTen.setText("Họ Tên: "+khachHang.getHoten());
        viewHolder.tvCnnd.setText("CMND: "+khachHang.getCnnd());
        viewHolder.tvBLX.setText("BLX: "+khachHang.getBlx());
        viewHolder.tvCoQuan.setText("CoQuan: "+khachHang.getCoquan());
        viewHolder.tvEmail.setText("Email: "+khachHang.getEmail());

        return view;
    }

}

