package com.example.wtechtec.microa.com.first;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.Test;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.openim.ImAdd;
import com.example.wtechtec.microa.com.second.Meun;
import com.example.wtechtec.microa.com.sql.LoginSql;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
public class MainActivity extends AppCompatActivity {
    private Button buttonlogin, buttonregisted;
    private TextView textViewre, textViewforget;
    private EditText editTextnumber, editTextpasword;
    private LinearLayout linearLayout;
    private LoginSql loginSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();
        InitView();
        DoMouse();
    }
    @TargetApi(Build.VERSION_CODES.N)
    private void InitView() {
        buttonlogin = (Button) findViewById(R.id.btlogin);
        buttonregisted = (Button) findViewById(R.id.btregisted);
        textViewforget = (TextView) findViewById(R.id.textforget);
        textViewre = (TextView) findViewById(R.id.textre);
        editTextnumber = (EditText) findViewById(R.id.acetnumber);
        editTextpasword = (EditText) findViewById(R.id.acetpassword);
        loginSql = new LoginSql(this);
        UserIfo userIfo = loginSql.find();
        if (userIfo != null) {
            finish();
            Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Meun.class);
            intent.putExtra("UserIfo", userIfo);
            startActivity(intent);
        }
    }
    private void DoMouse() {
        buttonregisted.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonregisted.setBackgroundResource(R.drawable.btimgret);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonregisted.setBackgroundResource(R.drawable.btimgreo);
                        Intent intent = new Intent(MainActivity.this, Registed.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        buttonlogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonlogin.setBackgroundResource(R.drawable.btimglgt);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonlogin.setBackgroundResource(R.drawable.btimglgo);
                        String number = editTextnumber.getText().toString();
                        String password = editTextpasword.getText().toString();
                        //此实现不一定要放在Application onCreate中
                        //此对象获取到后，保存为全局对象，供APP使用
                        //此对象跟用户相关，如果切换了用户，需要重新获取
                        if (number.equals("")) {
                            Toast.makeText(MainActivity.this, "请输入账号", Toast.LENGTH_LONG).show();
                        } else if (password.equals("")) {
                            Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
                        } else {
                            final YWIMKit mIMKit = YWAPI.getIMKitInstance(number, "24700296");
                            final UserIfo userIfo = new UserIfo(number, password);
                            //开始登录
                            IYWLoginService loginService = mIMKit.getLoginService();
                            YWLoginParam loginParam = YWLoginParam.createLoginParam(number, password);
                            loginService.login(loginParam, new IWxCallback() {
                                @Override
                                public void onSuccess(Object... arg0) {
                                    Log.i("YM", "s");
                                    finish();
                                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    loginSql.add(userIfo);
                                    Intent intent = new Intent(MainActivity.this, Meun.class);
                                    intent.putExtra("UserIfo", userIfo);
                                    startActivity(intent);
                                }
                                @Override
                                public void onProgress(int arg0) {
                                    // TODO Auto-generated method stub
                                }
                                @Override
                                public void onError(int errCode, String description) {
                                    //如果登录失败，errCode为错误码,description是错误的具体描述信息
                                    Log.i("YM", description);
                                    Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        break;
                }
                return false;
            }
        });
        textViewre.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        textViewre.setTextColor(Color.RED);
                        break;
                    case MotionEvent.ACTION_UP:
                        textViewre.setTextColor(Color.WHITE);
                        Intent intent = new Intent(MainActivity.this, Registed.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        textViewforget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        textViewforget.setTextColor(Color.RED);
                        break;
                    case MotionEvent.ACTION_UP:
                        textViewforget.setTextColor(Color.BLUE);
                        Intent intent = new Intent(MainActivity.this, Forget.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
