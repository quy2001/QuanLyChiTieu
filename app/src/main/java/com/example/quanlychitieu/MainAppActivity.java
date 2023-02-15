package com.example.quanlychitieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.quanlychitieu.Fragment.FragmentChi;
import com.example.quanlychitieu.Fragment.FragmentHome;
import com.example.quanlychitieu.Fragment.FragmentThongKe;
import com.example.quanlychitieu.Fragment.FragmentThu;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainAppActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  static final int FRAGMENT_HOME=0;
    private  static final int FRAGMENT_THU=1;
    private  static final int FRAGMENT_CHI=2;
    private  static final int FRAGMENT_THONGKE=3;

    private int current = FRAGMENT_HOME;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(MainAppActivity.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //bắt sự kiện khi click vào item của navgationview
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //bắt sự kiện khi click vào item của  bottom navgationview
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        openFragmentHome();
                        navigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;

                    case R.id.action_thu:
                        openFragmentThu();
                        navigationView.getMenu().findItem(R.id.khoanthu).setChecked(true);
                        break;

                    case R.id.action_chi:
                        openFragmentChi();
                        navigationView.getMenu().findItem(R.id.khoanchi).setChecked(true);
                        break;

                    case R.id.action_thongke:
                        openFragmentThongKe();
                        navigationView.getMenu().findItem(R.id.thongke).setChecked(true);
                        break;

                }
                return true;
            }
        });
        // fragment hiển thị lúc đầu tiên
        replaceFragment(new FragmentHome());
        navigationView.getMenu().findItem(R.id.home).setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
        setTitleToolbar();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home)
        {
            openFragmentHome();
            bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
        }else if(id == R.id.khoanthu)
        {
            openFragmentThu();
            bottomNavigationView.getMenu().findItem(R.id.action_thu).setChecked(true);
        }else if(id== R.id.khoanchi)
        {
            openFragmentChi();
            bottomNavigationView.getMenu().findItem(R.id.action_chi).setChecked(true);
        }
        else if(id== R.id.thongke)
        {
            openFragmentThongKe();
            bottomNavigationView.getMenu().findItem(R.id.action_thongke).setChecked(true);
        }
        else if(id == R.id.dangxuat)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainAppActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Đăng Xuất ?")
                    .setMessage("Bạn có chắc muốn Đăng Xuất ?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finishActivity(1000);
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(MainAppActivity.this, DangNhap.class ));
                    finish();

                }
            });

            builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        else if (id==R.id.thoat) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainAppActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Thoát ?")
                    .setMessage("Bạn có chắc muốn Thoát ?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                    System.exit(0);
                }
            });

            builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        setTitleToolbar();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //hàm xử lí nút back của máy
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    //các hàm bắt page
    //home
    private void openFragmentHome(){
        if(current != FRAGMENT_HOME)
        {
            replaceFragment(new FragmentHome());
            current = FRAGMENT_HOME;
        }
    }
    //thu
    private void openFragmentThu()
    {
        if(current != FRAGMENT_THU)
        {
            replaceFragment(new FragmentThu());
            current = FRAGMENT_THU;
        }
    }
    //chi
    private void openFragmentChi()
    {
        if(current != FRAGMENT_CHI)
        {
            replaceFragment(new FragmentChi());
            current = FRAGMENT_CHI;
        }
    }
    //thống kê
    private void openFragmentThongKe()
    {
        if(current != FRAGMENT_THONGKE)
        {
            replaceFragment(new FragmentThongKe());
            current = FRAGMENT_THONGKE;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
    }
    //hiển thị title của toolbar
    private void setTitleToolbar()
    {
        String title = "";
        switch (current)
        {
            case FRAGMENT_HOME:
                title = getString(R.string.menu_home);
                break;

            case FRAGMENT_THU:
                title = getString(R.string.menu_khoanthu);
                break;

            case FRAGMENT_CHI:
                title = getString(R.string.menu_khoanchi);
                break;

            case FRAGMENT_THONGKE:
                title = getString(R.string.menu_thongke);
                break;
        }
        //set cho toolbar
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);
        }
    }

}