package com.example.wtechtec.microa.com.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.JDData;
import com.example.wtechtec.microa.com.ifo.DLkdIfo;
import com.example.wtechtec.microa.com.ifo.JDIfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WtecHtec on 2018/1/7.
 */

public class MyDLkdxq extends AppCompatActivity {
    private LinearLayout linearLayout;
    private Button button;
    private  String Did;
    private ImageView imageView,imageViewcall,imageViewcall1;
    private DLkdIfo dlkdIfo;
    private TextView textViewqhdz,textViewqhh,textViewshdz,textViewshhm,textViewshsj,textViewbzly,textViewzt,textViewname,textViewhm,textViewxh;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String  result= (String) msg.obj;

            try {
                JSONObject jsonObject=new JSONObject(result);
                JSONObject jsonObject1=jsonObject.getJSONObject("Result");
                JSONArray jsonArray=jsonObject1.getJSONArray("lists");
                for(int i=0;i<jsonArray.length();i++){
                    int id=jsonArray.getJSONObject(i).getInt("id");
                    String d_id=jsonArray.getJSONObject(i).getString("d_id");
                    String stu_number=jsonArray.getJSONObject(i).getString("stu_number");
                    String pho_number=jsonArray.getJSONObject(i).getString("pho_number");
                    String type=jsonArray.getJSONObject(i).getString("type");
                    String zt=jsonArray.getJSONObject(i).getString("zt");
                    String xxxm=jsonArray.getJSONObject(i).getString("xxxm");
                    String jdtime = jsonArray.getJSONObject(i).getString("jdtime");
                    Did=jsonArray.getJSONObject(i).getString("id");
                    new JDIfo(id,d_id,stu_number,pho_number,type,zt,xxxm,jdtime);
                    if(zt.equals("是")){
                        textViewzt.setText("完成订单");
                        button.setVisibility(View.GONE);
                    }
                    if(stu_number.equals(dlkdIfo.getUserid())){
                        button.setVisibility(View.GONE);
                    }
                    textViewhm.setText(pho_number);
                    textViewxh.setText(stu_number);
                    textViewname.setText(xxxm);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.dlxq_layout);
        InitView();
    }

    private void InitView() {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Intent intent=getIntent();
        dlkdIfo=intent.getParcelableExtra("DLKD");
        textViewqhdz= (TextView) findViewById(R.id.dlxqtextqhdz);
        textViewqhh= (TextView) findViewById(R.id.dlxqtextqhh);
        textViewshdz= (TextView) findViewById(R.id.dlxqtextshdz);
        textViewshhm= (TextView) findViewById(R.id.dlxqtextshhm);
        textViewshsj= (TextView) findViewById(R.id.dlxqtextshsj);
        textViewbzly= (TextView) findViewById(R.id.dlxqtextbzly);
        textViewzt= (TextView) findViewById(R.id.dlxqtextzt);

        textViewname= (TextView) findViewById(R.id.dgxqtextname);
        textViewxh= (TextView) findViewById(R.id.dgxqtextxh);
        textViewhm= (TextView) findViewById(R.id.dgxqtexthm);

        imageViewcall= (ImageView) findViewById(R.id.imgcall);
        imageViewcall.setVisibility(View.GONE);
        imageViewcall1= (ImageView) findViewById(R.id.imgcall1);
        imageViewcall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyDLkdxq.this);
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

        imageView= (ImageView) findViewById(R.id.dlxqimgback);
        linearLayout= (LinearLayout) findViewById(R.id.dlxqlinejdxx);
        button= (Button) findViewById(R.id.dlxqbtok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyDLkdxq.this);
                normalDialog.setIcon(R.drawable.dialog);
                normalDialog.setTitle("完成接单提示");
                normalDialog.setMessage("是否确定完成");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread(){
                                    @Override
                                    public void run() {
                                        new JDData().GetJDMmess(Did);
                                    }


                                }.start();
                                finish();
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
        textViewqhdz.setText(dlkdIfo.getQhdz());
        textViewqhh.setText(dlkdIfo.getQhh());
        textViewshdz.setText(dlkdIfo.getShdz());
        textViewshhm.setText(dlkdIfo.getShhm());
        textViewshsj.setText(dlkdIfo.getShsj());
        textViewbzly.setText(dlkdIfo.getBzly());
        if(dlkdIfo.getZt().equals("否")){
            textViewzt.setText("未接单");
            linearLayout.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        }else if(dlkdIfo.getZt().equals("是")) {
            textViewzt.setText("派送中");
            new Thread(){
                @Override
                public void run() {
                    String  result=     new JDData().GetDidMmess(dlkdIfo.getId());
                    Message message=new Message();
                    message.obj=result;
                    handler.sendMessage(message);
                }
            }.start();
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
