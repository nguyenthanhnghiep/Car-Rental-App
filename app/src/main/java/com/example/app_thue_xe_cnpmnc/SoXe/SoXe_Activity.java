package com.example.app_thue_xe_cnpmnc.SoXe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_adapter;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SoXe_Activity extends AppCompatActivity {
ListView listviewsoxe;
ArrayList<SoXe> arrSoXe;
SoXeAdapter soXeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrSoXe = new ArrayList<>();
        setContentView(R.layout.activity_so_xe);
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

                            arrSoXe.add(new SoXe(soXeID, xeID, ngayThue, ngayTra, hopDongID));
                            soXeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SoXe_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void AnhXa() {
        listviewsoxe = findViewById(R.id.listviewsoxe);
        //Listview set up
        soXeAdapter = new SoXeAdapter(getApplicationContext(),arrSoXe);
        listviewsoxe.setAdapter(soXeAdapter);
    }
}