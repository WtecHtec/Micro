package com.example.wtechtec.microa.com.second;

        import android.app.Fragment;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v7.app.AlertDialog;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.example.wtechtec.microa.R;
        import com.example.wtechtec.microa.com.data.DNbsData;
        import com.example.wtechtec.microa.com.data.JDData;
        import com.example.wtechtec.microa.com.data.JZgcData;
        import com.example.wtechtec.microa.com.ifo.JZgcIfo;
        import com.example.wtechtec.microa.com.jzgc.MyJZgc;
        import com.example.wtechtec.microa.com.see.OneHttpTest;
        import com.example.wtechtec.microa.com.see.OneMainActivity;
        import com.example.wtechtec.microa.com.see.User;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.InputStream;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment extends android.support.v4.app.Fragment {
    public ArrayList<String> onelist=new ArrayList<>();
    private OneHttpTest oneHttpTest;
    private  Meun meun;
private List<JZgcIfo>   dlkdifos;
 private  TextView textViewo,textViewt;
    private TextView textView,textViewtime;

    private LinearLayout imageViewsee ,imageViewjz;
    private ImageView   imageView ,imageViewfb;
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

                            JSONObject jsonObject1= (JSONObject) jsonArray.get(0);
                            JSONObject jsonObject2=jsonObject1.getJSONObject("share_info");

                            final String image=jsonObject2.getString("image");
                            String content=jsonObject2.getString("content");
                        textView.setText(content);

                              new Thread(){
                                  @Override
                                  public void run() {
                                      super.run();
                                      InputStream list= oneHttpTest.DoImageView(image);
                                           Bitmap blist= BitmapFactory.decodeStream(list);
                                      Message message=new Message();
                                      message.obj=blist;
                                      message.what=2;
                                      handler.sendMessage(message);
                                  }
                              }.start();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    Bitmap bitmap= (Bitmap) msg.obj;
                    imageView.setImageBitmap(bitmap);
                    break;
                case  3:

                    String result= (String) msg.obj;

                    JSONObject json= null;
                    try {
                        json = new JSONObject(result);
                        JSONArray jsonArray=json.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++) {


                            onelist.add((String) jsonArray.get(i));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                         new  Thread(){
                             @Override
                             public void run() {
                                 String list=   oneHttpTest.DoOneList(onelist.get(0));

                                 Message message=new Message();
                                 message.obj=list;
                                 message.what=1;
                                 handler.sendMessage(message);
                             }
                         }.start();


                    break;
                case  4:
                    String  result1= (String) msg.obj;

                    try {
                        JSONObject jsonObject=new JSONObject(result1);
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
                        textViewo.setText(dlkdifos.get(0).getJzName());
                        textViewo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                                normalDialog.setView(TextViewo());

                                normalDialog.show();
                            }
                        });
                        textViewt.setText(dlkdifos.get(1).getJzName());
                        textViewt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                                normalDialog.setView(TextViewt());

                                normalDialog.show();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }


        }
    };

    private  View TextViewo(){
        View convertView=View.inflate(getActivity(),R.layout.jzgc_xqlist_layout,null);
convertView.setMinimumHeight(500);
        convertView.setMinimumWidth(860);
        TextView textViewname= (TextView) convertView.findViewById(R.id.jzgctextname);
        TextView textViewdy= (TextView) convertView.findViewById(R.id.jzgctextdy);
        TextView textViewqx= (TextView) convertView.findViewById(R.id.jzgctextqx);
        TextView textViewdz= (TextView) convertView.findViewById(R.id.jzgctextdz);
        final TextView textViewlx= (TextView) convertView.findViewById(R.id.jzgctextlx);
        TextView textViewrs= (TextView) convertView.findViewById(R.id.jzgctextrs);
        textViewrs.setText(dlkdifos.get(0).getZpNumber());
        textViewname.setText(dlkdifos.get(0).getJzName());
        textViewdy.setText(dlkdifos.get(0).getDaiyu());
        textViewqx.setText(dlkdifos.get(0).getEndtime());
        textViewdz.setText(dlkdifos.get(0).getWorkstation());
        textViewlx.setText(dlkdifos.get(0).getPhoneNumber());
        ImageView imageView= (ImageView) convertView.findViewById(R.id.jzdcimgcall);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                normalDialog.setIcon(R.drawable.call);
                normalDialog.setTitle("拨打电话提示");
                normalDialog.setMessage("是否确定拨打联系电话？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+textViewlx.getText().toString()+""));
                          meun.startActivity(intent);
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


    private  View TextViewt(){
        View convertView=View.inflate(getActivity(),R.layout.jzgc_xqlist_layout,null);
        convertView.setMinimumHeight(500);
        convertView.setMinimumWidth(860);
        TextView textViewname= (TextView) convertView.findViewById(R.id.jzgctextname);
        TextView textViewdy= (TextView) convertView.findViewById(R.id.jzgctextdy);
        TextView textViewqx= (TextView) convertView.findViewById(R.id.jzgctextqx);
        TextView textViewdz= (TextView) convertView.findViewById(R.id.jzgctextdz);
        final TextView textViewlx= (TextView) convertView.findViewById(R.id.jzgctextlx);
        TextView textViewrs= (TextView) convertView.findViewById(R.id.jzgctextrs);
        textViewrs.setText(dlkdifos.get(1).getZpNumber());
        textViewname.setText(dlkdifos.get(1).getJzName());
        textViewdy.setText(dlkdifos.get(1).getDaiyu());
        textViewqx.setText(dlkdifos.get(1).getEndtime());
        textViewdz.setText(dlkdifos.get(1).getWorkstation());
        textViewlx.setText(dlkdifos.get(1).getPhoneNumber());
        ImageView imageView= (ImageView) convertView.findViewById(R.id.jzdcimgcall);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                normalDialog.setIcon(R.drawable.call);
                normalDialog.setTitle("拨打电话提示");
                normalDialog.setMessage("是否确定拨打联系电话？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+textViewlx.getText().toString()+""));
                                meun.startActivity(intent);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
         InitView(view);
        dlkdifos=new ArrayList<>();
         DoMove();
        OneList();
        new Thread(){
            @Override
            public void run() {
                String result=   new JZgcData().GetIdMmess();
                Message message=new Message();
                message.what=4;
                message.obj=result;
                handler.sendMessage(message);
            }
        }.start();
        return view;
    }

    private void OneList() {
        new  Thread(){
            @Override
            public void run() {
                String list=   oneHttpTest.DoLists();
                Message message=new Message();
                message.obj=list;
                message.what=3;
                handler.sendMessage(message);
            }
        }.start();

    }

    private void DoMove() {
        imageViewsee.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case  MotionEvent.ACTION_DOWN:
                //    imageViewsee.setImageResource(R.drawable.moret);

                    break;
                case  MotionEvent.ACTION_UP:


                  //  imageViewsee.setImageResource(R.drawable.moreo);
                    Intent intent=new Intent(getActivity(), OneMainActivity.class);
                    intent.putStringArrayListExtra("OneList",onelist);
                    getActivity().startActivity(intent);
                    break;

            }
                return true;

            }
        });
        imageViewjz.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                     //   imageViewjz.setImageResource(R.drawable.moret);

                        break;
                    case  MotionEvent.ACTION_UP:

                     //   imageViewjz.setImageResource(R.drawable.moreo);
                        Intent intent=new Intent(getActivity(), MyJZgc.class);
                        getActivity().startActivity(intent);
                        break;

                }
                return true;

            }
        });
        imageViewfb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        imageViewfb.setImageResource(R.drawable.fb102);

                        break;
                    case  MotionEvent.ACTION_UP:

                        imageViewfb.setImageResource(R.drawable.fb101);
                        Intent intent=new Intent(getActivity(), com.example.wtechtec.microa.com.fb.MainActivity.class);
                        Meun meun= (Meun) getActivity();
                      intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
                        getActivity().startActivity(intent);
                        break;

                }
                return true;

            }
        });

    }

    private void InitView( View view) {
        oneHttpTest=new OneHttpTest();
        textView= (TextView) view.findViewById(R.id.fgtextsee);
        imageView= (ImageView) view.findViewById(R.id.fgimgsee);
  textViewo= (TextView) view.findViewById(R.id.jgtextjzgco);
        textViewt= (TextView) view.findViewById(R.id.jgtextjzgct);
        imageViewjz= (LinearLayout ) view.findViewById(R.id.fgimgviewjz);
        imageViewsee= (LinearLayout) view.findViewById(R.id.fgimgviewsee);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String data=simpleDateFormat.format(System.currentTimeMillis());
        imageViewfb= (ImageView) view.findViewById(R.id.myimgfb);
        textViewtime= (TextView) view.findViewById(R.id.fgtexttime);
        String[] dates=data.split("-");
        textViewtime.setText(dates[0]+" / "+dates[1]+" / "+dates[2]);
        meun= (Meun) getActivity();
    }

}

