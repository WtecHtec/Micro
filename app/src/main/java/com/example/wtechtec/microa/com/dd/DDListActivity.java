package com.example.wtechtec.microa.com.dd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DGspData;
import com.example.wtechtec.microa.com.data.DJkdData;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.DNbsData;
import com.example.wtechtec.microa.com.data.JDData;
import com.example.wtechtec.microa.com.ifo.DGspIfo;
import com.example.wtechtec.microa.com.ifo.DJkdIfo;
import com.example.wtechtec.microa.com.ifo.DLkdIfo;
import com.example.wtechtec.microa.com.ifo.DNbsIfo;
import com.example.wtechtec.microa.com.ifo.UserIfo;
import com.example.wtechtec.microa.com.my.MyDJkd;
import com.example.wtechtec.microa.com.my.MyDLkd;
import com.example.wtechtec.microa.com.my.MyDLkdxq;
import com.example.wtechtec.microa.com.second.Meun;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jayce on 2018/1/6.
 */

public class DDListActivity extends  android.support.v4.app.Fragment implements View.OnClickListener {

    private  String xx;
    private Button dl2;
    private Button dj2;
    private Button dg2;
    private Button dn2;
   private ListView listView;
    private List<DLkdIfo> dLkdIfos;
    private List<DJkdIfo> DJkdIfos;
    private  MyDLkdBase myDLkdBase;
    private  List<DGspIfo> dGspIfos;
    private  List<DNbsIfo> dNbsIfos;
    private UserIfo userIfo;
    private Calendar cal;
    private int year,month,day;
    private  String  xdsj ;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  0001:
                    String  result= (String) msg.obj;
                    dLkdIfos.clear();
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
                           dLkdIfos.add(new DLkdIfo(id,userid,qhdz,qhh,shdz,shhm,shsj,bzly,xf,zt,xdsj));
                        }
                        dNbsIfos.clear();
                        dGspIfos.clear();
                        DJkdIfos.clear();
                        listView.setAdapter(myDLkdBase);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case  0002:
DJkdIfos.clear();
                    String  result1= (String) msg.obj;

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
                            DJkdIfos.add(new DJkdIfo(id,userid,jjrname,jjrphone,jjraddress,sjrname,sjrphone,sjraddress,qhaddress,bzly,xf,xx,xdsj));
                        }
                        dNbsIfos.clear();

                        dGspIfos.clear();
                        dLkdIfos.clear();
                        listView.setAdapter(new MyDJkdBase());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case  0003:
                    String  result2= (String) msg.obj;
dGspIfos.clear();
                    try {
                        JSONObject jsonObject=new JSONObject(result2);
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
                            dGspIfos.add(new DGspIfo(id, userid, qhdz, qhh, shdz, shhm, shsj, bzly, xf, zt, xdsj, xx));
                        }

                        dNbsIfos.clear();


                        DJkdIfos.clear();
                        dLkdIfos.clear();
                   listView.setAdapter(new MyDGspBase());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case  0004:
                    String  result4= (String) msg.obj;
dNbsIfos.clear();
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
                            dNbsIfos.add(new DNbsIfo(id, userid, dnsw, bsaddress, bsphon, bssj, bsly, xf, zt, xdsj, XX));
                        }

                      dLkdIfos.clear();
                        dGspIfos.clear();
                        DJkdIfos.clear();
                        listView.setAdapter(new MyDNbsBase());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ddlist_layout, container, false);
       listView= (ListView) view.findViewById(R.id.ddlistsee);
        dl2 = (Button) view.findViewById(R.id.dl2);
        dj2 = (Button) view.findViewById(R.id.dj2);
        dg2 = (Button) view.findViewById(R.id.dg2);
        dn2 = (Button) view.findViewById(R.id.dn2);



        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒

        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);


        dl2.setOnClickListener(this);
        dj2.setOnClickListener(this);
        dg2.setOnClickListener(this);
        dn2.setOnClickListener(this);
  DJkdIfos=new ArrayList<>();
        dGspIfos=new ArrayList<>();
        dNbsIfos=new ArrayList<>();
  Meun meun= (Meun) getActivity();
        xx=meun.userIfo.getXx();
        final String xx=meun.userIfo.getXx();
    userIfo=meun.userIfo;
    dLkdIfos=new ArrayList<>();
        myDLkdBase=new MyDLkdBase();

        listView.setAdapter(myDLkdBase);
      new Thread(){
          @Override
          public void run() {
         String result=  new DLkdData().GetMmess(xx);
              Message message=new Message();
              message.obj=result;
              message.what=0001;
              handler.sendMessage(message);
          }
      }.start();

        dl2.performClick();

        return view;
    }

    @Override
    public void onClick(View v) {

        Meun meun= (Meun) getActivity();
        final String xx=meun.userIfo.getXx();
        switch (v.getId())
        {
            case R.id.dl2:
                dl2.setEnabled(false);
                dj2.setEnabled(true);
                dg2.setEnabled(true);
                dn2.setEnabled(true);
                new Thread(){
                    @Override
                    public void run() {
                        String result=  new DLkdData().GetMmess(xx);
                        Message message=new Message();
                        message.obj=result;
                        message.what=0001;
                        handler.sendMessage(message);
                    }
                }.start();


                break;
            case R.id.dj2:
                dl2.setEnabled(true);
                dj2.setEnabled(false);
                dg2.setEnabled(true);
                dn2.setEnabled(true);
                new Thread(){
                    @Override
                    public void run() {
                        String result=  new DJkdData().GetMmess(xx);
                        Message message=new Message();
                        message.obj=result;
                        message.what=0002;
                        handler.sendMessage(message);
                    }
                }.start();



                break;
            case R.id.dg2:
                dl2.setEnabled(true);
                dj2.setEnabled(true);
                dg2.setEnabled(false);
                dn2.setEnabled(true);
                new Thread(){
                    @Override
                    public void run() {
                        String result=  new DGspData().GetMmess(xx);
                        Message message=new Message();
                        message.obj=result;
                        message.what=0003;
                        handler.sendMessage(message);
                    }
                }.start();
                break;
            case R.id.dn2:
                dl2.setEnabled(true);
                dj2.setEnabled(true);
                dg2.setEnabled(true);
                dn2.setEnabled(false);

                new Thread(){
                    @Override
                    public void run() {
                        String result=  new DNbsData().GetMmess(xx);
                        Message message=new Message();
                        message.obj=result;
                        message.what=0004;
                        handler.sendMessage(message);
                    }
                }.start();
            default:
                break;
        }

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    class  MyDLkdBase extends BaseAdapter{

        @Override
        public int getCount() {
            return dLkdIfos.size();
        }

        @Override
        public Object getItem(int position) {
            return dLkdIfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(),R.layout.ddlistitem_layout,null);
            TextView textViewtime= (TextView) convertView.findViewById(R.id.dditemtexttime);
            TextView textViewshdi= (TextView) convertView.findViewById(R.id.dditemtextshdz);
            TextView textViewxf= (TextView) convertView.findViewById(R.id.dditemtextxf);
            ImageView imageView1= (ImageView) convertView.findViewById(R.id.ddlistimg);
            Button button= (Button) convertView.findViewById(R.id.ddlistbtjd);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userIfo.getUserid().equals(dLkdIfos.get(position).getUserid())) {
                        Toast.makeText(getActivity(), "不可以接自己发布的订单", Toast.LENGTH_LONG).show();
                    } else {

                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
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
                                                new JDData().AddData(dLkdIfos.get(position).getId(), userIfo.getUserid(), userIfo.getHm(), "代领", "否", userIfo.getNick(),xdsj);
                                                new DLkdData().GetJDMmess(dLkdIfos.get(position).getId());
                                                new Thread(){
                                                    @Override
                                                    public void run() {
                                                        String result=  new DLkdData().GetMmess(xx);
                                                        Message message=new Message();
                                                        message.obj=result;
                                                        message.what=0001;
                                                        handler.sendMessage(message);
                                                    }
                                                }.start();
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
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final String userid=meun.userIfo.getUserid();
                    Intent intent1=new Intent(getActivity(),SeeDLkdxq.class);
                    intent1.putExtra("DLKD",dLkdIfos.get(position));
                    intent1.putExtra("userid",userIfo);
                  getActivity().startActivity(intent1);
                }
            });
            imageView1.setImageResource(R.drawable.dlimg);
            textViewshdi.setText(dLkdIfos.get(position).getShdz());
            textViewtime.setText("下单时间:"+dLkdIfos.get(position).getXdsj());
            textViewxf.setText("￥"+dLkdIfos.get(position).getXf());
            ImageView imageView= (ImageView) convertView.findViewById(R.id.dditemimgchat);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final YWIMKit mIMKit = YWAPI.getIMKitInstance(meun.userIfo.getUserid(), "24700296");

                    final String target =dLkdIfos.get(position).getUserid(); //消息接收者ID
                    final String appkey = "24700296"; //消息接收者appKey
                    Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    class  MyDJkdBase extends BaseAdapter{

        @Override
        public int getCount() {
            return DJkdIfos.size();
        }

        @Override
        public Object getItem(int position) {
            return DJkdIfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(),R.layout.ddlistitem_layout,null);
            TextView textViewtime= (TextView) convertView.findViewById(R.id.dditemtexttime);
            TextView textViewshdi= (TextView) convertView.findViewById(R.id.dditemtextshdz);
            TextView textViewxf= (TextView) convertView.findViewById(R.id.dditemtextxf);
            TextView textViewtitle= (TextView) convertView.findViewById(R.id.dditemtexttitle);
            textViewtitle.setText("代寄快递");
            Button button= (Button) convertView.findViewById(R.id.ddlistbtjd);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userIfo.getUserid().equals(DJkdIfos.get(position).getUserid())) {
                        Toast.makeText(getActivity(), "不可以接自己发布的订单", Toast.LENGTH_LONG).show();
                    } else {

                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
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
                                                new JDData().AddData(DJkdIfos.get(position).getId(), userIfo.getUserid(), userIfo.getHm(), "代寄", "否", userIfo.getNick(),xdsj);
                                                new DJkdData().GetJDMmess(DJkdIfos.get(position).getId());
                                                new Thread(){
                                                    @Override
                                                    public void run() {
                                                        String result=  new DJkdData().GetMmess(xx);
                                                        Message message=new Message();
                                                        message.obj=result;
                                                        message.what=0002;
                                                        handler.sendMessage(message);
                                                    }
                                                }.start();
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
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final String userid=meun.userIfo.getUserid();
                    Intent intent1=new Intent(getActivity(),SeeDJkdxq.class);
                    intent1.putExtra("DJkdIfo",DJkdIfos.get(position));
                    intent1.putExtra("userid",userIfo);
                    getActivity().startActivity(intent1);
                }
            });
            textViewshdi.setText(DJkdIfos.get(position).getQhaddress());
            textViewtime.setText("下单时间:"+DJkdIfos.get(position).getXdsj());
            textViewxf.setText("￥"+DJkdIfos.get(position).getXf());
            ImageView imageView= (ImageView) convertView.findViewById(R.id.dditemimgchat);
            ImageView imageView1= (ImageView) convertView.findViewById(R.id.ddlistimg);
            imageView1.setImageResource(R.drawable.djimg);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final YWIMKit mIMKit = YWAPI.getIMKitInstance(meun.userIfo.getUserid(), "24700296");

                    final String target =DJkdIfos.get(position).getUserid(); //消息接收者ID
                    final String appkey = "24700296"; //消息接收者appKey
                    Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    class  MyDGspBase extends BaseAdapter{

        @Override
        public int getCount() {
            return dGspIfos.size();
        }

        @Override
        public Object getItem(int position) {
            return dGspIfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(),R.layout.ddlistitem_layout,null);
            TextView textViewtime= (TextView) convertView.findViewById(R.id.dditemtexttime);
            TextView textViewshdi= (TextView) convertView.findViewById(R.id.dditemtextshdz);
            TextView textViewxf= (TextView) convertView.findViewById(R.id.dditemtextxf);
            textViewshdi.setText(dGspIfos.get(position).getShaddress());
            TextView textViewtitle= (TextView) convertView.findViewById(R.id.dditemtexttitle);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final String userid=meun.userIfo.getUserid();
                    Intent intent1=new Intent(getActivity(),SeeDGspxq.class);
                    intent1.putExtra("DGSP",dGspIfos.get(position));
                    intent1.putExtra("userid",userIfo);
                    getActivity().startActivity(intent1);
                }
            });

            Button button= (Button) convertView.findViewById(R.id.ddlistbtjd);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userIfo.getUserid().equals(dGspIfos.get(position).getUserid())) {
                        Toast.makeText(getActivity(), "不可以接自己发布的订单", Toast.LENGTH_LONG).show();
                    } else {

                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
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
                                                new JDData().AddData(dGspIfos.get(position).getId(), userIfo.getUserid(), userIfo.getHm(), "代购", "否", userIfo.getNick(),xdsj);
                                                new DLkdData().GetJDMmess(dGspIfos.get(position).getId());
                                                new Thread(){
                                                    @Override
                                                    public void run() {
                                                        String result=  new DGspData().GetMmess(xx);
                                                        Message message=new Message();
                                                        message.obj=result;
                                                        message.what=0003;
                                                        handler.sendMessage(message);
                                                    }
                                                }.start();
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
            textViewtitle.setText("代购商品");
            textViewtime.setText("下单时间:"+dGspIfos.get(position).getXdsj());
            textViewxf.setText("￥"+dGspIfos.get(position).getXf());
            ImageView imageView= (ImageView) convertView.findViewById(R.id.dditemimgchat);
            ImageView imageView1= (ImageView) convertView.findViewById(R.id.ddlistimg);
            imageView1.setImageResource(R.drawable.dgimg);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final YWIMKit mIMKit = YWAPI.getIMKitInstance(meun.userIfo.getUserid(), "24700296");

                    final String target =dGspIfos.get(position).getUserid(); //消息接收者ID
                    final String appkey = "24700296"; //消息接收者appKey
                    Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    class  MyDNbsBase extends BaseAdapter{

        @Override
        public int getCount() {
            return dNbsIfos.size();
        }

        @Override
        public Object getItem(int position) {
            return dNbsIfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(),R.layout.ddlistitem_layout,null);
            TextView textViewtime= (TextView) convertView.findViewById(R.id.dditemtexttime);
            TextView textViewshdi= (TextView) convertView.findViewById(R.id.dditemtextshdz);
            TextView textViewxf= (TextView) convertView.findViewById(R.id.dditemtextxf);
            textViewshdi.setText(dNbsIfos.get(position).getBsaddress());
            TextView textViewtitle= (TextView) convertView.findViewById(R.id.dditemtexttitle);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final String userid=meun.userIfo.getUserid();
                    Intent intent1=new Intent(getActivity(),SeeDNbsxq.class);
                    intent1.putExtra("DNBS",dNbsIfos.get(position));
                    intent1.putExtra("userid",userIfo);
                    getActivity().startActivity(intent1);
                }
            });


            Button button= (Button) convertView.findViewById(R.id.ddlistbtjd);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userIfo.getUserid().equals(dNbsIfos.get(position).getUserid())) {
                        Toast.makeText(getActivity(), "不可以接自己发布的订单", Toast.LENGTH_LONG).show();
                    } else {

                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
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
                                                new JDData().AddData(dNbsIfos.get(position).getId(), userIfo.getUserid(), userIfo.getHm(), "帮手", "否", userIfo.getNick(),xdsj);
                                                new DNbsData().GetJDMmess(dNbsIfos.get(position).getId());
                                                new Thread(){
                                                    @Override
                                                    public void run() {
                                                        String result=  new DNbsData().GetMmess(xx);
                                                        Message message=new Message();
                                                        message.obj=result;
                                                        message.what=0004;
                                                        handler.sendMessage(message);
                                                    }
                                                }.start();
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
            textViewtitle.setText("电脑帮手");
            textViewtime.setText("下单时间:"+dNbsIfos.get(position).getXdsj());
            textViewxf.setText("￥"+dNbsIfos.get(position).getXf());
            ImageView imageView= (ImageView) convertView.findViewById(R.id.dditemimgchat);
            ImageView imageView1= (ImageView) convertView.findViewById(R.id.ddlistimg);
            imageView1.setImageResource(R.drawable.dnimg);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Meun meun= (Meun) getActivity();
                    final YWIMKit mIMKit = YWAPI.getIMKitInstance(meun.userIfo.getUserid(), "24700296");

                    final String target =dNbsIfos.get(position).getUserid(); //消息接收者ID
                    final String appkey = "24700296"; //消息接收者appKey
                    Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
}
