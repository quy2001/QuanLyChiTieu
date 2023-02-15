package com.example.quanlychitieu.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlychitieu.Fragment.FragmentKhoanThu;
import com.example.quanlychitieu.Fragment.FragmentLoaiThu;
import com.example.quanlychitieu.Fragment.FragmentTimKiemThu;

public class ThuAdapter extends FragmentStatePagerAdapter {

    public ThuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new FragmentLoaiThu();
            case 1:
                return new FragmentKhoanThu();
            case 2:
                return new FragmentTimKiemThu();
            default:
                return new FragmentLoaiThu();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    //SET TITLE

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position) {
            case 0:
                title = "Loại Thu";
                break;
            case 1:
                title = "Khoản Thu";
                break;
            case 2:
                title = "Tìm kiếm";
                break;
        }
        return title;
    }
}
