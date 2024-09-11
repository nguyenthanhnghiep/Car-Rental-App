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

public class Spinnerloaixe extends BaseAdapter {
    Context context;
    ArrayList<Loaixe_spinner> arraayloaisp;

    public Spinnerloaixe(Context context, ArrayList<Loaixe_spinner> arraayloaisp) {
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
        public TextView  tvtenloaixe;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Spinnerloaixe.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new Spinnerloaixe.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinnerlx_adapter, null);
            viewHolder.tvtenloaixe = (TextView) view.findViewById(R.id.idloaixe_spinner);
            view.setTag(viewHolder);
        }else {
            viewHolder = (Spinnerloaixe.ViewHolder) view.getTag();
        }

        Loaixe_spinner loaiXe = arraayloaisp.get(position);

        viewHolder.tvtenloaixe.setText(loaiXe.getTenloaixe());


        return  view;

    }
}
