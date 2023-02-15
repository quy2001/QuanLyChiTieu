package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.LauncherApps;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlychitieu.DAO.UserDAO;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class DangNhap extends AppCompatActivity {
    Button btnDangNhap,btnDangKy;
    EditText email,password;
    UserDAO userDAO;
    ImageView btnFb;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnDangNhap = findViewById(R.id.dangnhapbtn);
        
        userDAO = new UserDAO(DangNhap.this);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        startActivity(new Intent(DangNhap.this, MainAppActivity.class));//đến trang mới
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        //đăng nhập bằng facebook
        btnFb = findViewById(R.id.fb_btn);
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(DangNhap.this, Arrays.asList("public_profile"));
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                if(mail.equals("") || pass.equals(""))
                {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đủ thông tin trước khi đăng nhập!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean userCheckResult = userDAO.checkuser(mail,pass);
                    if(userCheckResult == true)
                    {
                        startActivity(new Intent(DangNhap.this,MainAppActivity.class));//đến trang mới
                        finish();
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DangNhap.this, "Thông tin không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        
        //Đăng ký tài khoản
        btnDangKy = findViewById(R.id.dangkytkbtn);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DangKy.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}