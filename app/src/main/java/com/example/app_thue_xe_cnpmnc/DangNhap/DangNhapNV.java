package com.example.app_thue_xe_cnpmnc.DangNhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.Main2Activity;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.TrangQuanly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DangNhapNV extends AppCompatActivity {
EditText edttk,edtmk;
Button btnDangNhap,btnDangKy;
String tkquanly = "quanly";
String passquanly = "123";
TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_nv);
        AnhXa();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(edttk.getText().toString().equals(tkquanly)&& edtmk.getText().toString().equals(passquanly)) {
                      Toast.makeText(DangNhapNV.this, "Bạn đã đăng nhập thành công quản lý", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(DangNhapNV.this, TrangQuanly.class);
                      startActivity(intent);
                  }
                getData();
            }
        });


    }
    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.gettknhanvien, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int IDtk = 0;
                String Tentaikhoan = "";
                String matkhau = "";
                if (response != null) {




                    for (int i = 0 ; i < response.length();i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            IDtk = jsonObject.getInt("TaiKhoanID");
                            Tentaikhoan = jsonObject.getString("TenDangNhap");
                            matkhau = jsonObject.getString("MatKhau");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (edttk.getText().length() != 0 && edtmk.getText().length() != 0) {
                            if (edttk.getText().toString().equals(Tentaikhoan) && edtmk.getText().toString().equals(matkhau)) {
                                Toast.makeText(DangNhapNV.this, "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangNhapNV.this, Main2Activity.class);
                                startActivity(intent);

                            }

                        }else{
                            Toast.makeText(DangNhapNV.this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else {
                    Toast.makeText(DangNhapNV.this, "Không có dữ liệu trả về từ máy chủ", Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangNhapNV.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void AnhXa() {
        edttk = findViewById(R.id.edttk);
        edtmk = findViewById(R.id.edtmk);
        btnDangNhap = findViewById(R.id.btndangnhap);


    }
}