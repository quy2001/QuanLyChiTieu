package com.example.quanlychitieu.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

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

import com.example.quanlychitieu.Adapter.KhoanChiAdapter;
import com.example.quanlychitieu.Adapter.KhoanThuAdapter;
import com.example.quanlychitieu.DAO.KhoanChiDAO;
import com.example.quanlychitieu.DAO.KhoanThuDAO;
import com.example.quanlychitieu.DAO.LoaiChiDAO;
import com.example.quanlychitieu.DAO.LoaiThuDAO;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiChi;
import com.example.quanlychitieu.Model.LoaiThu;
import com.example.quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentKhoanChi extends Fragment {

    RecyclerView recyclerViewKhoanChi;
    KhoanChiAdapter adapter;
    KhoanChiDAO khoanChiDAO;
    ArrayList<KhoanChi> arrayListKhoanChi;
    FloatingActionButton fabKhoanChi;
    ArrayList<LoaiChi> list = new ArrayList<LoaiChi>();
    LoaiChiDAO loaiChiDAO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);

        recyclerViewKhoanChi = view.findViewById(R.id.recyclerView_khoanchi);
        fabKhoanChi = view.findViewById(R.id.fab_addkhoanchi);
        //data
        khoanChiDAO = new KhoanChiDAO(getContext());
        loaiChiDAO = new LoaiChiDAO(getContext());
        //
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewKhoanChi.setLayoutManager(layoutManager);
        arrayListKhoanChi = new ArrayList<>();
        arrayListKhoanChi = khoanChiDAO.getAll();
        adapter = new KhoanChiAdapter(getContext(),arrayListKhoanChi);
        recyclerViewKhoanChi.setAdapter(adapter);

        fabKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddKhoanChi();
            }
        });

        return view;
    }
    //hàm thêm khoản thu
    public void AddKhoanChi(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_khoan_chi,null);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        EditText edThemTenKhoanChi = view.findViewById(R.id.ed_them_tenkhoanchi);
        EditText edThemNoiDungKhoanChi = view.findViewById(R.id.ed_them_noidungkhoanchi);
        EditText edThemNgayChiKhoanChi = view.findViewById(R.id.ed_them_ngaychikhoanchi);
        EditText edThemSoTienChiKhoanChi = view.findViewById(R.id.ed_them_sotienkhoanchi);
        Spinner spthemkhoanChi = view.findViewById(R.id.sp_them_loaichi);
        //đổ dữu liệu vào cho spinner
        list = loaiChiDAO.getAll();
        ArrayAdapter adapter_sp = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,list);
        spthemkhoanChi.setAdapter(adapter_sp);
        //chon ngay
        edThemNgayChiKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edThemNgayChiKhoanChi.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Button btnThem = view.findViewById(R.id.btn_them_khoanchi);
        Button btnHuy = view.findViewById(R.id.btn_huy_khoanchi);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edThemTenKhoanChi.getText().toString().isEmpty() ||
                        edThemNoiDungKhoanChi.getText().toString().isEmpty() ||
                        edThemNgayChiKhoanChi.getText().toString().isEmpty() ||
                        edThemSoTienChiKhoanChi.getText().toString().isEmpty()){
                    Toast.makeText(getContext()," Các Trường Không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setTenKhoanChi(edThemTenKhoanChi.getText().toString());
                khoanChi.setNoiDung(edThemNoiDungKhoanChi.getText().toString());
                try {
                    khoanChi.setNgayChi(simpleDateFormat.parse(edThemNgayChiKhoanChi.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                khoanChi.setSoTien(Float.parseFloat(edThemSoTienChiKhoanChi.getText().toString()));
                LoaiChi loaiChi = (LoaiChi) spthemkhoanChi.getSelectedItem();
                khoanChi.setIdTenLoaiChi(loaiChi.getIdTenLoaiChi());

                long result =  khoanChiDAO.insert(khoanChi);
                if(result>0){
                    //buoc cap nhap lai du lieu
                    arrayListKhoanChi.clear();
                    arrayListKhoanChi.addAll(khoanChiDAO.getAll());
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