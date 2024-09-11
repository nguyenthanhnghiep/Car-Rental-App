package com.example.app_thue_xe_cnpmnc.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe_Activity;
import com.example.app_thue_xe_cnpmnc.DatXe.DatxeAdapter;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThongTinXe extends AppCompatActivity {
int maXe;
ListView lvdangdat,lvdadat;
DatxeAdapter datxeAdapter;
    DatxeAdapter datxeAdapter2;
ArrayList<DatXe> datXes;
Button btnchonsoxe;
int datxeid;
    ArrayList<DatXe> datXes2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_xe);
        Intent intent = getIntent();
        maXe = Integer.parseInt(intent.getStringExtra("MA_XE"));
        AnhXa();
        getDatadangdat();
        getDatadangcho();
        btnchonsoxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(ThongTinXe.this, SoXe_QuanLy.class);
                intent2.putExtra("MA_XE", String.valueOf(maXe));
                startActivity(intent2);
            }
        });
        lvdadat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent3= new Intent(ThongTinXe.this, HoSoDatXe.class);
                intent3.putExtra("MA_XE", String.valueOf(maXe));
                intent3.putExtra("DatXe_id",String.valueOf(datXes.get(i).DatXeID));
                startActivity(intent3);
            }
        });
        lvdangdat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent4= new Intent(ThongTinXe.this, HoSoDatXe.class);
                intent4.putExtra("MA_XE", String.valueOf(maXe));
                intent4.putExtra("DatXe_id",String.valueOf(datXes.get(i).DatXeID));
                startActivity(intent4);
            }
        });
    }

    private void AnhXa() {
        datXes = new ArrayList<DatXe>();
        datXes2 = new ArrayList<DatXe>();
        lvdangdat = findViewById(R.id.lvdangcho);
        lvdadat = findViewById(R.id.lvxedangdat);
        btnchonsoxe = findViewById(R.id.btnxemsoxe);
        //Listview set up
        datxeAdapter = new DatxeAdapter(getApplicationContext(),datXes);
        lvdangdat.setAdapter(datxeAdapter);

        datxeAdapter2 = new DatxeAdapter(getApplicationContext(),datXes2);
        lvdadat.setAdapter(datxeAdapter2);


    }

    private void getDatadangdat() {
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
if(loaiXeID == maXe && response.length() > 0 && trangThai.equals("Hoàn thành") ){
    datXes.add(new DatXe(datXeID, khachHangID, loaiXeID, ngayDat, ngayNhanXe, ngayTraXe, trangThai));
}

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    datxeAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }


        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getDatadangcho() {
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
                            if(loaiXeID == maXe && response.length() > 0 && trangThai.equals("Chờ xác nhận") ){
                                datXes2.add(new DatXe(datXeID, khachHangID, loaiXeID, ngayDat, ngayNhanXe, ngayTraXe, trangThai));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    datxeAdapter2.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }


        });
        requestQueue.add(jsonArrayRequest);
    }
}