package com.example.daire.zuoye;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登陆界面
 * Created by Administrator on 2016/12/20.
 */
public class Login extends Activity {
    private Button bt_regi,bt_login;
    private EditText user,pwd;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor  editor;
    private CheckBox checkBox;
    //读取资料文件里的数据
    private String spUser;
    private String spPwd,checkEdi;
    //读取文本框里的数值
    private String strUser;
    private String strPwd;
    //接受注册界面里的数据
    private String str=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       //变量声明
        inStr();
        //注册切换按钮
        bt_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Regi.class);
                startActivity(intent);
                finish();
            }
        });
        //登录按钮
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strUser=user.getText().toString();
                strPwd=pwd.getText().toString();
                inUsertt(strUser);
                if(strUser.equals(spUser) && strPwd.equals(spPwd)){
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                    if(checkBox.isChecked()) {
                        editor.putString("check", strUser);
                        editor.commit();
                        Toast.makeText(Login.this, sharedPreferences.getString("check",null), Toast.LENGTH_SHORT).show();
                    }else{
                        editor.putString("check", null);
                        editor.commit();
                    }
                    greet_intent(spUser);
                }else{
                    Toast.makeText(Login.this, "密码或用户名错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //判断用户是从哪个界面跳转来的
        if(get_bundle() == null){
            checke();
        }else{
            String str = get_bundle();
            user.setText(str);
        }

    }
    //变量的声明
    void inStr(){
        sharedPreferences = getSharedPreferences("Login",MODE_APPEND);
        editor = sharedPreferences.edit();
        checkBox = (CheckBox) findViewById(R.id.check);
        user = (EditText) findViewById(R.id.login_user);
        pwd = (EditText) findViewById(R.id.login_pwd);
        bt_login = (Button) findViewById(R.id.login);
        bt_regi = (Button) findViewById(R.id.regi_view);
        //存储是否记住密码的判断
        checkEdi = sharedPreferences.getString("check",null);

    }
    //接受传过来的数据，如果第二个页面结束了数据就没了
    private String get_bundle(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            str=bundle.getString("login_name");
        else
            str = null;

        return str;
    }
    //传送数据到第三个界面
    private void greet_intent(String name){
        Intent greetintent = new Intent(Login.this,greet.class);
        Bundle greetbn = new Bundle();
        greetbn.putString("user",name);
        greetintent.putExtras(greetbn);
        startActivity(greetintent);
    }
    //是否勾选记住密码判断
    private void checke(){
        inUsertt(checkEdi);
        if(checkEdi != null){
            user.setText(spUser);
            pwd.setText(spPwd);
            checkBox.setChecked(true);
        }else{
            user.setText(spUser);
            Toast.makeText(this, "没有选中", Toast.LENGTH_SHORT).show();
        }
    }
    //读取文件内容
    void inUsertt(String str){
        String usertt = str;
        //用户密码的读取
        spUser = sharedPreferences.getString("user"+usertt,"");
        spPwd = sharedPreferences.getString("pwd"+usertt,"");
    }
}
