package com.example.app_thue_xe_cnpmnc.HopDong2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.app_thue_xe_cnpmnc.DatXe.Add_DatXe;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe;
import com.example.app_thue_xe_cnpmnc.DatXe.DatXe_Activity;
import com.example.app_thue_xe_cnpmnc.DatXe.DatxeAdapter;
import com.example.app_thue_xe_cnpmnc.HoaDon.HoaDon_Activity;
import com.example.app_thue_xe_cnpmnc.KhachHang.Add_KhachHang;
import com.example.app_thue_xe_cnpmnc.LoaiXe.LoaiXe_Activity;
import com.example.app_thue_xe_cnpmnc.Main2Activity;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.Xe.XemXe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class XemHopDong extends AppCompatActivity {
TextView tvidhopdong,tvngaylaphd,tvnoinhanxe,tvnoitraxe,tvtiencoc,tvtiencocconlai,tvtrangthai;
int idhopdong;
String ngaylaphopdong,noinhanxe,noitraxe,tiencoc,tiencocconlai,trangthai;
ListView lvhopdong;
int idxe;
    String idhopdong2;
ArrayList<DatXe> datXes;
ArrayList<HopDong> hopDongs;
HopDong_adapter hopDong_adapter;
    DatxeAdapter datxeAdapter;
    Date ngayhientai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hop_dong);
        datXes = new ArrayList<>();
        hopDongs = new ArrayList<>();
        AnhXa();
        getData();





lvhopdong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(XemHopDong.this);
        builder.setTitle("Bạn muốn xác nhận đặt xe");
        builder.setMessage("Bạn có muốn lưu thay đổi không?");

// Nút tích cực (Positive button)
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int iddatxe = hopDongs.get(i).iddatxe;


                HoanThanhHopDong(hopDongs.get(i).idhopdong);
                SuaTTDatXe(hopDongs.get(i).iddatxe);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getdatxe, new com.android.volley.Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            for (int j = 0; j < response.length(); j++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(j);

                                    int datXeID = jsonObject.getInt("DatXeID");
                                    int khachHangID = jsonObject.getInt("KhachHangID");
                                    int loaiXeID = jsonObject.getInt("XeID");
                                    String ngayDat = jsonObject.getString("NgayDat");
                                    String ngayNhanXe = jsonObject.getString("NgayNhanXe");
                                    String ngayTraXe = jsonObject.getString("NgayTraXe");
                                    String trangThai = jsonObject.getString("TrangThai");
                                    if(datXeID == iddatxe) {
                                        datXes.add(new DatXe(datXeID, khachHangID, loaiXeID, ngayDat, ngayNhanXe, ngayTraXe, trangThai));
                                        idxe = loaiXeID;
                                        String ngayNhanXe2 = ngayNhanXe;
                                        String ngayTraXe2 = ngayNhanXe;
                                        int idhopdong2 = hopDongs.get(i).idhopdong;
                                        SuaXe(idxe);
                                       SuaTTDatXe(datXeID);
                                       ThemSoXe(idxe,ngayNhanXe2,ngayTraXe2,idhopdong2);

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
                        Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }


                });
                requestQueue.add(jsonArrayRequest);

                    SuaHoaDom(hopDongs.get(i).idhopdong,hopDongs.get(i).TienCoc);

               HoanThanhHopDong(hopDongs.get(i).idhopdong);
                Intent intent1 = new Intent(XemHopDong.this, HoaDon_Activity.class);
                idhopdong2 = String.valueOf(hopDongs.get(i).idhopdong);
                intent1.putExtra("MA_HD", hopDongs.get(i).idhopdong);
               startActivity(intent1);

            }


        });

// Nút tiêu cực (Negative button)
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

// Tạo và hiển thị AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
});

    }

    private void ThemSoXe(int idxe2,String ngayNhanXe2,String ngayTraXe2,int idhopdong2) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themsoxe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server

                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " +idxe2+"/"+ngayNhanXe2+"/"+ngayTraXe2+"/"+idhopdong2, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi
                Map<String, String> params = new HashMap<>();
                params.put("XeID", String.valueOf(idxe2));
                params.put("NgayThue", ngayNhanXe2);
                params.put("NgayTra", ngayTraXe2);
                params.put("HopDongID", String.valueOf(idhopdong2));

                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);

    }

    private void SuaTTDatXe(int idxe) {

// Tạo một RequestQueue để gửi các yêu cầu tới server
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.suadatxe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi
                Map<String, String> params = new HashMap<>();
                params.put("DatXeID", String.valueOf(idxe));
                params.put("TrangThai", "Hoàn thành");
                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);


    }




    private void SuaXe(int IdDatxe) {

/// Tạo một RequestQueue để gửi các yêu cầu tới server
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Tạo một StringRequest để gửi yêu cầu POST đến server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.suaxe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi
                Map<String, String> params = new HashMap<>();
                params.put("XeID", String.valueOf(IdDatxe));
                params.put("TinhTrangXe", "có sẵn");
                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);

    }

    private void HoanThanhHopDong(int idhopdong2) {
        // Khởi tạo RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// URL của máy chủ


// Khởi tạo StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.suahopdong, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String status = jsonResponse.getString("status");
                    String message = jsonResponse.getString("message");

                    if (status.equals("success")) {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra: " + message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("HopDongID", String.valueOf(idhopdong2)); // Thay bằng giá trị thích hợp
                params.put("TrangThaiHopDong", "Hoàn Thành"); // Thay bằng giá trị thích hợp
                return params;
            }
        };

// Thêm request vào hàng đợi
        requestQueue.add(stringRequest);

    }
    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.gethopdong, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int hopDongID = jsonObject.getInt("HopDongID");
                            int datXeID = jsonObject.getInt("DatXeID");
                            String ngayLapHopDong = jsonObject.getString("NgayLapHopDong");
                            String diaDiemNhanXe = jsonObject.getString("DiaDiemNhanXe");
                            String diaDiemTraXe = jsonObject.getString("DiaDiemTraXe");
                            int tienCoc = jsonObject.getInt("TienCoc");
                            int tienConLai = jsonObject.getInt("TienConLai");
                            String trangThaiHopDong = jsonObject.getString("TrangThaiHopDong");

                            // Tạo đối tượng HopDong từ dữ liệu lấy về
                            HopDong hopDong = new HopDong(diaDiemTraXe, tienCoc, tienConLai, trangThaiHopDong, hopDongID, datXeID, ngayLapHopDong, diaDiemNhanXe);

                            // Thêm hopDong vào danh sách mangHopDong
                            hopDongs.add(hopDong);
                        }

                        // Cập nhật giao diện sau khi có dữ liệu
                        hopDong_adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        } );
        requestQueue.add(jsonArrayRequest);
    }

    private void ThemHoaDon(int idhopdong3,int tongtien,int dathanhtoan) {

        Calendar calendar = Calendar.getInstance();
        String selectedDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        ngayhientai = new Date();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themhoadon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi
                Map<String, String> params = new HashMap<>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


                try {
                    ngayhientai = sdf.parse(selectedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String ngayDatFormatted = sdf.format(ngayhientai);
                params.put("HopDongID", String.valueOf(idhopdong3));
                params.put("NgayLapHoaDon", ngayDatFormatted);
                params.put("TongTien", String.valueOf(tongtien));
                params.put("DaThanhToan", String.valueOf(dathanhtoan));

                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);

    }
    private void SuaHoaDom(int idhopdong4,int tienconlai) {


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.suahoadon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(XemHopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi
                Map<String, String> params = new HashMap<>();

                params.put("IDhopdong", String.valueOf(2));

                params.put("Tienconlai", String.valueOf(100));


                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);

    }


    private void AnhXa() {
        lvhopdong = findViewById(R.id.lvhopdong);
        hopDong_adapter = new HopDong_adapter(getApplicationContext(),hopDongs);
        lvhopdong.setAdapter(hopDong_adapter);



    }
}