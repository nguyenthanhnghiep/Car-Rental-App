package com.example.app_thue_xe_cnpmnc.LoaiXe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoaiXe_Activity extends AppCompatActivity {

public ListView lvloaixe;
    ArrayList<LoaiXe> arrLoaiXe;
    LoaiXe loaiXe;
public TextView test;
Button them,sua,xoa;
EditText edtidxe,edttenxe;
LoaiXe_adapter loaiXe_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_xe);
        arrLoaiXe = new ArrayList<>();
        AnhXa();
        getData();
        them.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String idxe = edtidxe.getText().toString().trim();
                final String txe = edttenxe.getText().toString().trim();



// Tạo một RequestQueue để gửi các yêu cầu tới server
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themloaixe, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý kết quả trả về từ server
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        arrLoaiXe.clear();
                        getData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu tới server
                        Toast.makeText(LoaiXe_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // Tạo một Map để chứa các tham số cần truyền đi
                        Map<String, String> params = new HashMap<>();
                        params.put("idLoaiXe", idxe);
                        params.put("tenLoaiXe", txe);
                        return params;
                    }
                };

// Thêm yêu cầu vào RequestQueue để gửi đi
                requestQueue.add(stringRequest);
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String idloaixex = edtidxe.getText().toString().trim();;




// Tạo một RequestQueue để gửi các yêu cầu tới server
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.xoaloaixe, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý kết quả trả về từ server
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        arrLoaiXe.clear();
                        getData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu tới server
                        Toast.makeText(LoaiXe_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // Tạo một Map để chứa các tham số cần truyền đi
                        Map<String, String> params = new HashMap<>();
                        params.put("idLoaiXe", idloaixex);


                        return params;
                    }
                };

// Thêm yêu cầu vào RequestQueue để gửi đi
                requestQueue.add(stringRequest);
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String idxe = edtidxe.getText().toString().trim();
                final String txe = edttenxe.getText().toString().trim();


// Tạo một RequestQueue để gửi các yêu cầu tới server
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.sualoaixe, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý kết quả trả về từ server
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        arrLoaiXe.clear();
                        getData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoaiXe_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // Tạo một Map để chứa các tham số cần truyền đi
                        Map<String, String> params = new HashMap<>();
                        params.put("LoaiXeID", idxe);
                        params.put("TenLoaiXe", txe);
                        return params;
                    }
                };

// Thêm yêu cầu vào RequestQueue để gửi đi
                requestQueue.add(stringRequest);
            }
        });

    }



    private void AnhXa() {
        lvloaixe = findViewById(R.id.listview_loaixe);
        them = findViewById(R.id.btnthemloaixe);
        sua = findViewById(R.id.btnsualoaixe);
        xoa = findViewById(R.id.btnxoaloaixe);
        edtidxe = findViewById(R.id.edtidloaixe);
        edttenxe = findViewById(R.id.edttenloaixe);
        //Listview set up
        loaiXe_adapter = new LoaiXe_adapter(getApplicationContext(),arrLoaiXe);
        lvloaixe.setAdapter(loaiXe_adapter);
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getloaixe, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int idLoaiXe = 0;
                    String tenLoaiXe = "";
                    for (int i = 0 ; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                             idLoaiXe = jsonObject.getInt("LoaiXeID");
                             tenLoaiXe = jsonObject.getString("TenLoaiXe");
                            arrLoaiXe.add(new LoaiXe(idLoaiXe,tenLoaiXe));
                            loaiXe_adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoaiXe_Activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


}