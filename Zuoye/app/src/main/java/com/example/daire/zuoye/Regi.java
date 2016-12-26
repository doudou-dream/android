package com.example.daire.zuoye;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

/**
 * 注册界面
 */
public class Regi extends AppCompatActivity {

    private Button bt_regi,bt_login;
    private EditText user,pwd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor  editor;
    //退出判断
    private boolean isExit;
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
                injudge();
            }
        });
        //跳转界面
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regi_click(null);
            }
        });
    }
    //注册判断
    private void injudge(){
        int ii=0;
        String strUser = user.getText().toString();
        String strPwd = pwd.getText().toString();
        if(!checkUsername(strUser)){
            Toast.makeText(Regi.this, "用户名不能为中文，6-20位", Toast.LENGTH_SHORT).show();
        }else
            ii++;
        if(!checkPwd(strPwd)){
            Toast.makeText(Regi.this, "首字母必须大写必须要有数字和英文，6-20", Toast.LENGTH_SHORT).show();

        }else
            ii++;
        String str1 = sharedPreferences.getString("user"+strUser,null);
        if(str1 != null){
            Toast.makeText(Regi.this, "用户已存在", Toast.LENGTH_SHORT).show();
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
    /**
     * 验证用户名
     * @param username 用户名
     * @return boolean
     */
    public static boolean checkUsername(String username){
        String regex = "([a-zA-Z0-9]{6,12})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }
    /**
     * 验证密码
     * @param pwd 密码
     * @return boolean
     */
    public static boolean checkPwd(String pwd){
        String regex = "^(?=.*?[A-Z])(?=.*?[0-9])[a-zA-Z0-9]{7,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }
    //退出判断
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (!isExit){
                isExit = true;
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0,2000);
            }else{
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                System.exit(0);
            }
            return false;
        }else
            return super.onKeyDown(keyCode, event);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
