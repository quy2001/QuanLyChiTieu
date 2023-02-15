package com.example.quanlychitieu.Fragment;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quanlychitieu.Adapter.KhoanThuAdapter;
import com.example.quanlychitieu.DAO.KhoanThuDAO;
import com.example.quanlychitieu.DAO.LoaiThuDAO;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiThu;
import com.example.quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentTimKiemThu extends Fragment  implements View.OnClickListener{

    RecyclerView recyclerViewTKKhoanThu;
    KhoanThuAdapter adapter;
    KhoanThuDAO khoanThuDAO;
    ArrayList<KhoanThu> arrayListKhoanThu;
    ArrayList<LoaiThu> list = new ArrayList<LoaiThu>();
    LoaiThuDAO loaiThuDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DatePickerDialog datePickerDialog;

    SearchView searchView;
    EditText edTuNgay,edDenNgay;
    Button btnTimKiem;
    TextView tvTong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tim_kiem_thu, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewTKKhoanThu = view.findViewById(R.id.recyclerView_TKThu);
        //data
        khoanThuDAO = new KhoanThuDAO(getContext());
        loaiThuDAO = new LoaiThuDAO(getContext());
        //hiển thị
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTKKhoanThu.setLayoutManager(layoutManager);
        arrayListKhoanThu = new ArrayList<>();
        arrayListKhoanThu = khoanThuDAO.getAll();
        adapter = new KhoanThuAdapter(getContext(),arrayListKhoanThu);
        recyclerViewTKKhoanThu.setAdapter(adapter);
        //
        edTuNgay = view.findViewById(R.id.edTu);
        edDenNgay = view.findViewById(R.id.edDen);
        tvTong = view.findViewById(R.id.tvTongtienThu);

        //
        searchView = view.findViewById(R.id.timkiem);
        btnTimKiem = view.findViewById(R.id.btnTimKiem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<KhoanThu> list_TimDuoc = khoanThuDAO.getByName(newText);
                adapter = new KhoanThuAdapter(getContext(), list_TimDuoc);
                adapter.notifyDataSetChanged();
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerViewTKKhoanThu.setLayoutManager(manager);
                recyclerViewTKKhoanThu.setAdapter(adapter);
                tvTong.setText("Tổng thu nhập: " + TinhTong(list_TimDuoc) + "VNĐ");
                return true;
            }
        });

        edTuNgay.setOnClickListener(this);
        edDenNgay.setOnClickListener(this);
        btnTimKiem.setOnClickListener(this);


    }
    //hàm tính tổng
    private float TinhTong(ArrayList<KhoanThu> arrayListKhoanChi){
        float t=0;
        for(KhoanThu i:arrayListKhoanChi)
        {
            t+=i.getSoTien();
        }
        return t;
    }

    @Override
    public void onClick(View v) {
        if(v==edTuNgay)
        {
            final Calendar c =Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(m>8)
                    {
                        date = d+"-"+(m+1)+"-"+y;


                    }else
                    {
                        date = d+"-0"+(m+1)+"-"+y;


                    }
                    edTuNgay.setText(date);

                }
            },year,month,day);
            dialog.show();
        }
        if(v==edDenNgay)
        {
            final Calendar c =Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(m>8)
                    {
                        date = d+"-"+(m+1)+"-"+y;


                    }else
                    {
                        date = d+"-0"+(m+1)+"-"+y;


                    }
                    edDenNgay.setText(date);
                }
            },year,month,day);
            dialog.show();
        }

        //bắt sự kiện button tìm kiếm
        if(v==btnTimKiem)
        {
            String tuNgay = edTuNgay.getText().toString();
            String denNgay = edDenNgay.getText().toString();
            if(!tuNgay.isEmpty() && !denNgay.isEmpty())
            {
                ArrayList<KhoanThu> list_TimDuoc_DATE = khoanThuDAO.getByDateFromTo(tuNgay,denNgay);
                adapter = new KhoanThuAdapter(getContext(),list_TimDuoc_DATE);
                adapter.notifyDataSetChanged();
                LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                recyclerViewTKKhoanThu.setLayoutManager(manager);
                recyclerViewTKKhoanThu.setAdapter(adapter);
                tvTong.setText("Tổng thu nhập: " + TinhTong(list_TimDuoc_DATE) + "VNĐ");
            }
        }
    }
}