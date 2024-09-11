package com.example.app_thue_xe_cnpmnc.DatXe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.HopDong2.Add_HopDong;
import com.example.app_thue_xe_cnpmnc.HopDong2.XemHopDong;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.example.app_thue_xe_cnpmnc.Xe.XemXe;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Add_DatXe extends AppCompatActivity {
    Calendar calendar2,calendarngnhan,calendarngaytra;
    TextView tvngaydatxe,tvngaynhanxe,tvngaytraxe;
    String maxe,makh,tinhtrang;
    int giatien;
    Button btndatxe;
    Date ngayDatDate,ngayNhanXeDate,ngayTraXeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dat_xe);
AnhXa();
        Intent intent = getIntent();

        maxe = intent.getStringExtra("MA_XE");
        makh =   intent.getStringExtra("MA_KH");
        giatien =   intent.getIntExtra("GiaXe",0);
        tinhtrang =   intent.getStringExtra("TINH_TRANG");

        btndatxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(tinhtrang != null ) {
                    Toast.makeText(getApplicationContext(), "Đã chọn" +tinhtrang, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_DatXe.this);
                    builder.setTitle("Bạn muốn xác nhận đặt xe");
                    builder.setMessage("Bạn có muốn lưu thay đổi không?");

// Nút tích cực (Positive button)
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SuaXe(Integer.parseInt(maxe));
                            Datxe_onclick();
                            Intent intent1 = new Intent(Add_DatXe.this,DatXe_Activity.class);
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


                }else if(tinhtrang != null &&tinhtrang.equals("đang thuê")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_DatXe.this);
                    builder.setTitle("Xe đang được sử dụng, bạn có muốn đặt trc không  ");
                    builder.setMessage("Bạn có muốn lưu thay đổi không?");

// Nút tích cực (Positive button)
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Datxe_onclick();
                            Intent intent1 = new Intent(Add_DatXe.this, XemXe.class);
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
                 else {
                     Toast.makeText(getApplicationContext(), "Có lỗi : " +tinhtrang, Toast.LENGTH_SHORT).show();
                 }
            }
        });


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
                Toast.makeText(Add_DatXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Tạo một Map để chứa các tham số cần truyền đi
                Map<String, String> params = new HashMap<>();
                params.put("XeID", String.valueOf(IdDatxe));
                params.put("TinhTrangXe", "đang thuê");
                return params;
            }
        };

// Thêm yêu cầu vào RequestQueue để gửi đi
        requestQueue.add(stringRequest);

    }

    private void Datxe_onclick() {
        final int khachHangID = Integer.parseInt(makh); // Thay thế "123" bằng giá trị thích hợp.
        final int xeID = Integer.parseInt(maxe); // Thay thế "456" bằng giá trị thích hợp.
        final String ngayDat = tvngaydatxe.getText().toString();
        final String ngayNhanXe = tvngaynhanxe.getText().toString(); // Thay thế "2023-10-22" bằng ngày thích hợp.
        final String ngayTraXe = tvngaytraxe.getText().toString(); // Thay thế "2023-10-23" bằng ngày thích hợp.
        final String trangThai = "Chờ xác nhận"; // Thay thế "Chờ xác nhận" bằng giá trị trạng thái thích hợp.

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.themdatxe, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Xử lý kết quả trả về từ server
                Toast.makeText(getApplicationContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Xử lý lỗi khi gửi yêu cầu tới server
                Toast.makeText(Add_DatXe.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ngayDatDate = sdf.parse(ngayDat);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                try {
                    ngayNhanXeDate = sdf.parse(ngayNhanXe);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                try {
                    ngayTraXeDate = sdf.parse(ngayTraXe);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String ngayDatFormatted = sdf.format(ngayDatDate);
                String ngayNhanXeFormatted = sdf.format(ngayNhanXeDate);
                String ngayTraXeFormatted = sdf.format(ngayTraXeDate);

                params.put("KhachHangID", String.valueOf(khachHangID));
                params.put("XeID", String.valueOf(xeID));
                params.put("NgayDat", ngayDatFormatted);
                params.put("NgayNhanXe", ngayNhanXeFormatted);
                params.put("NgayTraXe", ngayTraXeFormatted);
                params.put("TrangThai", trangThai);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        tvngaydatxe = findViewById(R.id.btntest);
        tvngaynhanxe = findViewById(R.id.tvngaynhanxe_datxe);
        tvngaytraxe = findViewById(R.id.tvngaytraxe_datxe);
btndatxe = findViewById(R.id.btndatxe);
        calendar2 = Calendar.getInstance();
    }

    private void showAlertDialogngaytra() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate layout custom vào dialog
        View dialogView = getLayoutInflater().inflate(R.layout.custom_alerdialog, null);
        builder.setView(dialogView);

        // Lấy reference của CalendarView trong dialogView
        CalendarView calendarView = dialogView.findViewById(R.id.calendarView_datxe);

        // Thêm nút "Đóng" vào AlertDialog
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng AlertDialog
                dialog.dismiss();
            }
        });

        // Hiển thị AlertDialog
        builder.show();

        // Set OnDateChangeListener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                 calendarngaytra = Calendar.getInstance();
                boolean isDay = true;
                // Đặt ngày thứ hai thành 10/10/2023
                calendarngaytra.set(year, month, dayOfMonth);

                // So sánh ngày, tháng và năm
                if (calendarngaytra.before(calendarngnhan)) {
                    Toast.makeText(Add_DatXe.this, "Ngày trả phải sau ngày nhận", Toast.LENGTH_SHORT).show();
                    isDay = false;
                    calendarngaytra = Calendar.getInstance();
                } else {
                    // Ngày trả hợp lệ, xử lý logic tiếp theo (nếu có)
                }

                if (isDay) {
                    int month2 = calendarngaytra.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
                    int day2 = calendarngaytra.get(Calendar.DAY_OF_MONTH);
                    int year2 = calendarngaytra.get(Calendar.YEAR);

                    tvngaytraxe.setText(year2+ "/" + month2 + "/" + day2 );
                }
            }
        });
    }

    private void showAlertDialogngaynhan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate layout custom vào dialog
        View dialogView = getLayoutInflater().inflate(R.layout.custom_alerdialog, null);
        builder.setView(dialogView);

        // Lấy reference của CalendarView trong dialogView
        CalendarView calendarView = dialogView.findViewById(R.id.calendarView_datxe);

        // Thêm nút "Đóng" vào AlertDialog
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng AlertDialog
                dialog.dismiss();
            }
        });

        // Hiển thị AlertDialog
        builder.show();

        // Set OnDateChangeListener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                Calendar calendar1 = Calendar.getInstance();
                calendarngnhan = Calendar.getInstance();
                boolean isDay = true;
                // Đặt ngày thứ hai thành 10/10/2023
                calendarngnhan.set(year, month, dayOfMonth);

                // So sánh ngày, tháng và năm
                if (calendarngnhan.before(calendar1)) {
                    Toast.makeText(Add_DatXe.this, "Không được chọn trước ngày hiện tại", Toast.LENGTH_SHORT).show();
                    isDay = false;
                    calendarngnhan = Calendar.getInstance();
                } else if (calendarngnhan.equals(calendar1)) {
                    Toast.makeText(Add_DatXe.this, "Ngày 1 bằng ngày hiện tại", Toast.LENGTH_SHORT).show();
                } else {
                    // Các trường hợp khác
                }

                if (isDay) {
                    int month2 = calendarngnhan.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
                    int day2 = calendarngnhan.get(Calendar.DAY_OF_MONTH);
                    int year2 = calendarngnhan.get(Calendar.YEAR);

                    tvngaynhanxe.setText(year2 + "/" + month2 + "/" + day2);

                }
            }
        });
    }




    public void onclick_ChonNgayDatXe(View view) {
         calendar2 = Calendar.getInstance();
        int month2 = calendar2.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cần cộng thêm 1
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        int year2 = calendar2.get(Calendar.YEAR);
        tvngaydatxe.setText(year2 + "/" + month2 + "/" + day2);

    }

    public void Ngaynhanxe_onlcick(View view) {
        showAlertDialogngaynhan();


    }

    public void Ngaytraxe_onclick(View view) {
        showAlertDialogngaytra();
    }
}