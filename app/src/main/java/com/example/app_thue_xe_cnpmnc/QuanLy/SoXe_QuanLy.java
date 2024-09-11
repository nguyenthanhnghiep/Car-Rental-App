package com.example.app_thue_xe_cnpmnc.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.DatXe.DatxeAdapter;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.SoXe.SoXe;
import com.example.app_thue_xe_cnpmnc.SoXe.SoXeAdapter;
import com.example.app_thue_xe_cnpmnc.SoXe.SoXe_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SoXe_QuanLy extends AppCompatActivity {
    int maXe;
    ArrayList<SoXe> soXes;
    SoXeAdapter soXeAdapter;
    ListView lvsoxe ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_xe_quan_ly);
        Intent intent = getIntent();
        maXe = Integer.parseInt(intent.getStringExtra("MA_XE"));

        AnhXa();
        getData();
    }
    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getsoxe, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    String soXeID = "";
                    String xeID = "";
                    String ngayThue = "";
                    String ngayTra = "";
                    String hopDongID = "";
                    for (int i = 0 ; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            soXeID = jsonObject.getString("SoXeID");
                            xeID = jsonObject.getString("XeID");
                            ngayThue = jsonObject.getString("NgayThue");
                            ngayTra = jsonObject.getString("NgayTra");
                            hopDongID = jsonObject.getString("HopDongID");
                    if(xeID.equals(String.valueOf(maXe))){
                        soXes.add(new SoXe(soXeID, xeID, ngayThue, ngayTra, hopDongID));
                        soXeAdapter.notifyDataSetChanged();
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
                Toast.makeText(SoXe_QuanLy.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void AnhXa() {
        soXes = new ArrayList<SoXe>();
        lvsoxe = findViewById(R.id.lvsoxe);
        soXeAdapter = new SoXeAdapter(getApplicationContext(),soXes);
        lvsoxe.setAdapter(soXeAdapter);
    }
}