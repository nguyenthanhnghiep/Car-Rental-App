package com.example.app_thue_xe_cnpmnc.Xe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
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
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_adapter;
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

public class ThemXe extends AppCompatActivity {
    private EditText edtTenXe, edtMaAnhXe, edtGiaXe;
    private EditText edtTenXe2, edtMaAnhXe2, edtGiaXe2;
    private Spinner spinnerLoaiXe, spinnerTrangThai;
    private Spinner spinnerLoaiXe2, spinnerTrangThai2;
    private ListView listViewXe;
    private Button btnSuaXe, btnThemXe, btnXoaXe;
    XeAdapter xeAdapter;
    ArrayList<Xe> xes;
    Spinnerloaixe loaiXe_adapter;

    ArrayList<Loaixe_spinner> loaiXes;
    int idxe;
    int idxe2;
    String trangthai;
    String trangthai2;
    Button btnsuaxett , btnxoaxett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_xe);

        AnhXa();
        Spinneradd();
        Getalldulieu();



        spinnerLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int loaixeid = xes.get(i).idxe;
                    idxe  = loaixeid;
                Toast.makeText(ThemXe.this, "Đã chọn: " + idxe, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý khi một mục được chọn
                if(position == 0)
                {
                    Getalldulieu();
                }
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(ThemXe.this, "Đã chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
               trangthai = selectedItem;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn
            }
        });
        btnThemXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tenxe = edtTenXe.getText().toString().trim();
                final int giaxe = edtGiaXe.getText().length();
                final String anhxe = edtMaAnhXe.getText().toString().trim();
// Tạo một RequestQueue để gửi các yêu cầu tới server
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themxe, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Xử lý kết quả trả về từ server
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            xes.clear();
                            Getalldulieu();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Xử lý lỗi khi gửi yêu cầu tới server
                            Toast.makeText(ThemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            // Tạo một Map để chứa các tham số cần truyền đi
                            Map<String, String> params = new HashMap<>();
                            params.put("LoaiXeID", String.valueOf(idxe));
                            params.put("TenXe", tenxe);
                            params.put("GiaXe", String.valueOf(giaxe));
                            params.put("AnhXe", anhxe);
                            params.put("TinhTrangXe", trangthai);
                            return params;
                        }
                    };

// Thêm yêu cầu vào RequestQueue để gửi đi
                    requestQueue.add(stringRequest);
                }

        });
       listViewXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               AlertDialog.Builder builder = new AlertDialog.Builder(ThemXe.this);
               // Inflate layout custom vào dialog
               View dialogView = getLayoutInflater().inflate(R.layout.aler_suaxe, null);
               builder.setView(dialogView);
Spinneradd();
               edtTenXe2 = dialogView.findViewById(R.id.edttenxe_aler);
               edtMaAnhXe2 = dialogView.findViewById(R.id.edtanhxe_aler);
               edtGiaXe2 = dialogView.findViewById(R.id.edtgiaxe_aler);
               spinnerLoaiXe2 = dialogView.findViewById(R.id.spinnerloaixe_quanly_aler);
               btnsuaxett = dialogView.findViewById(R.id.btnsuaxett);
               btnxoaxett = dialogView.findViewById(R.id.btnxoaxett);
               spinnerTrangThai2 = dialogView.findViewById(R.id.spinnertrangthai_quanly_aler);

               edtTenXe2.setText(xes.get(i).getTenxe());
               edtGiaXe2.setText(String.valueOf(xes.get(i).getGiatien()));
               edtMaAnhXe2.setText(xes.get(i).getAnhxe());

               String TenXe2,MaAnhXe2;
               int GiaXe2;
               TenXe2 = edtTenXe2.getText().toString().trim();
               MaAnhXe2 = edtMaAnhXe2.getText().toString().trim();

               GiaXe2=   Integer.parseInt(edtGiaXe2.getText().toString());
               loaiXes = new ArrayList<>();
               loaiXe_adapter = new Spinnerloaixe(getApplicationContext(),loaiXes);
               spinnerLoaiXe2.setAdapter(loaiXe_adapter);

               // Make sure that getIdloaixe() returns a valid position in loaiXes
               int selectedPosition = xes.get(i).getIdloaixe();

// Make sure the selected position is within the bounds of the list
               if (selectedPosition >= 0 && selectedPosition < loaiXes.size()) {
                   spinnerLoaiXe2.setSelection(selectedPosition);
               } else {
                   Toast.makeText(ThemXe.this, "Có lỗi xảy ra: " + loaiXes.size(), Toast.LENGTH_SHORT).show();
               }

               String[] items = {"Hãy chọn trạng thái", "có sẳn", "đang thuê", "đã đặt trước", "không hoạt động"};


               ArrayAdapter<String> adapter = new ArrayAdapter<>(ThemXe.this, android.R.layout.simple_spinner_item, items);
               adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinnerTrangThai2.setAdapter(adapter);
               spinnerLoaiXe2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       int loaixeid = xes.get(i).idloaixe;
                       idxe2  = loaixeid;

                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });
               spinnerTrangThai2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       // Xử lý khi một mục được chọn
                       if(position == 0)
                       {
                           Getalldulieu();
                       }
                       String selectedItem = parent.getItemAtPosition(position).toString();

                       trangthai2 = selectedItem;


                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {
                       // Xử lý khi không có mục nào được chọn
                   }
               });

               // Thêm nút "Đóng" vào AlertDialog
               btnsuaxett.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

// Tạo một RequestQueue để gửi các yêu cầu tới server
                       RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                       StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.suaxe2, new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               // Xử lý kết quả trả về từ server
                               Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                               xes.clear();
                               Getalldulieu();
                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               // Xử lý lỗi khi gửi yêu cầu tới server
                               Toast.makeText(ThemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                               error.printStackTrace();
                           }
                       }) {
                           @Nullable
                           @Override
                           protected Map<String, String> getParams() throws AuthFailureError {
                               // Tạo một Map để chứa các tham số cần truyền đi
                               Map<String, String> params = new HashMap<>();
                                params.put("XeID", String.valueOf(xes.get(i).idxe));
                               params.put("LoaiXeID", String.valueOf(idxe2));
                               params.put("TenXe", TenXe2);
                               params.put("GiaXe", String.valueOf(GiaXe2));
                               params.put("AnhXe", MaAnhXe2);
                               params.put("TinhTrangXe", trangthai2);
                               return params;
                           }
                       };

// Thêm yêu cầu vào RequestQueue để gửi đi
                       requestQueue.add(stringRequest);

                   }
               });
               btnxoaxett.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
                       StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.xoaxe, new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               // Xử lý kết quả trả về từ server
                               Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                               xes.clear();
                               Getalldulieu();
                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               // Xử lý lỗi khi gửi yêu cầu tới server
                               Toast.makeText(ThemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                               error.printStackTrace();
                           }
                       }) {
                           @Nullable
                           @Override
                           protected Map<String, String> getParams() throws AuthFailureError {
                               // Tạo một Map để chứa các tham số cần truyền đi
                               Map<String, String> params = new HashMap<>();
                               params.put("XeID", String.valueOf(xes.get(i).idxe));

                               return params;
                           }
                       };

// Thêm yêu cầu vào RequestQueue để gửi đi
                       requestQueue.add(stringRequest);

                   }
               });

               builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       // Đóng AlertDialog
                       dialog.dismiss();

                   }
               });


               // Hiển thị AlertDialog
               builder.show();
           }
       });

    }

    private void Getalldulieu() {
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
                Toast.makeText(ThemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void Spinneradd() {

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
                Toast.makeText(ThemXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void AnhXa() {
        edtTenXe = findViewById(R.id.edttenxe);
        edtMaAnhXe = findViewById(R.id.edtanhxe);
        edtGiaXe = findViewById(R.id.edtgiaxe);
        spinnerLoaiXe = findViewById(R.id.spinnerloaixe_quanly);
        loaiXes = new ArrayList<>();
        loaiXe_adapter = new Spinnerloaixe(getApplicationContext(),loaiXes);
        spinnerLoaiXe.setAdapter(loaiXe_adapter);

        spinnerTrangThai = findViewById(R.id.spinnertrangthai_quanly);
        String[] items = {"Hãy chọn trạng thái", "có sẳn", "đang thuê", "đã đặt trước", "không hoạt động"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrangThai.setAdapter(adapter);
xes = new ArrayList<>();
        listViewXe = findViewById(R.id.listview_xe);
        xeAdapter = new XeAdapter(getApplicationContext(),xes);
        listViewXe.setAdapter(xeAdapter);
        btnSuaXe = findViewById(R.id.btnsuaxe);
        btnThemXe = findViewById(R.id.btnthemxe);
        btnXoaXe = findViewById(R.id.btnxoaxe);
    }
}