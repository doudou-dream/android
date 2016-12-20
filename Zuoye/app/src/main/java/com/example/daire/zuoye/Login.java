package com.example.daire.zuoye;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/20.
 */
public class Login extends Activity {
    private Button bt_regi,bt_login;
    private EditText user,pwd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sharedPreferences = getSharedPreferences("Login",MODE_APPEND);
        user = (EditText) findViewById(R.id.login_user);
        pwd = (EditText) findViewById(R.id.login_pwd);
        bt_login = (Button) findViewById(R.id.login);
        bt_regi = (Button) findViewById(R.id.regi_view);
        bt_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUser=user.getText().toString();
                String strPwd=pwd.getText().toString();
                String spUser = sharedPreferences.getString("user","1");
                String spPwd = sharedPreferences.getString("pwd","1");
                if(strUser.equals(spUser) && strPwd.equals(spPwd)){
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                    greet_intent(spUser);
                }else{
                    Toast.makeText(Login.this, "密码或用户名错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        String str = get_bundle();
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        user.setText(str);

    }
    //接受传过来的数据，如果第二个页面结束了数据就没了
    private String get_bundle(){
        Bundle bundle = getIntent().getExtras();
        String str=bundle.getString("login_name");
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
}
