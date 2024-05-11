package com.example.wtechtec.microa.com.fb;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DLkdData;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jayce on 2018/1/5.
 */

public class DLActivity extends Fragment {
    private Calendar cal;
    private int year,month,day;
    private EditText editTextqhdz,editTextqhh,editTextshdz,editTextshhm,editTextshsj,editTextbzly,editTextxf;
    private Button button;
    private  String  qhsj, xdsj ,userid,xx;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dl_layout, container, false);
 getDate();
InitView(view);
        DoThing();
        return view;
    }

    private void DoThing() {

        editTextshsj.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        editTextshsj.setBackgroundColor(Color.RED);
                        break;
                    case  MotionEvent.ACTION_UP:
                        DatePickerDialog dialog=new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                qhsj=year+"-"+(month+1)+"-"+dayOfMonth;
                                editTextshsj.setBackgroundColor(Color.WHITE);
                                editTextshsj.setText(qhsj);
                            }
                        }, year, month, day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();

                        break;

                }

                return true;
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){

                    case  MotionEvent.ACTION_DOWN:
                        button.setBackgroundColor(Color.RED);
                        break;
                    case MotionEvent.ACTION_UP:
                        button.setBackgroundColor(Color.parseColor("#FF7F00"));

                        final String qhdz=editTextqhdz.getText().toString();
                        final String qhh=editTextqhh.getText().toString();
                        final String shdz=editTextshdz.getText().toString();
                        final String shhm=editTextshhm.getText().toString();
                        final String shsj=editTextshsj.getText().toString();
                        final String bzly=editTextbzly.getText().toString();
                        final String xf=editTextxf.getText().toString();

                        int hour=cal.get(Calendar.HOUR_OF_DAY);
                       int mm=cal.get(Calendar.MINUTE);
                        int m=cal.get(Calendar.SECOND);
                        xdsj=year+"-"+(month+1)+"-"+day+"-"+hour+"-"+mm+"-"+m;
                        if(qhdz.equals("")||qhh.equals("")||shdz.equals("")||shhm.equals("")||shsj.equals("")||bzly.equals("")||xf.equals("")||xdsj.equals("")){
                            Toast.makeText(getActivity(),"信息不完整",Toast.LENGTH_LONG).show();

                        }else {

                            new Thread(){
                                @Override
                                public void run() {
                                    new DLkdData().AddData(userid,qhdz,qhh,shdz,shhm,shsj,bzly,xf,xdsj,xx);

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

    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒

        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void InitView(View view) {
        MainActivity m= (MainActivity) getActivity();
  userid=m.userid;
        xx=m.xx;



        editTextqhdz= (EditText) view.findViewById(R.id.dletqhdz);
        editTextqhh= (EditText) view.findViewById(R.id.dletqhh);
        editTextshdz= (EditText) view.findViewById(R.id.dletshdz);
        editTextshhm= (EditText) view.findViewById(R.id.dletshhm);
        editTextshsj= (EditText) view.findViewById(R.id.dletshsj);
        editTextbzly= (EditText) view.findViewById(R.id.dletbzly);
        editTextxf= (EditText) view.findViewById(R.id.dletxf);
        button= (Button) view.findViewById(R.id.dlbtok);


    }
}
