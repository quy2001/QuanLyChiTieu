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

import com.example.quanlychitieu.DAO.LoaiThuDAO;
import com.example.quanlychitieu.Model.LoaiThu;
import com.example.quanlychitieu.R;

import java.util.ArrayList;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.LoaiThuViewHoldel> {

    private Context context;
    private ArrayList<LoaiThu> arrayList;
    Toast toast;
    LoaiThuDAO loaiThuDAO;

    public LoaiThuAdapter(Context context,ArrayList<LoaiThu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        toast = new Toast(context);
        loaiThuDAO = new LoaiThuDAO(context);
    }


    @NonNull
    @Override
    public LoaiThuViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loai_thu_item, parent, false);
        return new LoaiThuViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHoldel holder, int position) {
        LoaiThu loaiThu = arrayList.get(position);
        holder.tvNoiDungTenLoaiThu.setText(loaiThu.getTenLoaiThu());

        //sửa dữ liệu
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.edit_loai_thu,null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                //ẩn màn hình hiện tại
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                EditText edSuaLoaiThu = view.findViewById(R.id.ed_sua_tenloaithu);
                edSuaLoaiThu.setText(loaiThu.getTenLoaiThu());
                Button btnSua = view.findViewById(R.id.btn_sua_loaithu);
                Button btnHuy = view.findViewById(R.id.btn_huy_loaithu);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaiThu.setTenLoaiThu(edSuaLoaiThu.getText().toString());
                        long result = loaiThuDAO.update(loaiThu);//dùng hàm update bên loaiThuDAO
                        if (result > 0)
                        {
                            //đặt lại dữ liệu
                            arrayList.clear();//hàm xóa
                            arrayList.addAll(loaiThuDAO.getAll());
                            notifyDataSetChanged();
                            //hiển thị thông báo sửa thành công
                            Toast.makeText(v.getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(v.getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
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

        //xóa dữ liệu
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.delete,null);
                builder.setView(view);
                android.app.AlertDialog alertDialog= builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                Button btn_delete = view.findViewById(R.id.btn_ok_delete);
                Button btn_no_delete = view.findViewById(R.id.btn_no_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long result=loaiThuDAO.delete(loaiThu.getIdTenLoaiThu());
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayList.clear();
                            arrayList.addAll(loaiThuDAO.getAll());
                            notifyDataSetChanged();

                            Toast.makeText(v.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }else {
                            Toast.makeText(v.getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });
                btn_no_delete.setOnClickListener(new View.OnClickListener() {
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

    public class LoaiThuViewHoldel extends RecyclerView.ViewHolder {
        ImageView ivEdit;
        ImageView ivDel;
        TextView tvNoiDungTenLoaiThu;

        public LoaiThuViewHoldel(@NonNull View itemView) {
            super(itemView);
            ivDel = itemView.findViewById(R.id.iv_delete);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            tvNoiDungTenLoaiThu = itemView.findViewById(R.id.tv_noidung_tenloaithu);
        }
    }
}
