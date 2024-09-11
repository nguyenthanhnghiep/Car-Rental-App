package com.example.app_thue_xe_cnpmnc.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe_Activity;
import com.example.app_thue_xe_cnpmnc.KhachHang.ChonKhachHang;
import com.example.app_thue_xe_cnpmnc.KhachHang.KhachHang;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.Xe.Xe;
import com.example.app_thue_xe_cnpmnc.Xe.XemXe;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HoSoDatXe extends AppCompatActivity {
TextView hstenkh,hstenxe,hsloaixe,hsgiaxe,hsngaydatxe,hsngaynhanxe,hsngaytraxe,hstongtien,hstrangthai,ngayxemhd;
ImageView anhxe;
int maXe,maDatxe;
ImageButton print;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so_dat_xe);
        Intent intent = getIntent();
        maXe = Integer.parseInt(intent.getStringExtra("MA_XE"));
        maDatxe = Integer.parseInt(intent.getStringExtra("DatXe_id"));
        System.out.println(maXe); // In ra màn hình chuỗi "Hello, world!"
        System.out.println(maDatxe);
        AnhXa();
        GetData();
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(HoSoDatXe.this, "Đã in ra, hãy kiểm tra máy in" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getdatxe, new com.android.volley.Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);

                            int datXeID = jsonObject.getInt("DatXeID");
                            int khachHangID = jsonObject.getInt("KhachHangID");
                            int loaiXeID = jsonObject.getInt("XeID");
                            String ngayDat = jsonObject.getString("NgayDat");
                            String ngayNhanXe = jsonObject.getString("NgayNhanXe");
                            String ngayTraXe = jsonObject.getString("NgayTraXe");
                            String trangThai = jsonObject.getString("TrangThai");
                        if(datXeID == maDatxe){
                            hsngaydatxe.setText("Ngày Đặt: "+ngayDat);
                            hsngaytraxe.setText("Ngày Trả: "+ngayTraXe);
                            hsngaynhanxe.setText("Ngày Nhận Xe: "+ngayNhanXe);
                            hstrangthai.setText(trangThai);

                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getkhachhang, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response != null) {


                                        for (int i = 0; i < response.length(); i++) {
                                            try {
                                                JSONObject jsonObject = response.getJSONObject(i);
                                                int idkhachhang = jsonObject.getInt("KhachHangID");
                                                String hoten = jsonObject.getString("HoTen");
                                                String cnnd = jsonObject.getString("CMND");
                                                String blx = jsonObject.getString("BLX");
                                                String coquan = jsonObject.getString("CoQuan");
                                                String email = jsonObject.getString("Email");
                                            if(idkhachhang == khachHangID){
                                                hstenkh.setText("Họ Tên: " + hoten);

                                                StringRequest stringRequest = new StringRequest(Request.Method.GET, Sever.getxe, new Response.Listener<String>() {

                                                    @Override
                                                    public void onResponse(String response) {
                                                        try {

                                                            JSONArray jsonArray = new JSONArray(response);
                                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                                                // Đảm bảo rằng các trường dữ liệu trong JSON đúng với tên mà bạn đang sử dụng
                                                                int XeID = jsonObject.getInt("XeID");
                                                                int LoaiXeID = jsonObject.getInt("LoaiXeID");
                                                                String TinhTrangXe = jsonObject.getString("TinhTrangXe");
                                                                int GiaXe = jsonObject.getInt("GiaXe");
                                                                String hinhanhxe = jsonObject.getString("AnhXe");
                                                                String tenxe = jsonObject.getString("TenXe");
                                                                if(XeID ==  loaiXeID  ){
                                                                    hstenxe.setText("Tên Xe: "+tenxe);
                                                                    Picasso.get().load(hinhanhxe).into(anhxe);
                                                                    hsgiaxe.setText("Giá Xe: " +GiaXe);
                                                                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getloaixe, new Response.Listener<JSONArray>() {
                                                                        @Override
                                                                        public void onResponse(JSONArray response) {
                                                                            if (response != null) {
                                                                                int idLoaiXe = 0;
                                                                                String tenLoaiXe = "";
                                                                                for (int i = 0 ; i < response.length(); i++) {
                                                                                    try {
                                                                                        JSONObject jsonObject = response.getJSONObject(i);
                                                                                        idLoaiXe = jsonObject.getInt("LoaiXeID");
                                                                                        tenLoaiXe = jsonObject.getString("TenLoaiXe");
                                                                                       if(idLoaiXe == LoaiXeID){
                                                                                           hsloaixe.setText("Loại Xe: "+tenLoaiXe);
                                                                                       }
                                                                                    } catch (JSONException e) {
                                                                                        e.printStackTrace();
                                                                                    }
                                                                                }



                                                                            }
                                                                        }
                                                                    }, new Response.ErrorListener() {
                                                                        @Override
                                                                        public void onErrorResponse(VolleyError error) {
                                                                            Toast.makeText(HoSoDatXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                                                            error.printStackTrace();
                                                                        }
                                                                    });
                                                                    requestQueue.add(jsonArrayRequest);
                                                                }

                                                            }



                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(HoSoDatXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                                        error.printStackTrace();
                                                    }
                                                });
                                                requestQueue.add(stringRequest);
                                            }

                                                // Create a KhachHang object and add it to the list

                                            } catch (JSONException e) {

                                            }
                                        }

                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(HoSoDatXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    error.printStackTrace();
                                }
                            });
                            requestQueue.add(jsonArrayRequest);
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoSoDatXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }


        });
        requestQueue.add(jsonArrayRequest);
    }

    private void AnhXa() {
        hstenkh = findViewById(R.id.hosotenkh);
        hstenxe = findViewById(R.id.hosotenxe);
        hsgiaxe = findViewById(R.id.hosogiaxe);
        hsloaixe = findViewById(R.id.hosoloaixe);
        hsngaydatxe = findViewById(R.id.hosongaydatxe);
        anhxe = findViewById(R.id.anhxe);
        hsngaytraxe = findViewById(R.id.hosongaytraxe);
        hsngaynhanxe = findViewById(R.id.hosongaynhanxe);
        hstrangthai = findViewById(R.id.hosotrangthai);
        hstongtien = findViewById(R.id.hosotongtien);
        ngayxemhd = findViewById(R.id.hosongaythangnam);
        print = findViewById(R.id.print);
    }
}