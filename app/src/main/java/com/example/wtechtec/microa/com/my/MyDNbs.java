package com.example.wtechtec.microa.com.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DGspData;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.DNbsData;
import com.example.wtechtec.microa.com.ifo.DGspIfo;
import com.example.wtechtec.microa.com.ifo.DNbsIfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class MyDNbs extends AppCompatActivity {


     private ListView listView;
    private ImageView imageView;
    private List<DNbsIfo> dlkdifos;

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
                    String dnsw = jsonArray.getJSONObject(i).getString("dnsw");
                    String bsaddress = jsonArray.getJSONObject(i).getString("bsaddress");
                    String bsphon = jsonArray.getJSONObject(i).getString("bsphone");
                    String bssj = jsonArray.getJSONObject(i).getString("bssj");
                    String bsly =jsonArray.getJSONObject(i).getString("bzly");
                    String xf = jsonArray.getJSONObject(i).getString("xf");
                    String zt = jsonArray.getJSONObject(i).getString("zt");
                    String xdsj = jsonArray.getJSONObject(i).getString("xdsj");
                    String XX = jsonArray.getJSONObject(i).getString("xx");
                    dlkdifos.add(new DNbsIfo(id, userid, dnsw, bsaddress, bsphon, bssj, bsly, xf, zt, xdsj, XX));
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
        setContentView(R.layout.mydjkd_layout);
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
     Intent intent=getIntent();
        final String userid=intent.getStringExtra("userid");

        listView= (ListView) findViewById(R.id.mylistdjkd);
        imageView= (ImageView) findViewById(R.id.djkdimgback);
        dlkdifos=new ArrayList<>();

        mybasp=new MyDabasep();
        listView.setAdapter(mybasp);
        new Thread(){
            @Override
            public void run() {
             String result=   new DNbsData().GetIdMmess(userid);
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

            convertView=View.inflate(MyDNbs.this,R.layout.listitem_layout,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MyDNbs.this,MyDNbsxq.class);
                    intent.putExtra("DNBS",dlkdifos.get(position));
                    startActivity(intent);
                }
            });
            TextView textViewtitle= (TextView) convertView.findViewById(R.id.itemtexttitle);
            textViewtitle.setText("电脑帮手");
            ImageView imageView= (ImageView) convertView.findViewById(R.id.itemimg);
            imageView.setImageResource(R.drawable.dnimg);
            TextView textViewtime= (TextView) convertView.findViewById(R.id.itemtexttime);
            TextView textViewshdi= (TextView) convertView.findViewById(R.id.itemtextshdz);
            TextView textViewxf= (TextView) convertView.findViewById(R.id.itemtextxf);
            textViewshdi.setText(dlkdifos.get(position).getBsaddress());
            textViewtime.setText("下单时间:"+dlkdifos.get(position).getXdsj());
            textViewxf.setText("￥"+dlkdifos.get(position).getXf());
        Button   buttondel= (Button)convertView. findViewById(R.id.itemdel);
            buttondel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dlkdifos.get(position).getZt().equals("是")){
                        Toast.makeText(MyDNbs.this,"已被接单，不可删除订单",Toast.LENGTH_LONG).show();
                    }else {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyDNbs.this);
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

                                                String result = new DGspData().GetIdMmess(dlkdifos.get(position).getUserid());
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
