package com.example.wtechtec.microa.com.first;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.openim.ImAdd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by WtecHtec on 2017/12/30.
 */

public class Registed extends AppCompatActivity {

    private Button buttonre;
    private ImageView imageViewback;
    private Spinner spinner;
    private EditText editTextxh,editTexthm,editTextmm,editTexttmm,editTextname;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.hide();
 setContentView(R.layout.registed);
        InitView();
        DoMouse();
    }

    private void DoMouse() {
        buttonre.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        buttonre.setBackgroundResource(R.drawable.rebtimgt);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonre.setBackgroundResource(R.drawable.rebtimgo);
                       String xx= (String) spinner.getSelectedItem();
                     String xh=editTextxh.getText().toString();
                        String hm=editTexthm.getText().toString();
                        String mm=editTextmm.getText().toString();
                        String tmm=editTexttmm.getText().toString();
                        String name=editTextname.getText().toString();

                        if(xx.equals("")||hm.equals("")||xh.equals("")||mm.equals("")||tmm.equals("")){
                            Toast.makeText(Registed.this,"输入完整信息",Toast.LENGTH_LONG).show();
                        }else if(mm.equals(tmm)==false){
                            Toast.makeText(Registed.this,"两次密码不正确",Toast.LENGTH_LONG).show();
                        } else {
                            final UserIfo userIfo=new UserIfo(xx,xh,hm,mm);
    userIfo.setNick(name);
                            new  Thread(){
                                @Override
                                public void run() {
                                    try {

                                        String result= new ImAdd().SendMsg(userIfo);
                                        char b=result.charAt(result.length()-4);
                                        if(b!='{'){
                                            Message message=new Message();
                                            message.what=0001;
                                            handler.sendMessage(message);

                                        }else {
                                            Message message=new Message();
                                            message.what=0002;
                                            handler.sendMessage(message);

                                        }
                                        Log.d("IM",result+"123");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();



                        }

                        break;

                }

                return false;
            }
        });

        imageViewback.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        imageViewback.setImageResource(R.drawable.backt);
                        break;
                    case MotionEvent.ACTION_UP:
                        imageViewback.setImageResource(R.drawable.back);
   finish();
                        break;

                }

                return true;
            }
        });

    }

    private  void  InitView(){
        buttonre= (Button) findViewById(R.id.rebtre);
        imageViewback= (ImageView) findViewById(R.id.reimgback);
        editTextxh= (EditText) findViewById(R.id.reetxh);
        editTexthm= (EditText) findViewById(R.id.reethm);
        editTextmm= (EditText) findViewById(R.id.reetmm);
        editTexttmm= (EditText) findViewById(R.id.reettmm);
        spinner= (Spinner) findViewById(R.id.respinnerxx);
        editTextname= (EditText) findViewById(R.id.reetname);


    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
          switch (msg.what){
              case  0001:
                  finish();
                  Toast.makeText(Registed.this,"注册成功",Toast.LENGTH_LONG).show();
                  break;
              case  0002:
                  Toast.makeText(Registed.this,"注册失败",Toast.LENGTH_LONG).show();
                  break;

          }
        }
    };

}
