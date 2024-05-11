package com.example.wtechtec.microa.com.fb;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DJkdData;
import com.example.wtechtec.microa.com.data.DLkdData;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jayce on 2018/1/5.
 */

public class DJActivity extends Fragment {
    private Calendar cal;
    private int year,month,day;
    private  String  xdsj ,userid,xx;
    private Button button;
    private EditText editTextjjrname,editTextjjrphone,editTextjjraddress,editTextsjrname,editTextsjrphone,editTextsjraddress,editTextqhaddress,editTextbzly,editTextxf;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dj_layout, container, false);

   InItView(view);
DoThing();
        return view;
    }

    private void DoThing() {

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){

                    case  MotionEvent.ACTION_DOWN:
                        button.setBackgroundColor(Color.RED);
                        break;
                    case MotionEvent.ACTION_UP:
                        button.setBackgroundColor(Color.parseColor("#FF7F00"));

                 final String jjrname=editTextjjrname.getText().toString();
                        final String jjrphone=editTextjjrphone.getText().toString();
                        final String jjraddress=editTextjjraddress.getText().toString();
                        final String sjrname=editTextsjrname.getText().toString();
                            final String sjrphone=editTextsjrphone.getText().toString();
                        final String sjraddress=editTextsjraddress.getText().toString();
                        final String qhaddress=editTextqhaddress.getText().toString();

                        final String bzly=editTextbzly.getText().toString();
                        final String xf=editTextxf.getText().toString();
                        int hour=cal.get(Calendar.HOUR_OF_DAY);
                        int mm=cal.get(Calendar.MINUTE);
                        int m=cal.get(Calendar.SECOND);
                        xdsj=year+"-"+(month+1)+"-"+day+"-"+hour+"-"+mm+"-"+m;
                        if(jjrname.equals("")||jjrphone.equals("")||jjraddress.equals("")||sjrname.equals("")||sjrphone.equals("")||sjraddress.equals("")||qhaddress.equals("")||bzly.equals("")||xf.equals("")||xdsj.equals("")){
                            Toast.makeText(getActivity(),"信息不完整",Toast.LENGTH_LONG).show();

                        }else {

                            new Thread(){
                                @Override
                                public void run() {
                                   new DJkdData().AddData(userid,jjrname, jjrphone, jjraddress, sjrname, sjrphone,  sjraddress, qhaddress,  bzly,  xf,  xdsj,xx);

                                    getActivity().finish();
                                }
                            }.start();

                            Toast.makeText(getActivity(),"发布成功",Toast.LENGTH_LONG).show();



                        }
                        break;
                }

                return true;
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.N)
    private void InItView(View view) {

        MainActivity m= (MainActivity) getActivity();
        userid=m.userid;
        xx=m.xx;

        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒

        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);

        editTextjjrname= (EditText) view.findViewById(R.id.djetjjrname);
        editTextjjrphone= (EditText) view.findViewById(R.id.djetjjrphone);
        editTextjjraddress= (EditText) view.findViewById(R.id.djetjjaddress);

        editTextsjrname= (EditText) view.findViewById(R.id.djetsjrname);
        editTextsjrphone= (EditText) view.findViewById(R.id.djetsjrphone);
        editTextsjraddress= (EditText) view.findViewById(R.id.djetsjaddress);

        editTextqhaddress= (EditText) view.findViewById(R.id.djetqjaddress);
        editTextbzly= (EditText) view.findViewById(R.id.djetbzly);
        editTextxf= (EditText) view.findViewById(R.id.djetxf);

        button= (Button) view.findViewById(R.id.djbtok);

    }
}
