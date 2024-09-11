package com.example.app_thue_xe_cnpmnc.HopDong2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.HoaDon.HoaDon_Activity;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.Xe.Xe;
import com.example.app_thue_xe_cnpmnc.Xe.XemXe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Add_HopDong extends AppCompatActivity {
    Calendar calendar2;
    Date ngayhientai;
    EditText edtnoinhanxe,edtnoitraxe,edttiencoc;
    int tiencoc = 1;
    Button btndangky;
    String noinhanxe,noitraxe;
    String ngaylaphd;
CalendarView calendarView;
Date ngaylamhopdong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_hop_dong);
      AnhXa();
        Intent intent = getIntent();
       String maXe = intent.getStringExtra("MA_XE");
        int maXe2 = intent.getIntExtra("MA_XE2",0);
        int ngaydat = intent.getIntExtra("NgayNhan",0);
        Toast.makeText(getApplicationContext(), "mã xe: " + maXe2 , Toast.LENGTH_SHORT).show();
        int month2 = calendar2.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        int year2 = calendar2.get(Calendar.YEAR);

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
                        if(XeID == maXe2){
                            tiencoc = GiaXe * ngaydat;
                            edttiencoc.setText(String.valueOf(tiencoc) + " VNĐ");
                        }

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_HopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
        Toast.makeText(getApplicationContext(), "tien coc: " + tiencoc , Toast.LENGTH_SHORT).show();
        ngaylaphd = year2+ "/" + month2 + "/" + day2 ;
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noinhanxe = String.valueOf(edtnoinhanxe.getText());

                noitraxe = String.valueOf(edtnoitraxe.getText());
                final int DatXeID = Integer.parseInt(maXe);
                final String NgayLapHopDong = "Ngày lập";  // Thay bằng giá trị thích hợp
                final String DiaDiemNhanXe = noinhanxe;  // Thay bằng giá trị thích hợp
                final String DiaDiemTraXe = noitraxe;  // Thay bằng giá trị thích hợp
                final int TienCoc = tiencoc;  // Thay bằng giá trị thích hợp
                final int TienConLai = tiencoc / 2;  // Thay bằng giá trị thích hợp
                final String TrangThaiHopDong = "Đã đóng 50%";

                //Add hopdong
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                Intent intent1 = new Intent(Add_HopDong.this, HoaDon_Activity.class);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themhopdong, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý kết quả trả về từ server

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
                                            if(i == response.length() - 1){ //datxeid = Datxeid dc putextra vao
                                                ThemHoaDon(hopDongID,tienCoc,tienCoc/2);
                                                intent1.putExtra("MA_HD_DX", String.valueOf(hopDongID));
                                                startActivity(intent1);

                                            }
                                            // Thêm hopDong vào danh sách mangHopDong

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Add_HopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        } );
                        requestQueue.add(jsonArrayRequest);

                       // startActivity(intent1);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi khi gửi yêu cầu tới server
                        Toast.makeText(Add_HopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                })
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd", Locale.getDefault());
                        try {
                            ngaylamhopdong = sdf.parse(ngaylaphd);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        String ngayDatFormatted = sdf.format(ngaylamhopdong);
                        params.put("DatXeID", String.valueOf(DatXeID));
                        params.put("NgayLapHopDong", ngayDatFormatted);
                        params.put("DiaDiemNhanXe", DiaDiemNhanXe);
                        params.put("DiaDiemTraXe", DiaDiemTraXe);
                        params.put("TienCoc", String.valueOf(TienCoc));
                        params.put("TienConLai", String.valueOf(TienConLai));
                        params.put("TrangThaiHopDong", TrangThaiHopDong);

                        return params;
                    }
                };

                requestQueue.add(stringRequest);

            }
        });



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

                Toast.makeText(Add_HopDong.this, "Hóa đơn đã thêm thành công", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(Add_HopDong.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void AnhXa() {
        calendar2 = Calendar.getInstance();
        edtnoinhanxe = findViewById(R.id.edtdiemnhanxe);
        edtnoitraxe = findViewById(R.id.edtdiemtraxe);
        edttiencoc = findViewById(R.id.edttiencoc);
        btndangky = findViewById(R.id.btdangky_hd);


    }


}