package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlychitieu.DAO.UserDAO;
import com.example.quanlychitieu.Database.DBHelper;

public class DangKy extends AppCompatActivity {

    EditText email,password,repassword;
    Button btnDangKy;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        btnDangKy = findViewById(R.id.dangkybtn);

        userDAO = new UserDAO(DangKy.this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(mail.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(DangKy.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean emaiCheckResult = userDAO.checkemail(mail);
                        if (emaiCheckResult == false)
                        {
                            Boolean regResult = userDAO.insertData(mail,pass);
                            if(regResult == true)
                            {
                                Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DangKy.this,DangNhap.class));//đến trang mới
                                finish();
                            }
                            else
                            {
                                Toast.makeText(DangKy.this, "Đăng ký không thành công !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(DangKy.this, "Tài khoản đã tồn tại! vui lòng quay lại đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(DangKy.this, "Re-Password không đúng với password ! Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}