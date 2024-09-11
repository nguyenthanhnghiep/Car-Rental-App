package com.example.app_thue_xe_cnpmnc.KhachHang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import java.util.HashMap;
import java.util.Map;

public class Add_KhachHang extends AppCompatActivity {
EditText edthoten,edtcmnd,edtblx,edtcoquan,edtemail;
Button btndangkykh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_khach_hang);
        AnhXa();
        btndangkykh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String hoten = edthoten.getText().toString().trim();
                final String cmnd = edtcmnd.getText().toString().trim();
                final String blx = edtblx.getText().toString().trim();
                final String coquan = edtcoquan.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
// Tạo một RequestQueue để gửi các yêu cầu tới server
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themkhachhang, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý kết quả trả về từ server
                        Toast.makeText(getApplicationContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu tới server
                        Toast.makeText(Add_KhachHang.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // Tạo một Map để chứa các tham số cần truyền đi
                        Map<String, String> params = new HashMap<>();
                        params.put("HoTen", hoten);
                        params.put("CMND", cmnd);
                        params.put("BLX", blx);
                        params.put("CoQuan", coquan);
                        params.put("Email", email);
                        return params;
                    }
                };

// Thêm yêu cầu vào RequestQueue để gửi đi
                requestQueue.add(stringRequest);
            }
        });
    }

    private void AnhXa() {
        edthoten = findViewById(R.id.edthotenkh);
        edtcmnd = findViewById(R.id.edtcmnd);
        edtblx = findViewById(R.id.edtblx);
        edtcoquan = findViewById(R.id.edtcoqan);
        edtemail = findViewById(R.id.edtemail);
        btndangkykh = findViewById(R.id.btndangky_kh);
    }
}