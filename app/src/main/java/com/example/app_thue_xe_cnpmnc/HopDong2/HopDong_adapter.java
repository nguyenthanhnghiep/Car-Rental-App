package com.example.app_thue_xe_cnpmnc.HopDong2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_adapter;
import com.example.app_thue_xe_cnpmnc.R;

import java.util.ArrayList;

public class HopDong_adapter extends BaseAdapter {
    Context context;
    ArrayList<HopDong> arraayloaisp;

    public HopDong_adapter(Context context, ArrayList<HopDong> arraayloaisp) {
        this.context = context;
        this.arraayloaisp = arraayloaisp;
    }


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
        public TextView tvidhopdong , tviddatxe,tvngaylaphopdong,tvnoinhanxe,tvnoitraxe,tvtiencoc,tvtiencocconlai,tvtrangthai;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HopDong_adapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new HopDong_adapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hopdongadapter, null);
            viewHolder.tvidhopdong = (TextView) view.findViewById(R.id.idhopdong);
            viewHolder.tviddatxe = view.findViewById(R.id.tviddatxe);
            viewHolder.tvngaylaphopdong = view.findViewById(R.id.ngaylaphopdong);
            viewHolder.tvnoinhanxe = view.findViewById(R.id.edtdiadiemnhanxe);
            viewHolder.tvnoitraxe = view.findViewById(R.id.edtdiadiemtraxe);
            viewHolder.tvtiencoc = view.findViewById(R.id.edttiencoc_hd);
            viewHolder.tvtiencocconlai = view.findViewById(R.id.edttiencocconalai_hd);
            viewHolder.tvtrangthai = view.findViewById(R.id.edttinhtranghd);
            view.setTag(viewHolder);
        }else {
            viewHolder = (HopDong_adapter.ViewHolder) view.getTag();
        }

        HopDong hopDong = arraayloaisp.get(position);
        viewHolder.tviddatxe.setText("id đặt xe: "+ String.valueOf(hopDong.getIddatxe()));
        viewHolder.tvidhopdong.setText("id hợp đồng: "+String.valueOf(hopDong.getIdhopdong()));
        viewHolder.tvngaylaphopdong.setText("Ngày lập hợp đồng: "+hopDong.getNgaylaphopdong());
        viewHolder.tvnoinhanxe.setText("Nơi nhận xe: "+hopDong.getDiaDiemNhanXe());
        viewHolder.tvnoitraxe.setText("Nơi trả xe: "+ hopDong.getDiaDiemTraXe());
        viewHolder.tvtiencoc.setText("Tiền cọc: "+String.valueOf(hopDong.getTienCoc()));
        viewHolder.tvtiencocconlai.setText("Tiền còn lại: "+String.valueOf(hopDong.getTienConLai()));
        viewHolder.tvtrangthai.setText("Trạng thái: "+ hopDong.getTrangThaiHopDong());


        return  view;

    }
}
