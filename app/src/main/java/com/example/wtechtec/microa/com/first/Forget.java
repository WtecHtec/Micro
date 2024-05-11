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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.openim.ImMessage;
import com.example.wtechtec.microa.com.openim.ImModify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

/**
 * Created by WtecHtec on 2017/12/31.
 */

public class Forget extends AppCompatActivity {

    private Button buttonmobile,buttonforget,buttonforgetok;
    private LinearLayout linearLayout;
    private EditText editTextuserid,editTexthm, editTextyzm,editTextnewmm;
    private boolean b=false;
    private ImageView imageview;
    private  Random random=new Random();
    private  int yzm=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case  0001:
                    Toast.makeText(Forget.this,"手机号码不对",Toast.LENGTH_LONG).show();
                    break;
                case  0002:
                    b = true;
                    buttonforget.setBackgroundResource(R.drawable.forgeto);
                    yzm= random.nextInt(9999)+1000;
                    Toast.makeText(Forget.this,"验证码"+yzm,Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.forget);
        InitView();
        DoMouse();
    }

    private void DoMouse() {
       buttonmobile.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               switch (event.getAction()){
                   case  MotionEvent.ACTION_DOWN:
            buttonmobile.setBackgroundResource(R.drawable.mobliet);
                       final String userid=editTextuserid.getText().toString();
                       final String hm=editTexthm.getText().toString();



                       if(userid.equals("")||hm.equals("")){
                           Toast.makeText(Forget.this,"信息不能为空",Toast.LENGTH_LONG).show();
                       }else {
                           new Thread(){
                               @Override
                               public void run() {
                                   try {
                                       String  resutle=   new ImMessage().Message(userid);
                                       JSONObject jsonObject=new JSONObject(resutle);
                                          JSONObject jsonObject1=jsonObject.getJSONObject("openim_users_get_response");
                                       JSONObject jsonObject2=jsonObject1.getJSONObject("userinfos");
                                       JSONArray jsonArray=jsonObject2.getJSONArray("userinfos");
                                          JSONObject jsonObject3=jsonArray.getJSONObject(0);
                                       String mehm=jsonObject3.getString("mobile");

                                       if(hm.equals(mehm)){

                                           Message message=new Message();
                                           message.what=0002;
                                           handler.sendMessage(message);

                                       }else {
                                           Message message=new Message();
                                           message.what=0001;
                                           handler.sendMessage(message);


                                       }

                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }.start();
                       }




                       break;

                   case  MotionEvent.ACTION_UP:
                       buttonmobile.setBackgroundResource(R.drawable.mobileo);
                       break;
               }

               return false;
           }
       });

        buttonforget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        if(b==true) {
                            buttonforget.setBackgroundResource(R.drawable.forgett);
                        }
                        break;

                    case  MotionEvent.ACTION_UP:
                      String syzm=editTextyzm.getText().toString();
                        if(b==true){
                            if(syzm.equals(String.valueOf(yzm))) {

                                buttonforget.setBackgroundResource(R.drawable.forgeto);
                                buttonforget.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.VISIBLE);

                            }else {
                                Toast.makeText(Forget.this,"验证码不对",Toast.LENGTH_LONG).show();
                            }

                        }
                        break;
                }

                return false;
            }
        });

        buttonforgetok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        buttonforgetok.setBackgroundResource(R.drawable.forgetokt);
                        break;

                    case  MotionEvent.ACTION_UP:

                        buttonforgetok.setBackgroundResource(R.drawable.forgetoko);
                        final String userid=editTextuserid.getText().toString();
                        String newmm=editTextnewmm.getText().toString();
                        try {
                            new ImModify().Modify(new UserIfo(userid,newmm));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finish();
                        Toast.makeText(Forget.this,"重置密码成功",Toast.LENGTH_LONG).show();

                        break;
                }

                return false;
            }
        });

        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        imageview.setImageResource(R.drawable.backt);
                        break;
                    case MotionEvent.ACTION_UP:
                        imageview.setImageResource(R.drawable.back);
                        finish();
                        break;

                }

                return true;
            }
        });

    }

    private void InitView() {
        buttonforget= (Button) findViewById(R.id.fgbtforget);
        buttonforgetok= (Button) findViewById(R.id.fgbtfogetok);
        buttonmobile= (Button) findViewById(R.id.fgbtmobile);
        linearLayout= (LinearLayout) findViewById(R.id.fglinefg);
        imageview= (ImageView) findViewById(R.id.fgimgback);
        editTextuserid= (EditText) findViewById(R.id.fgetid);
        editTexthm= (EditText) findViewById(R.id.fgethm);
        editTextyzm= (EditText) findViewById(R.id.fgetyzm);
        editTextnewmm= (EditText) findViewById(R.id.fgetnewmm);
    }
}
