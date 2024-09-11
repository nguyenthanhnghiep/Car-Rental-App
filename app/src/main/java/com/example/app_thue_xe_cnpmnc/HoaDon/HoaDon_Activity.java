package com.example.app_thue_xe_cnpmnc.HoaDon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.Main2Activity;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HoaDon_Activity extends AppCompatActivity {
TextView tvidhoadon,tvidhopdong,tvngaylaphd,tvtongtien,tvtiendatra;
    String mahddx;
    Button btnhoadon;
    int mahd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        AnhXa();

        Intent intent = getIntent();

        mahd = intent.getIntExtra("MA_HD",0);

       if (mahd != 0) {


            Toast.makeText(HoaDon_Activity.this, "Mã hđ: " + mahd, Toast.LENGTH_SHORT).show();
            getData(mahd);
        }else {
           getDataMoiNhat();
       }
        btnhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HoaDon_Activity.this , Main2Activity.class);
                startActivity(intent1);
            }
        });
    }

    private void AnhXa() {
        tvidhoadon = findViewById(R.id.idhoadon);
        tvidhopdong = findViewById(R.id.idhopdong_hodon);
        tvngaylaphd = findViewById(R.id.ngaylaphoadon);
        tvtongtien = findViewById(R.id.tienphaitra_hdon);
        tvtiendatra = findViewById(R.id.dathanhtoan_hdon);
        btnhoadon = findViewById(R.id.btnhoadon);

    }
    private void getData(int hdid) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.gethoadon, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int hoaDonID = jsonObject.getInt("HoaDonID");
                            int hopDongID = jsonObject.getInt("HopDongID");
                            String ngayLapHoaDon = jsonObject.getString("NgayLapHoaDon");
                            int tongTien = jsonObject.getInt("TongTien");
                            int daThanhToan = jsonObject.getInt("DaThanhToan");

                            if(hopDongID == hdid)
                            {
                                tvidhoadon.setText("ID hóa đơn: "+String.valueOf(hoaDonID));
                                tvidhopdong.setText("ID hợp đồng: "+String.valueOf(hopDongID));
                                tvngaylaphd.setText("Ngày lập hợp đồng: "+ngayLapHoaDon);
                                tvtongtien.setText("Tổng tiền: "+String.valueOf(tongTien));
                                tvtiendatra.setText("Tien đã trả: "+String.valueOf(tongTien));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Notify the adapter that the data has changed
                    // Assuming that you have a custom adapter for your ListView

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoaDon_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getDataMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.gethoadon, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int hoaDonID = jsonObject.getInt("HoaDonID");
                            int hopDongID = jsonObject.getInt("HopDongID");
                            String ngayLapHoaDon = jsonObject.getString("NgayLapHoaDon");
                            int tongTien = jsonObject.getInt("TongTien");
                            int daThanhToan = jsonObject.getInt("DaThanhToan");

                            if(i == response.length() - 1)
                            {
                                tvidhoadon.setText("ID hóa đơn: "+String.valueOf(hoaDonID));
                                tvidhopdong.setText("ID hợp đồng: "+String.valueOf(hopDongID));
                                tvngaylaphd.setText("Ngày lập hợp đồng: "+ngayLapHoaDon);
                                tvtongtien.setText("Tổng tiền: "+String.valueOf(tongTien));
                                tvtiendatra.setText("Tien đã trả: "+String.valueOf(daThanhToan));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Notify the adapter that the data has changed
                    // Assuming that you have a custom adapter for your ListView

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoaDon_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}