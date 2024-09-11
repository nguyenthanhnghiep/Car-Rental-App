package com.example.app_thue_xe_cnpmnc.DatXe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.HopDong2.Add_HopDong;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatXe_Activity extends AppCompatActivity {
ListView listviewdatxe;
int maxe;
ArrayList<DatXe> arrdatXe;
DatXe datXe;
Calendar cldngaynhan,cldngaydat;
DatxeAdapter datXeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_xe);
        arrdatXe = new ArrayList<>();

        AnhXa();
        getData();
        listviewdatxe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DatXe_Activity.this, Add_HopDong.class);
                String ngaynhan = arrdatXe.get(i).NgayNhanXe;
                String ngaydat = arrdatXe.get(i).NgayTraXe;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    // Chuyển đổi chuỗi thành đối tượng Date
                    Date date = sdf.parse(ngaynhan);

                    // Tạo đối tượng Calendar và thiết lập giá trị của ngày tháng
                    cldngaynhan = Calendar.getInstance();
                    cldngaynhan.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                try {
                    // Chuyển đổi chuỗi thành đối tượng Date
                    Date date2 = sdf.parse(ngaydat);

                    // Tạo đối tượng Calendar và thiết lập giá trị của ngày tháng
                    cldngaydat = Calendar.getInstance();
                    cldngaydat.setTime(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                // Lấy số mili giây của hai ngày nhận và ngày trả xe
                long ngayNhanInMillis = cldngaynhan.getTimeInMillis();
                long ngayTraInMillis = cldngaydat.getTimeInMillis();

// Tính khoảng thời gian giữa hai ngày trong mili giây
                long khoangThoiGian = ngayTraInMillis - ngayNhanInMillis;

// Chuyển đổi khoảng thời gian từ mili giây sang số ngày
                int soNgay = (int) (khoangThoiGian / (1000 * 60 * 60 * 24)); // 1 ngày có 24 giờ, mỗi giờ có 60 phút, mỗi phút có 60 giây


                intent.putExtra("MA_XE", String.valueOf(arrdatXe.get(i).DatXeID));
                intent.putExtra("MA_XE2", arrdatXe.get(i).LoaiXeID);
                intent.putExtra("NgayNhan", soNgay);
                startActivity(intent);

            }
        });

    }
    private void getData() {
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

                            arrdatXe.add(new DatXe(datXeID, khachHangID, loaiXeID, ngayDat, ngayNhanXe, ngayTraXe, trangThai));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    datXeAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DatXe_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }


        });
        requestQueue.add(jsonArrayRequest);
    }


    private void AnhXa() {
        listviewdatxe = findViewById(R.id.listviewdatxe);
        //Listview set up
        datXeAdapter = new DatxeAdapter(getApplicationContext(),arrdatXe);
        listviewdatxe.setAdapter(datXeAdapter);
    }
}