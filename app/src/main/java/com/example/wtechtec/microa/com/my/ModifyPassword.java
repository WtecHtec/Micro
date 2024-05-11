package com.example.wtechtec.microa.com.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.JDData;
import com.example.wtechtec.microa.com.first.MainActivity;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.openim.ImModify;
import com.example.wtechtec.microa.com.sql.LoginSql;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by WtecHtec on 2018/1/4.
 */

public class ModifyPassword extends AppCompatActivity {
    private UserIfo userIfo;
    private Button button;
    private EditText editText;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifypassword_layout);
        InItView();
        DoMouse();
    }

    private void DoMouse() {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        button.setBackgroundResource(R.drawable.forgetokt);
                        break;

                    case  MotionEvent.ACTION_UP:

                        button.setBackgroundResource(R.drawable.forgetoko);
                        final String  password=editText.getText().toString();

                        if(password.equals("")||password==""){

                            Toast.makeText(ModifyPassword.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            break;
                        }




                        //
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(ModifyPassword.this);
                        normalDialog.setIcon(R.drawable.dialog);
                        normalDialog.setTitle("重置提示");
                        normalDialog.setMessage("是否确定重置密码？");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                        try {
                                            userIfo.setPassword(password);
                                            new ImModify().Modify(userIfo);

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        new LoginSql(ModifyPassword.this).delete(userIfo.getUserid());
                                        YWIMKit mIMKit = YWAPI.getIMKitInstance(userIfo.getUserid(), "24700296");
                                        IYWLoginService loginService =      mIMKit.getLoginService();
                                        loginService.logout(new IWxCallback() {
                                            @Override
                                            public void onSuccess(Object... objects) {
                                            }

                                            @Override
                                            public void onError(int i, String s) {

                                            }

                                            @Override
                                            public void onProgress(int i) {

                                            }
                                        });
                                        finish();
                                        Intent intent= new Intent(ModifyPassword.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                        normalDialog.setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        normalDialog.show();


                        break;
                }

                return false;
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        imageView.setImageResource(R.drawable.backt);
                        break;
                    case MotionEvent.ACTION_UP:
                        imageView.setImageResource(R.drawable.back);
                        finish();
                        break;

                }

                return true;
            }
        });

    }

    private void InItView() {
        ActionBar actionbar=this.getSupportActionBar();
        actionbar.hide();
        Intent intent=getIntent();

        userIfo=intent.getParcelableExtra("Modify");
         button= (Button) findViewById(R.id.mobtok);
        editText= (EditText) findViewById(R.id.moetpassword);
        imageView= (ImageView) findViewById(R.id.moimgback);

    }
}
