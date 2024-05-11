package com.example.wtechtec.microa.com.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.ifo.DLkdIfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class MyDLkd extends AppCompatActivity {


     private ListView listView;
    private ImageView imageView;
    private List<DLkdIfo> dlkdifos;

    private  MyDabasep mybasp;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String  result= (String) msg.obj;

            try {
                JSONObject jsonObject=new JSONObject(result);
                JSONObject jsonObject1=jsonObject.getJSONObject("Result");
                JSONArray jsonArray=jsonObject1.getJSONArray("lists");
                for(int i=0;i<jsonArray.length();i++){
                    String id=jsonArray.getJSONObject(i).getString("id");
                    String userid=jsonArray.getJSONObject(i).getString("userid");
                    String qhdz=jsonArray.getJSONObject(i).getString("qhdz");
                    String qhh=jsonArray.getJSONObject(i).getString("qhh");
                    String shdz=jsonArray.getJSONObject(i).getString("shdz");
                    String shhm=jsonArray.getJSONObject(i).getString("shhm");
                    String shsj=jsonArray.getJSONObject(i).getString("shsj");
                    String bzly=jsonArray.getJSONObject(i).getString("bzly");
                    String xf=jsonArray.getJSONObject(i).getString("xf");
                    String xdsj =jsonArray.getJSONObject(i).getString("xdsj");
                    String zt=jsonArray.getJSONObject(i).getString("zt");
                    dlkdifos.add(new DLkdIfo(id,userid,qhdz,qhh,shdz,shhm,shsj,bzly,xf,zt,xdsj));
                }
                mybasp.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydlkd_layout);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.hide();
        InitView();
        DoMouse();
    }

    private void DoMouse() {

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

    private void InitView() {
     final Intent intent=getIntent();
        final String userid=intent.getStringExtra("userid");
        Log.i("dlkd",userid+"w");
        listView= (ListView) findViewById(R.id.mylistdlkd);
        imageView= (ImageView) findViewById(R.id.dlkdimgback);
        dlkdifos=new ArrayList<>();

        mybasp=new MyDabasep();
        listView.setAdapter(mybasp);
        new Thread(){
            @Override
            public void run() {
             String result=   new DLkdData().GetIdMmess(userid);
                Message message=new Message();
                message.obj=result;
                handler.sendMessage(message);
            }
        }.start();





    }

    class  MyDabasep extends BaseAdapter{


        @Override
        public int getCount() {
            return dlkdifos.size() ;
        }

        @Override
        public Object getItem(int position) {
            return dlkdifos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView=View.inflate(MyDLkd.this,R.layout.listitem_layout,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(MyDLkd.this,MyDLkdxq.class);
                    intent1.putExtra("DLKD",dlkdifos.get(position));
                    startActivity(intent1);

                }
            });
            TextView textViewtime= (TextView) convertView.findViewById(R.id.itemtexttime);
            TextView textViewshdi= (TextView) convertView.findViewById(R.id.itemtextshdz);
            TextView textViewxf= (TextView) convertView.findViewById(R.id.itemtextxf);
            textViewshdi.setText(dlkdifos.get(position).getShdz());
            textViewtime.setText("下单时间:"+dlkdifos.get(position).getXdsj());
            textViewxf.setText("￥"+dlkdifos.get(position).getXf());
        Button   buttondel= (Button)convertView. findViewById(R.id.itemdel);
            buttondel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dlkdifos.get(position).getZt().equals("是")){
                        Toast.makeText(MyDLkd.this,"已被接单，不可删除订单",Toast.LENGTH_LONG).show();
                    }else {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyDLkd.this);
                        normalDialog.setIcon(R.drawable.dialog);
                        normalDialog.setTitle("删除提示");
                        normalDialog.setMessage("是否确定删除");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new Thread() {
                                            @Override
                                            public void run() {

                                                new DLkdData().GetDelMmess(dlkdifos.get(position).getId());

                                                String result = new DLkdData().GetIdMmess(dlkdifos.get(position).getUserid());
                                                dlkdifos.clear();
                                                Message message = new Message();
                                                message.obj = result;
                                                handler.sendMessage(message);
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
                }
            });
            return convertView;
        }
    }


}
