package com.example.quanlychitieu.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlychitieu.Fragment.FragmentKhoanChi;
import com.example.quanlychitieu.Fragment.FragmentLoaiChi;
import com.example.quanlychitieu.Fragment.FragmentTimKiemChi;

public class ChiAdapter extends FragmentStatePagerAdapter {

    public ChiAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new FragmentLoaiChi();
            case 1:
                return new FragmentKhoanChi();
            case 2:
                return new FragmentTimKiemChi();
            default:
                return new FragmentLoaiChi();
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
                title = "Loại Chi";
                break;
            case 1:
                title = "Khoản Chi";
                break;
            case 2:
                title = "Tìm Kiếm";
                break;
        }
        return title;
    }
}
