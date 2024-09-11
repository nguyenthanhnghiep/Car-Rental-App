package com.example.app_thue_xe_cnpmnc.Xe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.KhachHang.ChonKhachHang;
import com.example.app_thue_xe_cnpmnc.LoaiXe.Loaixe_spinner;
import com.example.app_thue_xe_cnpmnc.LoaiXe.Spinnerloaixe;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XemXe extends AppCompatActivity {
ArrayList<Xe> xes;
ArrayList<Loaixe_spinner> loaiXes;
Xe xe;
XeAdapter xeAdapter;
GridView lvxe;
Spinner spinnerlx,spinnertrangthai;
String trangthai;
    Spinnerloaixe loaiXe_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_xe);
        AnhXa();
        getData();
        SpinnerLoaiXe();

        spinnerlx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0) {

                    int loaixeid = xes.get(i).idxe;

                    Getxetheoloaixeid(loaixeid);
                    Toast.makeText(XemXe.this, "Đã bấm chọn: " + loaixeid, Toast.LENGTH_SHORT).show();
                    xes.clear();
                    xeAdapter.notifyDataSetChanged();
                }else {
                    getData();
                    xes.clear();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnertrangthai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý khi một mục được chọn
                if(position == 0)
                {
                    getData();
                }
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Bạn đã chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
                getDatatheotrangthai(selectedItem);
                xes.clear();
                xeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn
            }
        });

        lvxe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(xes.get(i).trangthai.equals("không hoạt động"))
                {
                    Toast.makeText(getApplicationContext(), "Xin lỗi xe đã ngừng hoạt động", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(XemXe.this, ChonKhachHang.class);
                    intent.putExtra("MA_XE", String.valueOf(xes.get(i).idxe));
                    intent.putExtra("TINH_TRANG", String.valueOf(xes.get(i).trangthai));
                    intent.putExtra("GiaXe", xes.get(i).giatien);
                    startActivity(intent);
                }


            }
        });


    }

    private void SpinnerLoaiXe() {
        loaiXes.add(new Loaixe_spinner("Chọn loại xe"));
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
                            loaiXes.add(new Loaixe_spinner(tenLoaiXe));
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
                Toast.makeText(XemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);



    }

    private void Getxetheoloaixeid(int loaixeid ) {

// Tạo một RequestQueue để gửi các yêu cầu tới server
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.getxetheoxeid, new Response.Listener<String>() {
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
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi


                Map<String, String> params = new HashMap<>();
                params.put("LoaiXeID", String.valueOf(loaixeid));

                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        xes = new ArrayList<>();
        lvxe = findViewById(R.id.lvxemxe);
        spinnerlx = findViewById(R.id.spinerlxe);
        xeAdapter = new XeAdapter(getApplicationContext(),xes);
        lvxe.setAdapter(xeAdapter);
         loaiXes = new ArrayList<>();
        loaiXe_adapter = new Spinnerloaixe(getApplicationContext(),loaiXes);
        spinnerlx.setAdapter(loaiXe_adapter);

        spinnertrangthai = findViewById(R.id.spinnertrangthai);



        String[] items = {"Hãy chọn trạng thái", "có sẳn", "đang thuê", "đã đặt trước", "không hoạt động"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertrangthai.setAdapter(adapter);

    }


    private void getDatatheotrangthai(String tinhtrangxe) {
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
if(tinhtrangxe.equals(TinhTrangXe)){
    xes.add(new Xe(LoaiXeID, TinhTrangXe, GiaXe, hinhanhxe, XeID,tenxe));
}

                    }

                    xeAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(XemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
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
                Toast.makeText(XemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }

}