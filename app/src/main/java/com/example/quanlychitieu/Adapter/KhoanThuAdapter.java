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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.DAO.KhoanThuDAO;
import com.example.quanlychitieu.DAO.LoaiThuDAO;
import com.example.quanlychitieu.Model.KhoanThu;
import com.example.quanlychitieu.Model.LoaiThu;
import com.example.quanlychitieu.R;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.KhoanThuViewHoldel> {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Context context;
    private ArrayList<KhoanThu> arrayListKhoanThu;
    private ArrayList<KhoanThu> arrayListKhoanThu1;
    LoaiThuDAO loaiThuDAO;
    KhoanThuDAO khoanThuDAO;
    DatePickerDialog datePickerDialog;
    LoaiThu loaiThu;
    ArrayList<LoaiThu> list = new ArrayList<>();

    public KhoanThuAdapter(Context context, ArrayList<KhoanThu> arrayListKhoanThu) {
        this.context = context;
        this.arrayListKhoanThu = arrayListKhoanThu;
        this.arrayListKhoanThu1 = arrayListKhoanThu;
        loaiThuDAO = new LoaiThuDAO(context);
        khoanThuDAO = new KhoanThuDAO(context);
    }

    public void setArrayListKhoanThu(ArrayList<KhoanThu> arrayListKhoanThu) {
        this.arrayListKhoanThu = arrayListKhoanThu;
    }

    @NonNull
    @Override
    public KhoanThuViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khoan_thu_item, null);
        return new KhoanThuViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuAdapter.KhoanThuViewHoldel holder, int position) {
        KhoanThu khoanThu = khoanThuDAO.getAll().get(position);
        holder.tvTenKhoanThu.setText(khoanThu.getTenKhoanThu());
        holder.tvSoTienKhoanThu.setText("Số Tiền: " + khoanThu.getSoTien() + " vnđ");
        holder.tvNgayThuKhoanThu.setText("Ngày Thu: " + simpleDateFormat.format(khoanThu.getNgayThu()));
        holder.tvNoiDungKhoanThu.setText("Nội Dung: " + khoanThu.getNoiDung());
        holder.tvLoaiThuKhoanThu.setText("Loại Thu: " + loaiThuDAO.getTenLoaiThu(khoanThu.getIdTenLoaiThu()));

    }

    @Override
    public int getItemCount() {
        return arrayListKhoanThu.size();
    }

    public class KhoanThuViewHoldel extends RecyclerView.ViewHolder {
        TextView tvTenKhoanThu;
        TextView tvSoTienKhoanThu;
        TextView tvNgayThuKhoanThu;
        TextView tvNoiDungKhoanThu;
        TextView tvLoaiThuKhoanThu;
        ImageView ivEdit;
        ImageView ivDel;

        public KhoanThuViewHoldel(View itemView) {
            super(itemView);
            tvTenKhoanThu = itemView.findViewById(R.id.tv_tenkhoanthu);
            tvSoTienKhoanThu = itemView.findViewById(R.id.tv_sotien_khoanthu);
            tvNgayThuKhoanThu = itemView.findViewById(R.id.tv_ngaythu_khoanthu);
            tvNoiDungKhoanThu = itemView.findViewById(R.id.tv_noidung_khoanthu);
            tvLoaiThuKhoanThu = itemView.findViewById(R.id.tv_loaithu_khoanthu);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDel = itemView.findViewById(R.id.iv_delete);
        }
    }



    ///
    @Override
    public void onBindViewHolder(@NonNull KhoanThuViewHoldel holder, int position, @NonNull List<Object> payloads) {
        KhoanThu khoanThu = khoanThuDAO.getAll().get(position);
        holder.tvTenKhoanThu.setText(khoanThu.getTenKhoanThu());
        holder.tvSoTienKhoanThu.setText("Số Tiền: "+khoanThu.getSoTien()+" vnđ");
        holder.tvNgayThuKhoanThu.setText("Ngày Thu: "+simpleDateFormat.format(khoanThu.getNgayThu()));
        holder.tvNoiDungKhoanThu.setText("Nội Dung: "+khoanThu.getNoiDung());
        holder.tvLoaiThuKhoanThu.setText("Loại Thu: "+loaiThuDAO.getTenLoaiThu(khoanThu.getIdTenLoaiThu()));
        //Sửa dữ liệu
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.edit_khoan_thu,null);
                builder.setView(view);
                AlertDialog alertDialog= builder.create();
                //ẩn background của view-item
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                EditText edSuaTenKhoanThu = view.findViewById(R.id.ed_sua_tenkhoanthu);
                EditText edSuaNgayThuKhoanThu = view.findViewById(R.id.ed_sua_ngaythukhoanthu);
                EditText edSuaNoiDungKhoanThu = view.findViewById(R.id.ed_sua_noidungkhoanthu);
                EditText edSuaSoTienKhoanThu = view.findViewById(R.id.ed_sua_sotienkhoanthu);
                Spinner spinner = view.findViewById(R.id.sp_sua_loaithu);
                //đổ dữu liệu vào cho spinner
                list = loaiThuDAO.getAll();
                ArrayAdapter adapter_sp = new ArrayAdapter(context, android.R.layout.simple_list_item_1,list);
                spinner.setAdapter(adapter_sp);
                //chon ngay
                edSuaNgayThuKhoanThu.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edSuaNgayThuKhoanThu.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.MONTH));
                        datePickerDialog.show();
                    }
                });
                //set lại dữ liệu khi sửa
                edSuaTenKhoanThu.setText(khoanThu.getTenKhoanThu());
                edSuaNgayThuKhoanThu.setText(simpleDateFormat.format(khoanThu.getNgayThu()));
                edSuaNoiDungKhoanThu.setText(khoanThu.getNoiDung());
                edSuaSoTienKhoanThu.setText(khoanThu.getSoTien()+"");
                int vitri = -1;
                for (int i=0;i<list.size();i++){
                    if (list.get(i).getTenLoaiThu().equalsIgnoreCase(loaiThuDAO.getTenLoaiThu(khoanThu.getIdTenLoaiThu()))){
                        vitri=i;
                        break;
                    }
                }
                spinner.setSelection(vitri);

                Button btnSua = view.findViewById(R.id.btn_sua_khoanthu);
                Button btnHuy = view.findViewById(R.id.btn_huy_khoanthu);
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        khoanThu.setTenKhoanThu(edSuaTenKhoanThu.getText().toString());
                        khoanThu.setNoiDung(edSuaNoiDungKhoanThu.getText().toString());
                        khoanThu.setSoTien(Float.parseFloat(edSuaSoTienKhoanThu.getText().toString()));
                        try {
                            khoanThu.setNgayThu(simpleDateFormat.parse(edSuaNgayThuKhoanThu.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //set spiner
                        loaiThu = (LoaiThu) spinner.getSelectedItem();
                        khoanThu.setIdTenLoaiThu(loaiThu.getIdTenLoaiThu());
                        long result =  khoanThuDAO.update(khoanThu);
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayListKhoanThu.clear();
                            arrayListKhoanThu.addAll(khoanThuDAO.getAll());
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
                alertDialog.show();
                Button btn_delete = view.findViewById(R.id.btn_ok_delete);
                Button btn_huy_delete = view.findViewById(R.id.btn_no_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long result=khoanThuDAO.delete(khoanThu.getIdKhoanThu());
                        if(result>0){
                            //buoc cap nhap lai du lieu
                            arrayListKhoanThu.clear();
                            arrayListKhoanThu.addAll(khoanThuDAO.getAll());
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
