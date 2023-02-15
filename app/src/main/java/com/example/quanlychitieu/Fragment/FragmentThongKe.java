package com.example.quanlychitieu.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.quanlychitieu.Adapter.KhoanChiAdapter;
import com.example.quanlychitieu.DAO.KhoanChiDAO;
import com.example.quanlychitieu.DAO.KhoanThuDAO;
import com.example.quanlychitieu.DAO.LoaiChiDAO;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiChi;
import com.example.quanlychitieu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentThongKe extends Fragment {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    KhoanChiDAO khoanChiDAO;
    KhoanThuDAO khoanThuDAO;
    float tongthu=0,tongchi=0;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public FragmentThongKe() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        khoanChiDAO = new KhoanChiDAO(getContext());
        khoanThuDAO = new KhoanThuDAO(getContext());
        Date d = new Date();
        ArrayList<KhoanChi> listchi =khoanChiDAO.getByDate(simpleDateFormat.format(d));
        for (int i= 0;i<listchi.size();i++)
        {
            tongchi = tongchi + listchi.get(i).getSoTien();
        }

        ArrayList<KhoanThu> listthu =khoanThuDAO.getByDate(simpleDateFormat.format(d));
        for (int i= 0;i<listthu.size();i++){
            tongthu = tongthu + listthu.get(i).getSoTien();
        }


        barChart = view.findViewById(R.id.bar_chart);
        ArrayList<BarEntry> visitors = new ArrayList<>();

        visitors.add(new BarEntry(19, tongchi));
        visitors.add(new BarEntry(22, tongthu));

        barDataSet = new BarDataSet(visitors, "Với Màu Xanh Là Tổng Thu Và Màu Vàng Là Tổng Chi");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(15f);

        barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bảng Thống Kê Thu Chi");
        barChart.animateY(2000);

        return view;
    }
}