package com.example.quanlychitieu.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.DAO.KhoanChiDAO;
import com.example.quanlychitieu.DAO.LoaiChiDAO;
import com.example.quanlychitieu.Model.KhoanChi;
import com.example.quanlychitieu.Model.LoaiChi;
import com.example.quanlychitieu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.KhoanChiHolder> {
    Context context;
    ArrayList<KhoanChi> arrayListKhoanChi;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    LoaiChiDAO loaiChiDAO;
    ArrayList<LoaiChi> list = new ArrayList<>();
    DatePickerDialog datePickerDialog;
    LoaiChi loaiChi;
    KhoanChiDAO khoanChiDAO;

    public KhoanChiAdapter(Context context, ArrayList<KhoanChi> arrayListKhoanChi) {
        this.context = context;
        this.arrayListKhoanChi = arrayListKhoanChi;
        loaiChiDAO = new LoaiChiDAO(context);
        khoanChiDAO = new KhoanChiDAO(context);
    }

    @Override
    public KhoanChiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khoan_chi_item, null);
        return new KhoanChiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiHolder holder, int position) {
        KhoanChi khoanChi = arrayListKhoanChi.get(position);
        holder.tvTenKhoanChi.setText(khoanChi.getTenKhoanChi());
        holder.tvNoiDungKhoanChi.setText("Nội Dung: " + khoanChi.getNoiDung());
        holder.tvNgayChiKhoanChi.setText("Ngày Chi: " + simpleDateFormat.format(khoanChi.getNgayChi()));
        holder.tvSoTienKhoanChi.setText("Số Tiền: " + khoanChi.getSoTien() + " vnđ");
        holder.tvLoaiChiKhoanChi.setText("Loại Chi: " + loaiChiDAO.getTenLoaiChi(khoanChi.getIdTenLoaiChi()));

    }

    @Override
    public int getItemCount() {
        return arrayListKhoanChi.size();
    }

    public void setList(ArrayList<KhoanChi> list) {
    }

    public class KhoanChiHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhoanChi;
        TextView tvSoTienKhoanChi;
        TextView tvNgayChiKhoanChi;
        TextView tvNoiDungKhoanChi;
        TextView tvLoaiChiKhoanChi;
        ImageView ivEdit;
        ImageView ivDel;

        public KhoanChiHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhoanChi = itemView.findViewById(R.id.tv_tenkhoanchi);
            tvSoTienKhoanChi = itemView.findViewById(R.id.tv_sotien_khoanchi);
            tvNgayChiKhoanChi = itemView.findViewById(R.id.tv_ngaythu_khoanchi);
            tvNoiDungKhoanChi = itemView.findViewById(R.id.tv_noidung_khoanchi);
            tvLoaiChiKhoanChi = itemView.findViewById(R.id.tv_loaichi_khoanchi);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDel = itemView.findViewById(R.id.iv_delete);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiHolder holder, int position, @NonNull List<Object> payloads) {
        KhoanChi khoanChi = arrayListKhoanChi.get(position);
        holder.tvTenKhoanChi.setText(khoanChi.getTenKhoanChi());
        holder.tvNoiDungKhoanChi.setText("Nội Dung: "+khoanChi.getNoiDung());
        holder.tvNgayChiKhoanChi.setText("Ngày Chi: "+simpleDateFormat.format(khoanChi.getNgayChi()));
        holder.tvSoTienKhoanChi.setText("Số Tiền: "+khoanChi.getSoTien()+" vnđ");
        holder.tvLoaiChiKhoanChi.setText("Loại Chi: "+loaiChiDAO.getTenLoaiChi(khoanChi.getIdTenLoaiChi()));
        //sửa sữ liệu
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.edit_khoan_chi,null);
                builder.setView(view);
                AlertDialog alertDialog= builder.create();

                alertDialog.show();

                EditText edSuaTenKhoanChi = view.findViewById(R.id.ed_sua_tenkhoanchi);
                EditText edSuaNgayThuKhoanChi = view.findViewById(R.id.ed_sua_ngaychikhoanchi);
                EditText edSuaNoiDungKhoanChi = view.findViewById(R.id.ed_sua_noidungkhoanchi);
                EditText edSuaSoTienKhoanChi = view.findViewById(R.id.ed_sua_sotienkhoanchi);
                Spinner spinner = view.findViewById(R.id.sp_sua_loaichi);
                //đổ dữu liệu vào cho spinner
                list = loaiChiDAO.getAll();
                ArrayAdapter adapter_sp = new ArrayAdapter(context, android.R.layout.simple_list_item_1,list);
                spinner.setAdapter(adapter_sp);
                //chon ngay
                edSuaNgayThuKhoanChi.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edSuaNgayThuKhoanChi.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                            }
                        },calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(
                                Calendar.YEAR));
                        datePickerDialog.show();
                    }
                });
                //set lại dữ liệu khi sửa
                edSuaTenKhoanChi.setText(khoanChi.getTenKhoanChi());
                edSuaNgayThuKhoanChi.setText(simpleDateFormat.format(khoanChi.getNgayChi()));
                edSuaNoiDungKhoanChi.setText(khoanChi.getNoiDung());
                edSuaSoTienKhoanChi.setText(khoanChi.getSoTien()+"");
                int vitri = -1;
                for (int i=0;i<list.size();i++){
                    if (list.get(i).getTenLoaiChi().equalsIgnoreCase(loaiChiDAO.getTenLoaiChi(
                            khoanChi.getIdTenLoaiChi()))){
                        vitri=i;
                        break;
                    }
                }
                spinner.setSelection(vitri);

                Button btnSua = view.findViewById(R.id.btn_sua_khoanchi);
                Button btnHuy = view.findViewById(R.id.btn_huy_khoanchi);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        khoanChi.setTenKhoanChi(edSuaTenKhoanChi.getText().toString());
                        khoanChi.setNoiDung(edSuaNoiDungKhoanChi.getText().toString());
                        khoanChi.setSoTien(Float.parseFloat(edSuaSoTienKhoanChi.getText().toString()));
                        try {
                            khoanChi.setNgayChi(simpleDateFormat.parse(edSuaNgayThuKhoanChi.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //set spiner
                        loaiChi = (LoaiChi) spinner.getSelectedItem();
                        khoanChi.setIdTenLoaiChi(loaiChi.getIdTenLoaiChi());
                        long result =  khoanChiDAO.update(khoanChi);
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayListKhoanChi.clear();
                            arrayListKhoanChi.addAll(khoanChiDAO.getAll());
                            notifyDataSetChanged();

                            Toast.makeText(v.getContext(), "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }else {
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
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.delete,null);
                builder.setView(view);
                AlertDialog alertDialog= builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                Button btn_delete = view.findViewById(R.id.btn_ok_delete);
                Button btn_huy_delete = view.findViewById(R.id.btn_no_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long result=khoanChiDAO.delete(khoanChi.getIdKhoanChi());
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayListKhoanChi.clear();
                            arrayListKhoanChi.addAll(khoanChiDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(v.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }else {
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
}

