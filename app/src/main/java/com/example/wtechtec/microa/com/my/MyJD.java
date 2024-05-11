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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DGspData;
import com.example.wtechtec.microa.com.data.DJkdData;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.DNbsData;
import com.example.wtechtec.microa.com.data.JDData;
import com.example.wtechtec.microa.com.data.JZgcData;
import com.example.wtechtec.microa.com.dd.SeeDGspxq;
import com.example.wtechtec.microa.com.ifo.DGspIfo;
import com.example.wtechtec.microa.com.ifo.DJkdIfo;
import com.example.wtechtec.microa.com.ifo.DLkdIfo;
import com.example.wtechtec.microa.com.ifo.DNbsIfo;
import com.example.wtechtec.microa.com.ifo.JDIfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jayce on 2018/1/8.
 */

public class MyJD extends AppCompatActivity{
    private ListView listJD;
    private List<JDIfo> jdifos;
    private TextView type;
    private TextView zt;
    private TextView jdtime;
    private ImageView imageView, imageView1;

    private  MyDabasep mybasp;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String  result= (String) msg.obj;
jdifos.clear();
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

                    jdifos.add(new JDIfo(id,d_id,stu_number,pho_number,type,zt,xxxm,jdtime));
                }
                mybasp.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private  Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  0001:
                    String  result= (String) msg.obj;

                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        JSONObject jsonObject1=jsonObject.getJSONObject("Result");
                        JSONArray jsonArray=jsonObject1.getJSONArray("lists");
                        for(int i=0;i<jsonArray.length();i++){
                            String id=jsonArray.getJSONObject(i).getString("id");
                            String userid=jsonArray.getJSONObject(i).getString("userid");

                            String qhdz=jsonArray.getJSONObject(i).getString("spname");
                            String qhh=jsonArray.getJSONObject(i).getString("spnumber");
                            String shdz=jsonArray.getJSONObject(i).getString("shaddress");
                            String shhm=jsonArray.getJSONObject(i).getString("shphone");
                            String shsj=jsonArray.getJSONObject(i).getString("shsj");


                            String bzly=jsonArray.getJSONObject(i).getString("bzly");
                            String xf=jsonArray.getJSONObject(i).getString("xf");
                            String zt=jsonArray.getJSONObject(i).getString("zt");
                            String xx=jsonArray.getJSONObject(i).getString("xx");
                            String xdsj=jsonArray.getJSONObject(i).getString("xdsj");
                     DGspIfo gspIfo=       new DGspIfo(id, userid, qhdz, qhh, shdz, shhm, shsj, bzly, xf, zt, xdsj, xx);
                            Intent intent=new Intent(MyJD.this,MyDGspxq.class);
                            intent.putExtra("DGspIfo",gspIfo);
                            startActivity(intent);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0002:
                    String  result1= (String) msg.obj;
                    DJkdIfo dJkdIfo=null;
                    try {
                        JSONObject jsonObject=new JSONObject(result1);
                        JSONObject jsonObject1=jsonObject.getJSONObject("Result");
                        JSONArray jsonArray=jsonObject1.getJSONArray("lists");
                        for(int i=0;i<jsonArray.length();i++){
                            String id=jsonArray.getJSONObject(i).getString("id");
                            String userid=jsonArray.getJSONObject(i).getString("userid");

                            String jjrname=jsonArray.getJSONObject(i).getString("jjrname");
                            String jjrphone=jsonArray.getJSONObject(i).getString("jjrphone");
                            String jjraddress=jsonArray.getJSONObject(i).getString("jjraddress");
                            String sjrname=jsonArray.getJSONObject(i).getString("sjrname");
                            String sjrphone=jsonArray.getJSONObject(i).getString("sjrphone");
                            String sjraddress=jsonArray.getJSONObject(i).getString("sjraddress");
                            String qhaddress=jsonArray.getJSONObject(i).getString("qhaddress");
                            String bzly=jsonArray.getJSONObject(i).getString("bzly");
                            String xf=jsonArray.getJSONObject(i).getString("xf");

                            String xx=jsonArray.getJSONObject(i).getString("zt");
                            String xdsj=jsonArray.getJSONObject(i).getString("xdsj");
               dJkdIfo=       new DJkdIfo(id,userid,jjrname,jjrphone,jjraddress,sjrname,sjrphone,sjraddress,qhaddress,bzly,xf,xx,xdsj);


                        }


                        Intent intent=new Intent(MyJD.this,MyDJkdxq.class);
                        intent.putExtra("DJkdIfo",dJkdIfo);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;
                case  0003:
                    String  result2= (String) msg.obj;

                    try {
                        JSONObject jsonObject=new JSONObject(result2);
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
                     DLkdIfo dLkdIfo=   new DLkdIfo(id,userid,qhdz,qhh,shdz,shhm,shsj,bzly,xf,zt,xdsj);
                            Intent intent1=new Intent(MyJD.this,MyDLkdxq.class);
                            intent1.putExtra("DLKD",dLkdIfo);
                            startActivity(intent1);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                case 0004:

                    String  result4= (String) msg.obj;

                    try {
                        JSONObject jsonObject=new JSONObject(result4);
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
                       DNbsIfo dNbsIfo=   new DNbsIfo(id, userid, dnsw, bsaddress, bsphon, bssj, bsly, xf, zt, xdsj, XX);
                            Intent intent1=new Intent(MyJD.this,MyDNbsxq.class);
                            intent1.putExtra("DNBS",dNbsIfo);
                            startActivity(intent1);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myjd_layout);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.hide();
        InitView();
        DoMouse();
    }

    private void DoMouse(){
        imageView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        imageView1.setImageResource(R.drawable.backt);
                        break;
                    case MotionEvent.ACTION_UP:
                        imageView1.setImageResource(R.drawable.back);
                        finish();
                        break;

                }

                return true;
            }
        });
    }

    private void InitView(){
        imageView1= (ImageView) findViewById(R.id.dlkdimgback);
        Intent intent = getIntent();
        final String userid=intent.getStringExtra("userid");

        listJD= (ListView) findViewById(R.id.listJD);
        jdifos=new ArrayList<>();

        mybasp=new MyDabasep();
        listJD.setAdapter(mybasp);

        new Thread(){
            @Override
            public void run() {
                String result=   new JDData().GetIdMmess(userid);
                Message message=new Message();
                message.obj=result;
                handler.sendMessage(message);
            }
        }.start();
    }

    class MyDabasep extends BaseAdapter{

        @Override
        public int getCount() {
            return jdifos.size();
        }

        @Override
        public Object getItem(int position) {
            return jdifos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView=View.inflate(MyJD.this,R.layout.myjditme_layout,null);



        convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i("asd","123");
                    if(jdifos.get(position).getType().equals("代领")){
                        imageView.setImageResource(R.drawable.dlimg);
                        new Thread(){
                            @Override
                            public void run() {
                                String  reluet=   new DLkdData().GetDIdMmess(jdifos.get(position).getD_id());
                                Message message=new Message();
                                message.what=0003;
                                message.obj=reluet;
                                handler1.sendMessage(message);

                            }
                        }.start();

                    }
                    else  if(jdifos.get(position).getType().equals("代寄")){
                        imageView.setImageResource(R.drawable.djimg);
                        new Thread(){
                            @Override
                            public void run() {
                                String  reluet=   new DJkdData().GetDIdMmess(jdifos.get(position).getD_id());
                                Message message=new Message();
                                message.what=0002;
                                message.obj=reluet;
                                handler1.sendMessage(message);

                            }
                        }.start();
                    }
                    else    if(jdifos.get(position).getType().equals("代购")){
                        imageView.setImageResource(R.drawable.dgimg);
                        new Thread(){
                            @Override
                            public void run() {
                                String  reluet=   new DGspData().GetDIdMmess(jdifos.get(position).getD_id());
                                Message message=new Message();
                                message.what=0001;
                                message.obj=reluet;
                                handler1.sendMessage(message);

                            }
                        }.start();
                    }
                    else   if(jdifos.get(position).getType().equals("帮手")){
                        imageView.setImageResource(R.drawable.dnimg);
                        new Thread(){
                            @Override
                            public void run() {
                                String  reluet=   new DNbsData().GetDIdMmess(jdifos.get(position).getD_id());
                                Message message=new Message();
                                message.what=0004;
                                message.obj=reluet;
                                handler1.sendMessage(message);

                            }
                        }.start();
                    }


                }
            });

            type= (TextView) convertView.findViewById(R.id.type);

            imageView= (ImageView) convertView.findViewById(R.id.imageView);

            zt= (TextView) convertView.findViewById(R.id.zt);

            jdtime = (TextView) convertView.findViewById(R.id.jdtime);

            jdtime.setText(jdifos.get(position).getJdtime());
       type.setText(jdifos.get(position).getType());
            if(jdifos.get(position).getType().equals("代领")){
                imageView.setImageResource(R.drawable.dlimg);
            }
            if(jdifos.get(position).getType().equals("代寄")){
                imageView.setImageResource(R.drawable.djimg);
            }
            if(jdifos.get(position).getType().equals("代购")){
                imageView.setImageResource(R.drawable.dgimg);
            }
            if(jdifos.get(position).getType().equals("帮手")){
                imageView.setImageResource(R.drawable.dnimg);
            }
            if(jdifos.get(position).getZt().equals("否")){
                zt.setText("派送中");
            }else {
                zt.setText("完成订单");
            }
            return convertView;
        }
    }
}
