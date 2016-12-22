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
 * Created by Administrator on 2016/12/20.
 */
public class Login extends Activity {
    private Button bt_regi,bt_login;
    private EditText user,pwd;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor  editor;
    private CheckBox checkBox;
    String spUser;
    String spPwd,checkEdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sharedPreferences = getSharedPreferences("Login",MODE_APPEND);
        editor = sharedPreferences.edit();
        checkBox = (CheckBox) findViewById(R.id.check);
        user = (EditText) findViewById(R.id.login_user);
        pwd = (EditText) findViewById(R.id.login_pwd);
        bt_login = (Button) findViewById(R.id.login);
        bt_regi = (Button) findViewById(R.id.regi_view);
        spUser = sharedPreferences.getString("user","1");
        spPwd = sharedPreferences.getString("pwd","1");
        checkEdi = sharedPreferences.getString("check",null);
        bt_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Regi.class);
                startActivity(intent);
                finish();
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUser=user.getText().toString();
                String strPwd=pwd.getText().toString();

                if(strUser.equals(spUser) && strPwd.equals(spPwd)){
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                    if(checkBox.isChecked()) {
                        editor.putString("check", "1");
                        editor.commit();
                    }else{
                        editor.putString("check", "");
                        editor.commit();
                    }

                    greet_intent(spUser);
                }else{
                    editor.putString("check",null);
                    Toast.makeText(Login.this, "密码或用户名错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        String str = get_bundle();
        user.setText(str);
        checke();
    }
    //接受传过来的数据，如果第二个页面结束了数据就没了
    private String get_bundle(){
        Bundle bundle = getIntent().getExtras();
        String str;
        if(bundle != null)
            str=bundle.getString("login_name");
        else
            str = "";

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
        if(checkEdi == "1"){
            user.setText(spUser);
            pwd.setText(spPwd);
            checkBox.setChecked(true);
        }else{
            user.setText(spUser);
//            Toast.makeText(this, "没有选中", Toast.LENGTH_SHORT).show();
        }

    }
}
