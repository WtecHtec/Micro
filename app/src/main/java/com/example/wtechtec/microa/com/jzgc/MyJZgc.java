package com.example.wtechtec.microa.com.jzgc;

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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DJkdData;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.JZgcData;
import com.example.wtechtec.microa.com.ifo.DJkdIfo;
import com.example.wtechtec.microa.com.ifo.DLkdIfo;
import com.example.wtechtec.microa.com.ifo.JZgcIfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.wtechtec.microa.R.layout.meun;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class MyJZgc extends AppCompatActivity {


    private ListView listJZgc;
    private ImageView imageView;
    private List<JZgcIfo> dlkdifos;
   private  TextView textView;
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
                    String jzName=jsonArray.getJSONObject(i).getString("jzName");
                    String daiyu=jsonArray.getJSONObject(i).getString("daiyu");
                    String gxTime=jsonArray.getJSONObject(i).getString("gxTime");
                    String zpNumber=jsonArray.getJSONObject(i).getString("zpNumber");
                    String endtime=jsonArray.getJSONObject(i).getString("endtime");
                    String workstation=jsonArray.getJSONObject(i).getString("workstation");
                    String phoneNumber=jsonArray.getJSONObject(i).getString("phoneNumber");
                    dlkdifos.add(new JZgcIfo(id,jzName,daiyu,gxTime,zpNumber,endtime,workstation,phoneNumber));
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
        setContentView(R.layout.myjzgc_layout);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.hide();
        InitView();
        DoMouse();
    }



    private void InitView() {
     Intent intent=getIntent();
        imageView= (ImageView) findViewById(R.id.dlkdimgback);

      textView= (TextView) findViewById(R.id.myjzgc_time);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String data=simpleDateFormat.format(System.currentTimeMillis());

        String[] dates=data.split("-");
        textView.setText(dates[0]+" / "+dates[1]+" / "+dates[2]);




        listJZgc= (ListView) findViewById(R.id.listJZgc);
        dlkdifos=new ArrayList<>();

        mybasp=new MyDabasep();
        listJZgc.setAdapter(mybasp);
        new Thread(){
            @Override
            public void run() {
             String result=   new JZgcData().GetMmessAll();
                Message message=new Message();
                message.obj=result;
                handler.sendMessage(message);
            }
        }.start();


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

            convertView=View.inflate(MyJZgc.this,R.layout.jzgc_xqlist_layout,null);
             TextView textViewname= (TextView) convertView.findViewById(R.id.jzgctextname);
            TextView textViewdy= (TextView) convertView.findViewById(R.id.jzgctextdy);
            TextView textViewqx= (TextView) convertView.findViewById(R.id.jzgctextqx);
            TextView textViewdz= (TextView) convertView.findViewById(R.id.jzgctextdz);
            final TextView textViewlx= (TextView) convertView.findViewById(R.id.jzgctextlx);
TextView textViewrs= (TextView) convertView.findViewById(R.id.jzgctextrs);
            textViewrs.setText(dlkdifos.get(position).getZpNumber());
            textViewname.setText(dlkdifos.get(position).getJzName());
            textViewdy.setText(dlkdifos.get(position).getDaiyu());
            textViewqx.setText(dlkdifos.get(position).getEndtime());
            textViewdz.setText(dlkdifos.get(position).getWorkstation());
            textViewlx.setText(dlkdifos.get(position).getPhoneNumber());

            ImageView imageView= (ImageView) convertView.findViewById(R.id.jzdcimgcall);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyJZgc.this);
                    normalDialog.setIcon(R.drawable.call);
                    normalDialog.setTitle("拨打电话提示");
                    normalDialog.setMessage("是否确定拨打联系电话？");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+textViewlx.getText().toString()+""));
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
            return convertView;
        }
    }


}
