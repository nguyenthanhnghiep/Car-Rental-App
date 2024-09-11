package com.example.app_thue_xe_cnpmnc.KhachHang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.DatXe.Add_DatXe;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe_Activity;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_adapter;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.Xe.XemXe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChonKhachHang extends AppCompatActivity {
KhachHang_Adapter khachHang_adapter;
ListView lvkh;
    String maXe = "0";
    int giatien = 0;
    String tinhtrang = "0";
Button btnsearchkh,btndangky_ckh;
EditText edtidkh;
ArrayList<KhachHang> khachHangs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_khach_hang);
        khachHangs = new ArrayList<>();
        AnhXa();
        btnsearchkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

if(edtidkh.getText().length() == 0){
    getData();
}
else {
    getDatatheocmnd(Integer.parseInt(edtidkh.getText().toString()));

}
            }
        });
        Intent intent = getIntent();

        maXe = intent.getStringExtra("MA_XE");
        giatien = intent.getIntExtra("GiaXe",0);
        tinhtrang = intent.getStringExtra("TINH_TRANG");
        Toast.makeText(ChonKhachHang.this, "Mã xe đã chọn: " + maXe, Toast.LENGTH_SHORT).show();
        lvkh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(ChonKhachHang.this, Add_DatXe.class);
                    intent.putExtra("MA_XE", maXe);
                intent.putExtra("GiaXe", giatien );
                    intent.putExtra("MA_KH", String.valueOf(khachHangs.get(i).idkhachhang));
                intent.putExtra("TINH_TRANG", tinhtrang);
                    startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        lvkh = findViewById(R.id.lvkh);
btnsearchkh = findViewById(R.id.btnsearchkh);
        btndangky_ckh = findViewById(R.id.btndangky_ckh);
edtidkh = findViewById(R.id.edtidkh);
        khachHang_adapter = new KhachHang_Adapter(getApplicationContext(),khachHangs);
        lvkh.setAdapter(khachHang_adapter);
        getData();
        btndangky_ckh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChonKhachHang.this, Add_KhachHang.class);
                startActivity(intent);
            }
        });

    }
    private void getDatatheocmnd(int idkh) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getkhachhang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    khachHangs.clear(); // Clear the existing data before adding new data

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int idkhachhang = jsonObject.getInt("KhachHangID");
                            String hoten = jsonObject.getString("HoTen");
                            String cnnd = jsonObject.getString("CMND");
                            String blx = jsonObject.getString("BLX");
                            String coquan = jsonObject.getString("CoQuan");
                            String email = jsonObject.getString("Email");
if(idkh == idkhachhang)
{
    khachHangs.add(new KhachHang(idkhachhang, hoten, cnnd, blx, coquan, email));
}
                            // Create a KhachHang object and add it to the list

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Notify the adapter that the data has changed
                    khachHang_adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChonKhachHang.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getkhachhang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    khachHangs.clear(); // Clear the existing data before adding new data

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int idkhachhang = jsonObject.getInt("KhachHangID");
                            String hoten = jsonObject.getString("HoTen");
                            String cnnd = jsonObject.getString("CMND");
                            String blx = jsonObject.getString("BLX");
                            String coquan = jsonObject.getString("CoQuan");
                            String email = jsonObject.getString("Email");

                            // Create a KhachHang object and add it to the list
                            khachHangs.add(new KhachHang(idkhachhang, hoten, cnnd, blx, coquan, email));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Notify the adapter that the data has changed
                    khachHang_adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChonKhachHang.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


}