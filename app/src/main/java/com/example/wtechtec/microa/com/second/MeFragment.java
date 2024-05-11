package com.example.wtechtec.microa.com.second;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.JDData;
import com.example.wtechtec.microa.com.first.MainActivity;
import com.example.wtechtec.microa.com.my.ModifyPassword;
import com.example.wtechtec.microa.com.my.MyDGsp;
import com.example.wtechtec.microa.com.my.MyDJkd;
import com.example.wtechtec.microa.com.my.MyDLkd;
import com.example.wtechtec.microa.com.my.MyDNbs;
import com.example.wtechtec.microa.com.my.MyJD;
import com.example.wtechtec.microa.com.sql.LoginSql;

import java.util.Calendar;

/**
 * Created by WtecHtec on 2018/1/3.
 */

public class MeFragment extends Fragment {
    private LinearLayout linearLayoutout,linearLayoutmodify,linearLayoutdlkd,linearLayoutdjkd,linearLayoutdgsp,linearLayoutdnbs,linearLayoutjd;
    private ImageView imageViewout,imageViewmodify,imageViewfb,imageViewdlkd,imageViewdjkd,imageViewdgsp,imageViewdnbs,imageViewjd;
    private LoginSql loginsql;
    private TextView textView,textViewxx,textViewname,textViewphone;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_layout,container,false);
  InitView(view);
        DoMove();
        return view;
    }

    private void DoMove() {
    linearLayoutout.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                 //   imageViewout.setImageResource(R.drawable.imgt);
                    break;
                case  MotionEvent.ACTION_UP:
                    imageViewout.setImageResource(R.drawable.img);

                    final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                    normalDialog.setIcon(R.drawable.dialog);
                    normalDialog.setTitle("退出提示");
                    normalDialog.setMessage("是否确定退出本次登陆？");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                    Meun meun=(Meun) getActivity();
                                    loginsql.delete(meun.userIfo.getUserid());
                                    Toast.makeText(getContext(),"退出",Toast.LENGTH_LONG).show();
                                    YWIMKit mIMKit = YWAPI.getIMKitInstance(meun.userIfo.getUserid(), "24700296");
                                    IYWLoginService loginService =      mIMKit.getLoginService();
                                    loginService.logout(new IWxCallback() {
                                        @Override
                                        public void onSuccess(Object... objects) {
                                        }

                                        @Override
                                        public void onError(int i, String s) {

                                        }

                                        @Override
                                        public void onProgress(int i) {

                                        }
                                    });

                                    Intent intent=new Intent(getActivity(), MainActivity.class);
                                    getActivity().startActivity(intent);

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
//



                    break;

            }
            return true;
        }
    });
        linearLayoutmodify.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                     //   imageViewmodify.setImageResource(R.drawable.imgt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewmodify.setImageResource(R.drawable.img);

                        Meun meun=(Meun) getActivity();

                        Intent intent=new Intent(getActivity(), ModifyPassword.class);
                        intent.putExtra("Modify",meun.userIfo);
                        getActivity().startActivity(intent);

                        break;

                }
                return true;
            }
        });


        linearLayoutdlkd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                     //   imageViewdlkd.setImageResource(R.drawable.imgt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewdlkd.setImageResource(R.drawable.img);

                        Meun meun=(Meun) getActivity();

                        Intent intent=new Intent(getActivity(), MyDLkd.class);
                        intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
                        getActivity().startActivity(intent);

                        break;

                }
                return true;
            }
        });

        linearLayoutdjkd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                     //   imageViewdjkd.setImageResource(R.drawable.imgt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewdjkd.setImageResource(R.drawable.img);

                        Meun meun=(Meun) getActivity();

                        Intent intent=new Intent(getActivity(), MyDJkd.class);
                        intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
                        getActivity().startActivity(intent);

                        break;

                }
                return true;
            }
        });

        linearLayoutdgsp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                      //  imageViewdgsp.setImageResource(R.drawable.imgt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewdgsp.setImageResource(R.drawable.img);

                        Meun meun=(Meun) getActivity();

                        Intent intent=new Intent(getActivity(), MyDGsp.class);
                        intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
                        getActivity().startActivity(intent);

                        break;

                }
                return true;
            }
        });


         linearLayoutdnbs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                      //  imageViewdnbs.setImageResource(R.drawable.imgt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewdnbs.setImageResource(R.drawable.img);

                        Meun meun=(Meun) getActivity();

                        Intent intent=new Intent(getActivity(), MyDNbs.class);
                        intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
                        getActivity().startActivity(intent);

                        break;

                }
                return true;
            }
        });

        linearLayoutjd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                     //   imageViewjd.setImageResource(R.drawable.imgt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewjd.setImageResource(R.drawable.img);

                        Meun meun=(Meun) getActivity();

                        Intent intent=new Intent(getActivity(), MyJD.class);
                        intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
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
                    case MotionEvent.ACTION_DOWN:
                     //   imageViewfb.setImageResource(R.drawable.fb102);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageViewfb.setImageResource(R.drawable.fb101);
                        Meun meun= (Meun) getActivity();

                          Intent intent=new Intent(getActivity(), com.example.wtechtec.microa.com.fb.MainActivity.class);
                       intent.putExtra("userid",meun.userIfo.getUserid());
                        intent.putExtra("xx",meun.userIfo.getXx());
                        getActivity().startActivity(intent);


                        break;

                }
                return true;
            }
        });

    }

    private void InitView(View view) {
      linearLayoutout= (LinearLayout) view.findViewById(R.id.melineout);
         imageViewout= (ImageView) view.findViewById(R.id.meimgout);
        linearLayoutmodify= (LinearLayout) view.findViewById(R.id.melinemodify);
        imageViewmodify= (ImageView) view.findViewById(R.id.meimgmodify);
        loginsql=new LoginSql(getContext());
        textView= (TextView) view.findViewById(R.id.metextxh);
        Meun meun=(Meun) getActivity();
           textView.setText(meun.userIfo.getUserid());
           imageViewfb= (ImageView) view.findViewById(R.id.meimgfb);
        linearLayoutdlkd= (LinearLayout) view.findViewById(R.id.melinedlkd);
        imageViewdlkd= (ImageView) view.findViewById(R.id.meimgdlkd);
        textViewxx= (TextView) view.findViewById(R.id.metextxx);
        textViewxx.setText(meun.userIfo.getXx());

        linearLayoutdjkd= (LinearLayout) view.findViewById(R.id.melinedjkd);
        imageViewdjkd= (ImageView) view.findViewById(R.id.meimgdjkd);

        linearLayoutdgsp= (LinearLayout) view.findViewById(R.id.melinedgsp);
        imageViewdgsp= (ImageView) view.findViewById(R.id.meimgdgsp);

        linearLayoutdnbs= (LinearLayout) view.findViewById(R.id.melinednbs);
        imageViewdnbs= (ImageView) view.findViewById(R.id.meimgdnbs);

        textViewname= (TextView) view.findViewById(R.id.metextname);
        textViewphone= (TextView) view.findViewById(R.id.metextphone);

        textViewphone.setText(meun.userIfo.getHm());
        textViewname.setText(meun.userIfo.getNick());

        linearLayoutjd= (LinearLayout) view.findViewById(R.id.melinejd);
        imageViewjd= (ImageView) view.findViewById(R.id.meimgjd);
    }
}
