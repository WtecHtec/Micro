package com.example.wtechtec.microa.com.dd;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DJkdData;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.JDData;
import com.example.wtechtec.microa.com.ifo.DLkdIfo;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.my.MyJD;
import com.example.wtechtec.microa.com.second.Meun;

import java.util.Calendar;

/**
 * Created by WtecHtec on 2018/1/7.
 */

public class SeeDLkdxq extends AppCompatActivity {
    private LinearLayout linearLayout;
    private Button button;
    private  String userid;
    private Calendar cal;
    private int year,month,day;
    private  String  xdsj ;
    private ImageView imageView ,imageViewchat,imageViewcall;
    private DLkdIfo dlkdIfo;
    private UserIfo userIfo;
    private TextView textViewqhdz,textViewqhh,textViewshdz,textViewshhm,textViewshsj,textViewbzly,textViewzt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.dlxq_layout);
        InitView();
    }

    private void InitView() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒

        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Intent intent=getIntent();
        dlkdIfo=intent.getParcelableExtra("DLKD");
        userIfo=intent.getParcelableExtra("userid");
        textViewqhdz= (TextView) findViewById(R.id.dlxqtextqhdz);
        textViewqhh= (TextView) findViewById(R.id.dlxqtextqhh);
        textViewshdz= (TextView) findViewById(R.id.dlxqtextshdz);
        textViewshhm= (TextView) findViewById(R.id.dlxqtextshhm);
        textViewshsj= (TextView) findViewById(R.id.dlxqtextshsj);
        textViewbzly= (TextView) findViewById(R.id.dlxqtextbzly);
        textViewzt= (TextView) findViewById(R.id.dlxqtextzt);
         imageView= (ImageView) findViewById(R.id.dlxqimgback);
        linearLayout= (LinearLayout) findViewById(R.id.dlxqlinejdxx);
        button= (Button) findViewById(R.id.dlxqbtok);
        imageViewchat= (ImageView) findViewById(R.id.dlxqimgchat);
        imageViewchat.setVisibility(View.VISIBLE);
        imageViewcall= (ImageView) findViewById(R.id.imgcall);
        imageViewcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(SeeDLkdxq.this);
                normalDialog.setIcon(R.drawable.call);
                normalDialog.setTitle("拨打电话提示");
                normalDialog.setMessage("是否确定拨打联系电话？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+textViewshhm.getText().toString()+""));
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
            }
        });
        if(dlkdIfo.getUserid().equals(userIfo.getUserid())) {

            button.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(SeeDLkdxq.this);
                normalDialog.setIcon(R.drawable.dialog);
                normalDialog.setTitle("接单提示");
                normalDialog.setMessage("接单后不可退单，是否确定接单？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread() {
                                    @Override
                                    public void run() {
                                        int hour=cal.get(Calendar.HOUR_OF_DAY);
                                        int mm=cal.get(Calendar.MINUTE);
                                        int m=cal.get(Calendar.SECOND);
                                        xdsj=year+"-"+(month+1)+"-"+day+"-"+hour+"-"+mm+"-"+m;
                                        new JDData().AddData(dlkdIfo.getId(), userIfo.getUserid(), userIfo.getHm(), "代领", "否", userIfo.getNick(),xdsj);
                                        new DLkdData().GetJDMmess(dlkdIfo.getId());
                                        Intent intent1=new Intent(SeeDLkdxq.this, MyJD.class);
                                        intent1.putExtra("userid",userIfo.getUserid());
                                        startActivity(intent1);
                                    }
                                }.start();
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

            }
        });

        imageViewchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final YWIMKit mIMKit = YWAPI.getIMKitInstance(userIfo.getUserid(), "24700296");

                final String target =dlkdIfo.getUserid(); //消息接收者ID
                final String appkey = "24700296"; //消息接收者appKey
                Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
                startActivity(intent);
            }
        });


        textViewqhdz.setText(dlkdIfo.getQhdz());
        textViewqhh.setText(dlkdIfo.getQhh());
        textViewshdz.setText(dlkdIfo.getShdz());
        textViewshhm.setText(dlkdIfo.getShhm());
        textViewshsj.setText(dlkdIfo.getShsj());
        textViewbzly.setText(dlkdIfo.getBzly());
        if(dlkdIfo.getZt().equals("否")){
            textViewzt.setText("未接单");
            linearLayout.setVisibility(View.GONE);
            button.setText("立即接单");
        }else if(dlkdIfo.getZt().equals("是")) {
            textViewzt.setText("派送中");
        }

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
}
