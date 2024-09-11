package com.example.app_thue_xe_cnpmnc.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.KhachHang.ChonKhachHang;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.Xe.Xe;
import com.example.app_thue_xe_cnpmnc.Xe.XeAdapter;
import com.example.app_thue_xe_cnpmnc.Xe.XemXe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChonXe extends AppCompatActivity {
ListView lvchonxe;
XeAdapter xeAdapter;
ArrayList<Xe> xes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_xe);
        xes = new ArrayList<Xe>();
        getData();
        AnhXa();
        lvchonxe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChonXe.this, ThongTinXe.class);
                intent.putExtra("MA_XE", String.valueOf(xes.get(i).idxe));

                startActivity(intent);
            }
        });



    }
    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
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

                        xes.add(new Xe(LoaiXeID, TinhTrangXe, GiaXe, hinhanhxe, XeID,tenxe));
                    }

                    xeAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChonXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }
    private void AnhXa() {
        lvchonxe = findViewById(R.id.listview_Chonxe);
        xeAdapter = new XeAdapter(getApplicationContext(),xes);
        lvchonxe.setAdapter(xeAdapter);

    }
}