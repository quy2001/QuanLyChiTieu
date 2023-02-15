package com.example.quanlychitieu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.DAO.KhoanThuDAO;
import com.example.quanlychitieu.DAO.LoaiChiDAO;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiChi;
import com.example.quanlychitieu.R;

import java.util.ArrayList;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.LoaiChiViewHoldel> {
    private Context context;
    private ArrayList<LoaiChi> arrayList;
    private ArrayList<KhoanThu> khoanThuArrayList = new ArrayList<>();
    LoaiChiDAO loaiChiDAO;
    KhoanThuDAO khoanThuDAO;

    public LoaiChiAdapter(Context context, ArrayList<LoaiChi> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        loaiChiDAO = new LoaiChiDAO(context);
        khoanThuDAO = new KhoanThuDAO(context);
    }


    @Override
    public LoaiChiViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loai_chi_item, parent, false);
        return new LoaiChiViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHoldel holder, int position) {
        LoaiChi loaiChi = arrayList.get(position);
        holder.tvNoiDungTenLoaichi.setText(loaiChi.getTenLoaiChi());
        //sửa dữ liệu
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.edit_loai_chi, null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                //ẩn background của view-item
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                EditText edSuaLoaiChi = view.findViewById(R.id.ed_sua_tenloaichi);
                edSuaLoaiChi.setText(loaiChi.getTenLoaiChi());
                Button btnSua = view.findViewById(R.id.btn_sua_loaichi);
                Button btnHuy = view.findViewById(R.id.btn_huy_loaichi);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaiChi.setTenLoaiChi(edSuaLoaiChi.getText().toString());
                        long result = loaiChiDAO.update(loaiChi);
                        if (result > 0) {
                            //buoc cap nhap lai du lieu
                            arrayList.clear();
                            arrayList.addAll(loaiChiDAO.getAll());
                            notifyDataSetChanged();

                            Toast.makeText(v.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(v.getContext(), "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
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
        });
        //Xóa Dữ Liệu
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.delete, null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button btn_delete = view.findViewById(R.id.btn_ok_delete);
                Button btn_huy_delete = view.findViewById(R.id.btn_no_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long result = loaiChiDAO.delete(loaiChi.getIdTenLoaiChi());
                        if (result > 0) {
                            //buoc cap nhap lai du lieu
                            arrayList.clear();
                            arrayList.addAll(loaiChiDAO.getAll());
                            notifyDataSetChanged();
                            //thời gian hiển thị
                            Toast.makeText(v.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(v.getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });


                btn_huy_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class LoaiChiViewHoldel extends RecyclerView.ViewHolder {
        ImageView ivEdit;
        ImageView ivDel;
        TextView tvNoiDungTenLoaichi;

        public LoaiChiViewHoldel(@NonNull View itemView) {
            super(itemView);
            ivDel = itemView.findViewById(R.id.iv_delete);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            tvNoiDungTenLoaichi = itemView.findViewById(R.id.tv_noidung_tenloaichi);
        }
    }
}