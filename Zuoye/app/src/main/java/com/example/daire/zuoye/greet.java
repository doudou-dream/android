package com.example.daire.zuoye;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by daire on 2016/12/20.
 */

public class greet extends Activity {
    private TextView title,tent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greet);
        title = (TextView) findViewById(R.id.title);
        tent = (TextView) findViewById(R.id.tent);
        show();
    }
    void show(){
        Bundle bunText = getIntent().getExtras();
        String str = bunText.getString("user");
        String text1=title.getText().toString();
        title.setText(text1+str+"用户");
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
