package com.example.quanlychitieu.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlychitieu.Adapter.KhoanThuAdapter;
import com.example.quanlychitieu.DAO.KhoanThuDAO;
import com.example.quanlychitieu.DAO.LoaiThuDAO;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiThu;
import com.example.quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentKhoanThu extends Fragment {
    RecyclerView recyclerViewKhoanThu;
    KhoanThuAdapter adapter;
    KhoanThuDAO khoanThuDAO;
    ArrayList<KhoanThu> arrayListKhoanThu;
    FloatingActionButton fabKhoanThu;
    ArrayList<LoaiThu> list = new ArrayList<LoaiThu>();
    LoaiThuDAO loaiThuDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);

        recyclerViewKhoanThu = view.findViewById(R.id.recyclerView_khoanthu);
        fabKhoanThu = view.findViewById(R.id.fab_addkhoanthu);
        //data
        khoanThuDAO = new KhoanThuDAO(getContext());
        loaiThuDAO = new LoaiThuDAO(getContext());
        //
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewKhoanThu.setLayoutManager(layoutManager);
        arrayListKhoanThu = new ArrayList<>();
        arrayListKhoanThu = khoanThuDAO.getAll();
        adapter = new KhoanThuAdapter(getContext(),arrayListKhoanThu);
        recyclerViewKhoanThu.setAdapter(adapter);

        fabKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddKhoanThu();
            }
        });

        return view;
    }
   //hàm thêm khoản thu
    public void AddKhoanThu(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_khoan_thu,null);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        EditText edThemTenKhoanThu = view.findViewById(R.id.ed_them_tenkhoanthu);
        EditText edThemNoiDungKhoanThu = view.findViewById(R.id.ed_them_noidungkhoanthu);
        EditText edThemNgayThuKhoanThu = view.findViewById(R.id.ed_them_ngaythukhoanthu);
        EditText edThemSoTienThuKhoanThu = view.findViewById(R.id.ed_them_sotienkhoanthu);
        Spinner spthemkhoanthu = view.findViewById(R.id.sp_them_loaithu);
        //đổ dữu liệu vào cho spinner
        list = loaiThuDAO.getAll();
        ArrayAdapter adapter_sp = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,list);
        spthemkhoanthu.setAdapter(adapter_sp);
        //chon ngay
        edThemNgayThuKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edThemNgayThuKhoanThu.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Button btnThem = view.findViewById(R.id.btn_them_khoanthu);
        Button btnHuy = view.findViewById(R.id.btn_huy_khoanthu);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edThemTenKhoanThu.getText().toString().isEmpty() ||
                        edThemNoiDungKhoanThu.getText().toString().isEmpty() ||
                        edThemNgayThuKhoanThu.getText().toString().isEmpty() ||
                        edThemSoTienThuKhoanThu.getText().toString().isEmpty()){
                    Toast.makeText(getContext()," Các Trường Không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setTenKhoanThu(edThemTenKhoanThu.getText().toString());
                khoanThu.setNoiDung(edThemNoiDungKhoanThu.getText().toString());
                try {
                    khoanThu.setNgayThu(simpleDateFormat.parse(edThemNgayThuKhoanThu.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                khoanThu.setSoTien(Float.parseFloat(edThemSoTienThuKhoanThu.getText().toString()));
                LoaiThu loaiThu = (LoaiThu) spthemkhoanthu.getSelectedItem();
                khoanThu.setIdTenLoaiThu(loaiThu.getIdTenLoaiThu());

                long result =  khoanThuDAO.insert(khoanThu);
                if(result>0){
                    //buoc cap nhap lai du lieu
                    arrayListKhoanThu.clear();
                    arrayListKhoanThu.addAll(khoanThuDAO.getAll());
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}