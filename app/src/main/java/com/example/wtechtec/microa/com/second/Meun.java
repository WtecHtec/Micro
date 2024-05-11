package com.example.wtechtec.microa.com.second;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.dd.MainActivity;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.openim.ImMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by WtecHtec on 2018/1/2.
 */

public class Meun extends AppCompatActivity implements View.OnClickListener {
    //UI Object

    private TextView txt_channel;
    private TextView txt_message;
    private TextView txt_better;
    private TextView txt_setting;
    private FrameLayout ly_content;
    private  Fragment fragment;
    public UserIfo userIfo=null;
    //Fragment Object
    private MyFragment fg1;
    private MainActivity fg2;
    private MeFragment fg4;
    private Fragment fg3;
    private android.app.FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.meun);
        Intent intent=getIntent();
        userIfo=intent.getParcelableExtra("UserIfo");
        Log.i("UserIfo",userIfo.getUserid());
        new  Thread(){
            @Override
            public void run() {
                String  resutle= null;
                try {
                    resutle = new ImMessage().Message(userIfo.getUserid());
                    JSONObject jsonObject=new JSONObject(resutle);
                    JSONObject jsonObject1=jsonObject.getJSONObject("openim_users_get_response");
                    JSONObject jsonObject2=jsonObject1.getJSONObject("userinfos");
                    JSONArray jsonArray=jsonObject2.getJSONArray("userinfos");
                    JSONObject jsonObject3=jsonArray.getJSONObject(0);
                    String xx=jsonObject3.getString("address");
                    String name=jsonObject3.getString("nick");
                    String phone=jsonObject3.getString("mobile");
                    userIfo.setXx(xx);
                    userIfo.setNick(name);
                    userIfo.setHm(phone);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();



        fManager = getFragmentManager();
        bindViews();
        txt_channel.performClick();   //模拟一次点击，既进去后选择第一项
    }

    //UI组件初始化与事件绑定
    private void bindViews() {


        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        txt_setting = (TextView) findViewById(R.id.txt_setting);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        ly_content.setLayoutParams(new LinearLayout.LayoutParams(width , (int) (height*0.88f)));
        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment( android.support.v4.app.FragmentTransaction fragmentTransaction ){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }


    @Override
    public void onClick(View v) {
        final YWIMKit mIMKit = YWAPI.getIMKitInstance(userIfo.getUserid(), "24700296");
        android.support.v4.app.FragmentManager mFragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);





        switch (v.getId()){
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if(fg1 == null){
           fg1=new MyFragment();
                    fragmentTransaction.replace(R.id.ly_content,fg1);
                }else{
                    fragmentTransaction.show(fg1);
                }
                fragmentTransaction.commit();
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if(fg2 == null){
                    fg2=new MainActivity();
                    fragmentTransaction.add(R.id.ly_content,fg2);
                }else{
                    fragmentTransaction.show(fg2);
                }
                fragmentTransaction.commit();
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);


                if(fg3 == null){
     fg3=mIMKit.getConversationFragment();
                    fragmentTransaction .add(R.id.ly_content,fg3);

                }else{
                    fragmentTransaction.show(fg3);

                }
                fragmentTransaction .commit();
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);

                if(fg4 == null){
                    fg4=new MeFragment();
                    fragmentTransaction.add(R.id.ly_content,fg4);
                }else{
                    fragmentTransaction.show(fg4);
                }
                fragmentTransaction.commit();
                break;
        }


    }
}
