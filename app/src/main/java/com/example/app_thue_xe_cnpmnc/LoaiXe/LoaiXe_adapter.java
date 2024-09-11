package com.example.app_thue_xe_cnpmnc.LoaiXe;

import android.content.Context;
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

public class LoaiXe_adapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiXe> arraayloaisp;

    public LoaiXe_adapter(Context context, ArrayList<LoaiXe> arraayloaisp) {
        this.context = context;
        this.arraayloaisp = arraayloaisp;
    }

    public int IDloaixe;
    public String Tenloaixe;


    @Override
    public int getCount() {
        return arraayloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arraayloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView tvidloaixe , tvtenloaixe;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LoaiXe_adapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new LoaiXe_adapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(com.example.app_thue_xe_cnpmnc.R.layout.loaixe_adapter, null);
            viewHolder.tvidloaixe = (TextView) view.findViewById(R.id.idlaoixe);
            viewHolder.tvtenloaixe = (TextView) view.findViewById(R.id.idtenloaixe);
            view.setTag(viewHolder);
        }else {
            viewHolder = (LoaiXe_adapter.ViewHolder) view.getTag();
        }

        LoaiXe loaiXe = arraayloaisp.get(position);
        viewHolder.tvidloaixe.setText(String.valueOf(loaiXe.getLoaiXeID()));
        viewHolder.tvtenloaixe.setText(loaiXe.getTenLoaiXe());


        return  view;

    }
}
