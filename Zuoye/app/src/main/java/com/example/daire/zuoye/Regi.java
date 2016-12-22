package com.example.daire.zuoye;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Thread.sleep;

/**
 * 注册界面
 */
public class Regi extends AppCompatActivity {

    private Button bt_regi,bt_login;
    private EditText user,pwd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor  editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //注册
        bt_regi = (Button) findViewById(R.id.regi);
        //登录按钮切换
        bt_login = (Button) findViewById(R.id.login_view);
        user = (EditText) findViewById(R.id.regi_user);
        pwd = (EditText) findViewById(R.id.regi_pwd);
        sharedPreferences = getSharedPreferences("Login",MODE_APPEND);
        editor = sharedPreferences.edit();
        //注册
        bt_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ii=0;
                String strUser = user.getText().toString();
                String strPwd = pwd.getText().toString();
                if(strUser.length()<6 || strUser.length()>20){
                    Toast.makeText(Regi.this, "用户名位数不对，6-20", Toast.LENGTH_SHORT).show();
                }else
                    ii++;
                if(strPwd.length()<6 || strPwd.length()>20){
                    Toast.makeText(Regi.this, "密码位数不对，6-20", Toast.LENGTH_SHORT).show();

                }else
                    ii++;
                String str1 = sharedPreferences.getString("user"+strUser,null);
                if(str1 != null){
                    Toast.makeText(Regi.this, "已经注册过了", Toast.LENGTH_SHORT).show();
                }else 
                    ii++;
                if(ii==3){
                    editor.putString("user"+strUser,strUser);
                    editor.putString("pwd"+strUser,strPwd);
                    editor.commit();
                    Toast.makeText(Regi.this, "注册成功", Toast.LENGTH_SHORT).show();
                    regi_click(strUser);
                }
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regi_click("");
            }
        });
    }
    //数据跳转
    private void regi_click(String name){
        Intent intent = new Intent(Regi.this,Login.class);
        Bundle userPwd = new Bundle();
        userPwd.putString("login_name",name);
        intent.putExtras(userPwd);
        startActivity(intent);
        //等数据传到第二个界面再把界面结束
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        finish();
    }
}
