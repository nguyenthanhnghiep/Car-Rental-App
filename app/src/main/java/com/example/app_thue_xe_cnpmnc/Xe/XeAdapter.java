package com.example.app_thue_xe_cnpmnc.Xe;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_thue_xe_cnpmnc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XeAdapter extends BaseAdapter {
    Context context;
    ArrayList<Xe> arraayXe;

    public XeAdapter(Context context, ArrayList<Xe> arraayXe) {
        this.context = context;
        this.arraayXe = arraayXe;
    }

    @Override
    public int getCount() {
        return arraayXe.size();
    }

    @Override
    public Object getItem(int position) {
        return arraayXe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public ImageView imgAnhXe;
        public TextView tvIDXe, tvIDLoaiXe, tvGiaXe, tvTinhTrang,tvtenxe;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.xe_adapter, null);

            viewHolder.imgAnhXe = view.findViewById(R.id.imganhxe);
            viewHolder.tvIDXe = view.findViewById(R.id.tvidxe);
            viewHolder.tvtenxe = view.findViewById(R.id.tvtenxe);
            viewHolder.tvIDLoaiXe = view.findViewById(R.id.idloaixe_xe);
            viewHolder.tvGiaXe = view.findViewById(R.id.giaxe);
            viewHolder.tvTinhTrang = view.findViewById(R.id.tinhtrang);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Xe xe = arraayXe.get(position);

        // Sử dụng Picasso để đặt ảnh
        Picasso.get().load(xe.getAnhxe()).into(viewHolder.imgAnhXe);

        viewHolder.tvIDXe.setText(String.valueOf("X" +xe.getIdxe()));
        viewHolder.tvIDLoaiXe.setText("LX" + String.valueOf(xe.getIdloaixe()));
        viewHolder.tvtenxe.setText("Tên Xe: "+xe.getTenxe());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvGiaXe.setText("Giá: "+ decimalFormat.format(xe.getGiatien())+"Đồng");
        if(xe.getTrangthai().equals("có sẵn")){
            viewHolder.tvTinhTrang.setText("Trạng thái: "+xe.getTrangthai());
            viewHolder.tvTinhTrang.setTextColor(Color.parseColor("#00FF00"));
        }
        if(xe.getTrangthai().equals("có sẵn")){
            viewHolder.tvTinhTrang.setText("Trạng thái: "+xe.getTrangthai());
            viewHolder.tvTinhTrang.setTextColor(Color.parseColor("#00FF00"));
        }else if(xe.getTrangthai().equals("đang thuê")){
            viewHolder.tvTinhTrang.setText("Trạng thái: "+xe.getTrangthai());
            viewHolder.tvTinhTrang.setTextColor(Color.parseColor("#FFFF00"));
        }
        else if(xe.getTrangthai().equals("không hoạt động")){
            viewHolder.tvTinhTrang.setText("Trạng thái: "+xe.getTrangthai());
            viewHolder.tvTinhTrang.setTextColor(Color.parseColor("#FF0000"));
        }




        return view;
    }
}

