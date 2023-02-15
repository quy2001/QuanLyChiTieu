package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class GioiThieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);

        PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getPaperOnboardingPageData());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame,paperOnboardingFragment);
        fragmentTransaction.commit();
        paperOnboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent intent = new Intent(GioiThieu.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getPaperOnboardingPageData(){
        PaperOnboardingPage scr1 = new PaperOnboardingPage("WELCOME",
                "Chào mừng đến với ứng dụng quản lí chi tiêu !",
                Color.parseColor("#FFCC33"), R.drawable.step1, R.drawable.logohome);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("SUCCESS",
                "Kiểm soát thu chi, nhắm thẳng mục tiêu về tài chính",
                Color.parseColor("#33CCFF"), R.drawable.step2, R.drawable.icvi);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("DREAM",
                "Hưởng thụ cuộc sống thoải mái, tự do tài chính với người thân, bạn bè",
                Color.parseColor("#FFCC66"), R.drawable.step3, R.drawable.icnext);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }
}