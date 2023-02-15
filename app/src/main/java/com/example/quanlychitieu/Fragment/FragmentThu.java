package com.example.quanlychitieu.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlychitieu.Adapter.ThuAdapter;
import com.example.quanlychitieu.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentThu extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    ThuAdapter thuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        tabLayout = view.findViewById(R.id.tablayout_thu);
        viewPager = view.findViewById(R.id.viewpager_thu);
        thuAdapter = new ThuAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        //set adapter cho viewpager
        viewPager.setAdapter(thuAdapter);
        //set viewpager vao tablayout
        tabLayout.setupWithViewPager(viewPager);
        return view;

    }
}