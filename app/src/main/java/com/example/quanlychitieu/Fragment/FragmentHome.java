package com.example.quanlychitieu.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.quanlychitieu.Adapter.KhoanChiAdapter;
import com.example.quanlychitieu.DAO.KhoanChiDAO;
import com.example.quanlychitieu.DAO.LoaiChiDAO;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.LoaiChi;
import com.example.quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment {
    RecyclerView recyclerViewKhoanChi;
    KhoanChiAdapter adapter;
    KhoanChiDAO khoanChiDAO;
    ArrayList<KhoanChi> arrayListKhoanChi;
    ArrayList<LoaiChi> list = new ArrayList<LoaiChi>();
    LoaiChiDAO loaiChiDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    TextView tvTong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewKhoanChi = view.findViewById(R.id.recyclerView_ctHomnay);
        tvTong = view.findViewById(R.id.tvTongTien);
        //data
        khoanChiDAO = new KhoanChiDAO(getContext());
        loaiChiDAO = new LoaiChiDAO(getContext());
        //
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewKhoanChi.setLayoutManager(layoutManager);
        arrayListKhoanChi = new ArrayList<>();
        Date d = new Date();
        arrayListKhoanChi = khoanChiDAO.getByDate(simpleDateFormat.format(d));
        adapter = new KhoanChiAdapter(getContext(),arrayListKhoanChi);
        recyclerViewKhoanChi.setAdapter(adapter);

        //tính tổng tiền
        tvTong.setText("Tổng chi tiêu: " + TinhTong(arrayListKhoanChi) + " VNĐ");

        return view;
    }
    //hàm tính tổng tiền
    private float TinhTong(ArrayList<KhoanChi> arrayListKhoanChi){
        float t=0;
        for(KhoanChi i:arrayListKhoanChi)
        {
            t+=i.getSoTien();
        }
        return t;
    }

}