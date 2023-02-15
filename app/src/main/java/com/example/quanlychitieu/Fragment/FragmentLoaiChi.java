package com.example.quanlychitieu.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlychitieu.Adapter.LoaiChiAdapter;
import com.example.quanlychitieu.DAO.LoaiChiDAO;
import com.example.quanlychitieu.Model.LoaiChi;
import com.example.quanlychitieu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentLoaiChi extends Fragment {

    RecyclerView recyclerViewLoaiChi;
    LoaiChiAdapter adapter;
    LoaiChiDAO loaiChiDAO;
    ArrayList<LoaiChi> arrayListLoaiChi;
    FloatingActionButton fabLoaiChi;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_chi,container,false);
        recyclerViewLoaiChi = view.findViewById(R.id.recyclerView_loaichi);
        fabLoaiChi = view.findViewById(R.id.fab_addloaichi);
        loaiChiDAO = new LoaiChiDAO(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewLoaiChi.setLayoutManager(layoutManager);
        arrayListLoaiChi = new ArrayList<>();
        arrayListLoaiChi = loaiChiDAO.getAll();
        adapter = new LoaiChiAdapter(getContext(), arrayListLoaiChi);
        recyclerViewLoaiChi.setAdapter(adapter);
        fabLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLoaiChi();
            }
        });
        return view;
    }

    public void AddLoaiChi()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_loai_chi,null);
        builder.setView(view);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
        EditText edThemLoaiChi = view.findViewById(R.id.ed_them_tenloaichi);
        Button btnThem = view.findViewById(R.id.btn_them_loaichi);
        Button btnHuy = view.findViewById(R.id.btn_huy_loaichi);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiChi loaiChi = new LoaiChi();
                loaiChi.setTenLoaiChi(edThemLoaiChi.getText().toString());
                if (edThemLoaiChi.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Không được để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                long result =  loaiChiDAO.insert(loaiChi);
                if(result>0){
                    //buoc cap nhap lai du lieu
                    arrayListLoaiChi.clear();
                    arrayListLoaiChi.addAll(loaiChiDAO.getAll());
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();;
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