package com.example.app_thue_xe_cnpmnc.QuanLy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_thue_xe_cnpmnc.HopDong2.HopDong;
import com.example.app_thue_xe_cnpmnc.HopDong2.XemHopDong;
import com.example.app_thue_xe_cnpmnc.R;
import com.example.app_thue_xe_cnpmnc.Sever.Sever;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Barchart_activity extends AppCompatActivity implements OnChartValueSelectedListener {
    int tienquy1,tienquy2,tienqy3,tienquy4 ;
    int tienquythang1, tienquythang2, tienquythang3;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        GetData();
        SetupBarchart();


    }



    private void GetData() {

    }

    private void SetupBarchart() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.gethopdong, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    tienquy1 = 0;
                    tienquy2 = 0;
                    tienqy3 = 0;
                    tienquy4 =0;
                    try {
                        for (int i = 0; i < response.length(); i++) {

                            JSONObject jsonObject = response.getJSONObject(i);
                            String ngayLapHopDong = jsonObject.getString("NgayLapHopDong");
                            int tienCoc = jsonObject.getInt("TienCoc");

// Định dạng của chuỗi ngày tháng
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                            try {
                                // Chuyển đổi chuỗi thành đối tượng Date
                                Date date = sdf.parse(ngayLapHopDong);

                                // Tạo đối tượng Calendar và thiết lập giá trị của ngày tháng
                                calendar = Calendar.getInstance();
                                calendar.setTime(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                            if (calendar.get(Calendar.MONTH) >= Calendar.JANUARY && calendar.get(Calendar.MONTH) <= Calendar.MARCH) {
                                tienquy1 += tienCoc;
                            } else if (calendar.get(Calendar.MONTH) >= Calendar.APRIL && calendar.get(Calendar.MONTH) <= Calendar.JUNE) {
                                tienquy2 += tienCoc;
                            } else if (calendar.get(Calendar.MONTH) >= Calendar.JULY && calendar.get(Calendar.MONTH) <= Calendar.SEPTEMBER) {
                                tienqy3 += tienCoc;
                            } else if (calendar.get(Calendar.MONTH) >= Calendar.OCTOBER && calendar.get(Calendar.MONTH) <= Calendar.DECEMBER) {
                                tienquy4 += tienCoc;
                            }


                        }

                        ArrayList<String> labels = new ArrayList<>();
                        labels.add("Quý 1");
                        labels.add("Quý 1");
                        labels.add("Quý 2");
                        labels.add("Quý 3");
                        labels.add("Quý 4");


                        // Khởi tạo GraphView từ layout
                        LinearLayout chartLayout = findViewById(R.id.chartLayout);

                        // Tạo biểu đồ cột
                        BarChart barChart = new BarChart(Barchart_activity.this);
                        // Số lượng label
                        int labelCount = labels.size();

                        // Mã hiện có của bạn để thiết lập BarChart
                        barChart.setOnChartValueSelectedListener(Barchart_activity.this);


// Đặt số lượng chú thích và kích thước chữ của chú thích trên trục x
                        barChart.getXAxis().setLabelCount(labelCount);
                        barChart.getXAxis().setTextSize(10f);
// Thiết lập chiều rộng và chiều cao cho BarChart
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                        );
                        barChart.setLayoutParams(params);
                        // Xác định kích thước chữ cho nhãn trục x và trục y
                        barChart.getXAxis().setTextSize(20f); // Kích thước chữ của nhãn trục x
                        barChart.getAxisLeft().setTextSize(20f); // Kích thước chữ của nhãn trục y
// Xác định kích thước chữ cho tiêu đề của biểu đồ
                        barChart.getDescription().setTextSize(20f);

                        // Kích thước chữ của tiêu đề biểu đồ


// Thêm biểu đồ vào layout

                        chartLayout.addView(barChart);

// Chuẩn bị dữ liệu cho từng dòng dữ liệu
                        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
                        barEntries1.add(new BarEntry(1, tienquy1));
                        barEntries1.add(new BarEntry(2, tienquy2));
                        barEntries1.add(new BarEntry(3, tienqy3));
                        barEntries1.add(new BarEntry(4, tienquy4));


                        barChart.getDescription().setText("");


// Tạo BarDataSet cho mỗi dòng dữ liệu với màu sắc khác nhau
                        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "Giá trị: triệu đồng");
                        barDataSet1.setColor(Color.BLUE); // Thiết lập màu sắc cho dòng dữ liệu 1
                        barDataSet1.setValueTextSize(20f);

                        BarData barData = new BarData(barDataSet1);
                        barData.setBarWidth(0.4f); // Để tạo khoảng cách giữa các thanh cột

                        barData.setValueTextSize(18f); // Thiết lập kích cỡ chữ cho giá trị trên thanh cột


// Thiết lập BarData cho biểu đồ
                        barChart.setData(barData);
// Thiết lập chú thích cho trục x
                        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
                        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                        barChart.getXAxis().setGranularity(1f); // Để không bị mất chú thích
                        barChart.getXAxis().setLabelCount(labels.size()); // Đặt số lượng chú thích
                        barChart.getXAxis().setTextSize(10f); // Kích cỡ chữ của chú thích

// Cài đặt cho biểu đồ
                        barChart.setFitBars(true); // Để cân đối cột theo chiều rộng
                        barChart.invalidate(); // Hiển thị biểu đồ

                        // Cập nhật giao diện sau khi có dữ liệu


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Barchart_activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        } );
        requestQueue.add(jsonArrayRequest);


    }
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        // Xử lý sự kiện khi cột được chọn ở đây
        if (e == null) return;

        BarEntry entry = (BarEntry) e; // Lấy BarEntry đã chọn
        int index = (int) entry.getX(); // Lấy chỉ số của cột đã chọn

        // Thực hiện các hành động dựa trên cột được chọn, ví dụ:
        Toast.makeText(this, "Đã chọn cột có chỉ số: " + index, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate layout custom vào dialog
        View dialogView = getLayoutInflater().inflate(R.layout.doanhthu_aler, null);
        builder.setView(dialogView);

        // Lấy reference của CalendarView trong dialogView
        TextView tvthang1 = dialogView.findViewById(R.id.tvthang1);
        TextView tvthang2 = dialogView.findViewById(R.id.tvthang2);
        TextView tvthang3 = dialogView.findViewById(R.id.tvthang3);
        TextView tvdoanhthuquy = dialogView.findViewById(R.id.tvdoanhthuquy);
        //Lấy data
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.gethopdong, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            String ngayLapHopDong = jsonObject.getString("NgayLapHopDong");

                            int tienCoc = jsonObject.getInt("TienCoc");

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                            try {
                                // Chuyển đổi chuỗi thành đối tượng Date
                                Date date = sdf.parse(ngayLapHopDong);

                                // Tạo đối tượng Calendar và thiết lập giá trị của ngày tháng
                                calendar = Calendar.getInstance();
                                calendar.setTime(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                            if(index == 1){

                                if (calendar.get(Calendar.MONTH) == Calendar.JANUARY ) {
                                    tienquythang1 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY ) {
                                    tienquythang2 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.MARCH ) {
                                    tienquythang3 += tienCoc;
                                }
                                tvthang1.setText(String.valueOf("Tháng 1: "+decimalFormat.format(tienquythang1))+" nghìn VNĐ đồng");
                                tvthang2.setText(String.valueOf("Tháng 2: "+decimalFormat.format(tienquythang2))+" nghìn VNĐ đồng");
                                tvthang3.setText(String.valueOf("Tháng 3: "+decimalFormat.format(tienquythang3))+" nghìn VNĐ đồng");
                            }if(index == 2){

                                if (calendar.get(Calendar.MONTH) == Calendar.APRIL ) {
                                    tienquythang1 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.MAY ) {
                                    tienquythang2 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.JUNE ) {
                                    tienquythang3 += tienCoc;
                                }
                                tvthang1.setText(String.valueOf("Tháng 4: "+decimalFormat.format(tienquythang1))+" nghìn VNĐ đồng");
                                tvthang2.setText(String.valueOf("Tháng 5: "+decimalFormat.format(tienquythang2))+" nghìn VNĐ đồng");
                                tvthang3.setText(String.valueOf("Tháng 6: "+decimalFormat.format(tienquythang3))+" nghìn VNĐ đồng");
                            }if(index == 3){

                                if (calendar.get(Calendar.MONTH) == Calendar.JULY ) {
                                    tienquythang1 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.AUGUST ) {
                                    tienquythang2 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER ) {
                                    tienquythang3 += tienCoc;
                                }
                                tvthang1.setText(String.valueOf("Tháng 7: "+decimalFormat.format(tienquythang1))+" nghìn VNĐ đồng");
                                tvthang2.setText(String.valueOf("Tháng 8: "+decimalFormat.format(tienquythang2))+" nghìn VNĐ đồng");
                                tvthang3.setText(String.valueOf("Tháng 9: "+decimalFormat.format(tienquythang3))+" nghìn VNĐ đồng");
                            }if(index == 4){

                                if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER ) {
                                    tienquythang1 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER ) {
                                    tienquythang2 += tienCoc;
                                }if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER ) {
                                    tienquythang3 += tienCoc;
                                }

                                tvthang1.setText(String.valueOf("Tháng 10: "+decimalFormat.format(tienquythang1))+" nghìn VNĐ đồng");
                                tvthang2.setText(String.valueOf("Tháng 11: "+decimalFormat.format(tienquythang2))+" nghìn VNĐ đồng");
                                tvthang3.setText(String.valueOf("Tháng 12: "+decimalFormat.format(tienquythang3))+" nghìn VNĐ đồng");
                            }



                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Barchart_activity.this, "Có lỗi xảy ra: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        } );
        requestQueue.add(jsonArrayRequest);
        tienquythang1 = 0;
        tienquythang2 = 0;
        tienquythang3 = 0;
        tvdoanhthuquy.setText("Doanh Thu Của Quý " + index);
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

    }

    @Override
    public void onNothingSelected() {
        // Xử lý khi không có cột nào được chọn
    }


}