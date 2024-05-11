package com.example.wtechtec.microa.com.see;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wtechtec.microa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by xuetao on 2017/9/11.
 */

public class OneMainActivity extends AppCompatActivity {
private ArrayList<String> onelist=new ArrayList<>();
    private  OneHttpTest oneHttpTest;
    private  MyOneAdapter myOneAdapter;
    private List<User> userList=new ArrayList<>();
    private ImageView imageView,imageView_side,imageView_back,imageViewoneback;
    private  TextView textViewtime;
    private  int i=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:

                    String s= (String) msg.obj;
                    try {

                        JSONObject jsonObject=new JSONObject(s);
                        JSONObject object=jsonObject.getJSONObject("data");
                        JSONArray jsonArray=object.getJSONArray("content_list");
                        JSONObject jsonObject3=object.getJSONObject("weather");



                        for (int i=1;i<jsonArray.length();i++){
                            JSONObject jsonObject1= (JSONObject) jsonArray.get(i);
                            JSONObject jsonObject2=jsonObject1.getJSONObject("share_info");
                            String title=jsonObject2.getString("title");
                            String image=jsonObject2.getString("image");
                            String content=jsonObject1.getString("forward");
                            String url=jsonObject2.getString("url");
                            userList.add(new User(title,image,content,url));
                        }
                        myOneAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    Bitmap bitmap= (Bitmap) msg.obj;
                     imageView.setImageBitmap(bitmap);
                    break;
            }


        }
    };
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionbr=this.getSupportActionBar();
        actionbr.hide();
        setContentView(R.layout.one_layout);
        Intent intent=getIntent();
        myOneAdapter=new MyOneAdapter();
        oneHttpTest =new OneHttpTest();


        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String data=simpleDateFormat.format(System.currentTimeMillis());
        textViewtime= (TextView) findViewById(R.id.onetexttime);
        String[] dates=data.split("-");
      textViewtime.setText(dates[0]+" / "+dates[1]+" / "+dates[2]);
        imageView_back= (ImageView) findViewById(R.id.oneimgup);
        imageView_side= (ImageView) findViewById(R.id.oneimgdown);
        imageViewoneback= (ImageView) findViewById(R.id.oneimgback);

        imageViewoneback.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        imageViewoneback.setImageResource(R.drawable.backt);
                        break;
                    case MotionEvent.ACTION_UP:
                        imageViewoneback.setImageResource(R.drawable.back);
                        finish();
                        break;

                }

                return true;
            }
        });
        imageView_side.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        imageView_side.setImageResource(R.drawable.moret);

                        break;
                    case  MotionEvent.ACTION_UP:


                        imageView_side.setImageResource(R.drawable.moreo);

                        userList.clear();
                        if (i<onelist.size()-1){
                            i=i+1;
                            Calendar cal   =   Calendar.getInstance();
                            cal.add(Calendar.DATE,   -i);
                            String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
                            String[] dates=yesterday.split("-");

                            textViewtime.setText(dates[0]+" / "+dates[1]+" / "+dates[2]);
                            Log.i("One",yesterday);
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    Message message=new Message();
                                    message.obj=oneHttpTest.DoOneList(onelist.get(i));
                                    message.what=1;
                                    handler.sendMessage(message);
                                }
                            }.start();

                        }

                        break;

                }
                return true;

            }
        });
        imageView_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        imageView_back.setImageResource(R.drawable.upt);

                        break;
                    case  MotionEvent.ACTION_UP:


                        imageView_back.setImageResource(R.drawable.upo);

                        if (i>0){
                            i=i-1;
                            userList.clear();

                            Calendar cal   =   Calendar.getInstance();
                            cal.add(Calendar.DATE,   -i);
                            String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
                            String[] dates=yesterday.split("-");
                            textViewtime.setText(dates[0]+" / "+dates[1]+" / "+dates[2]);
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    Message message=new Message();
                                    message.obj=oneHttpTest.DoOneList(onelist.get(i));
                                    message.what=1;
                                    handler.sendMessage(message);
                                }
                            }.start();

                        }
                        break;

                }
                return true;

            }
        });






        listView= (ListView) findViewById(R.id.list_one);
         onelist=   intent.getStringArrayListExtra("OneList");
    new  Thread(){
        @Override
        public void run() {
            super.run();
           String s= oneHttpTest.DoOneList(onelist.get(0));
            Message message=new Message();
       message.what=1;
            message.obj=s;
            handler.sendMessage(message);
        }
    }.start();
        listView.setAdapter(myOneAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(OneMainActivity.this,OneListsWebActivity.class);
                intent1.putExtra("URL",userList.get(position).getUrl());
                startActivity(intent1);

            }
        });

    }


    class  MyOneAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return userList.size();
        }

        @Override
        public Object getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(OneMainActivity.this,R.layout.onelist_layout,null);
            TextView textViewtitle= (TextView) convertView.findViewById(R.id.text_onetitme);
            TextView textViewcontext= (TextView) convertView.findViewById(R.id.text_onecontext);
            imageView= (ImageView) convertView.findViewById(R.id.image_oneiamge);
            textViewtitle.setText(userList.get(position).getTitle());
            textViewcontext.setText(userList.get(position).getContext());
            new  Thread(){
                @Override
                public void run() {
                    super.run();
                InputStream image=oneHttpTest.DoImageView(userList.get(position).getImage());
                    Bitmap bitmap= BitmapFactory.decodeStream(image);
                    Message message=new Message();
                    message.what=2;
                    message.obj=bitmap;
                    handler.sendMessage(message);
                }
            }.start();


            return convertView;
        }
    }
}
