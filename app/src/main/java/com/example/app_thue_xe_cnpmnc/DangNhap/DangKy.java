package com.example.app_thue_xe_cnpmnc.DangNhap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.CheckConection;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {
EditText edttk,edtmk,edtnlmk;
Button btndangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventButton();
            }
        });
    }

    private void AnhXa() {
        edttk = findViewById(R.id.edttk_dk);
        edtmk = findViewById(R.id.edtnhapmk_dk);
        edtnlmk = findViewById(R.id.edtnhaplaimk_dk);
        btndangky = findViewById(R.id.btndangky_dk);
    }

    private void EventButton() {
        final String username = edttk.getText().toString().trim();
        final String pass = edtmk.getText().toString().trim();
        if(!edtmk.getText().toString().equals(edtnlmk.getText().toString())) {
            CheckConection.ShowToast_Short(getApplicationContext(),"Mật khẩu không trùng khớp");

        }
        else if(edttk.length() > 0 && edtmk.length() > 0){


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.dangkytk, new Response.Listener<String>() {
                @Override
                public void onResponse(final String madonhang) {


                    Intent intent = new Intent(getApplicationContext(),DangNhapNV.class);
                    startActivity(intent);
                    CheckConection.ShowToast_Short(getApplicationContext(),"Đăng Ký Thành Công");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> hashMap = new HashMap<String, String>();
                    hashMap.put("ten_dang_nhap", username);
                    hashMap.put("mat_khau", pass);

                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
        }else {
            CheckConection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu");
        }
    }
}