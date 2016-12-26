package com.example.daire.zuoye;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by daire on 2016/12/25.
 */

public class cc extends Activity {
    private LinearLayout linearLayout;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cc);
        linearLayout = (LinearLayout) findViewById(R.id.LinearLayout01);
        setLayout(linearLayout);
    }
    private void setLayout(LinearLayout linearLayout){
        AlphaAnimation aa= new AlphaAnimation(0.0f, 1.0f);
        aa.setDuration(2000);
        linearLayout.setAnimation(aa);
        linearLayout.startAnimation(aa);
        //通过handler，延迟两秒
        new Handler().postDelayed(new LoaMainTabTask(), 2000);
    }
    private class LoaMainTabTask implements Runnable{

        @Override
        public void run() {
            Intent intent = new Intent(cc.this, Login.class);
            startActivity(intent);
            finish();
        }

    }

}
