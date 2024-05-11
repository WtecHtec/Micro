package com.example.wtechtec.microa.com.fb;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.data.DLkdData;
import com.example.wtechtec.microa.com.data.DNbsData;

import java.util.Calendar;

/**
 * Created by Jayce on 2018/1/5.
 */

public class DNActivity extends Fragment {
    private Spinner spinner;
    private EditText editTextdz,editTextphone,editTexttime,editTextbzly,editTextxf;
    private Button button;
    private  String  qhsj, xdsj ,userid,xx;
    private Calendar cal;
    private int year,month,day;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dn_layout, container, false);
InitView(view);
        getDate();

        DoThing();
        return view;
    }
    private void DoThing() {

        editTexttime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        editTexttime.setBackgroundColor(Color.RED);
                        break;
                    case  MotionEvent.ACTION_UP:
                        DatePickerDialog dialog=new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                qhsj=year+"-"+(month+1)+"-"+dayOfMonth;
                                editTexttime.setBackgroundColor(Color.WHITE);
                                editTexttime.setText(qhsj);
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
                           final String dnsw = (String) spinner.getSelectedItem();
                        final String dz=editTextdz.getText().toString();
                        final String phone=editTextphone.getText().toString();


                        final String shsj=  editTexttime.getText().toString();
                        final String bzly=editTextbzly.getText().toString();
                        final String xf=editTextxf.getText().toString();

                        int hour=cal.get(Calendar.HOUR_OF_DAY);
                        int mm=cal.get(Calendar.MINUTE);
                        int m=cal.get(Calendar.SECOND);
                        xdsj=year+"-"+(month+1)+"-"+day+"-"+hour+"-"+mm+"-"+m;
                        if(dz.equals("")||phone.equals("")||shsj.equals("")||bzly.equals("")||xf.equals("")||xdsj.equals("")){
                            Toast.makeText(getActivity(),"信息不完整",Toast.LENGTH_LONG).show();

                        }else {

                            new Thread(){
                                @Override
                                public void run() {

                             new DNbsData().AddData(userid,dnsw,dz,phone,shsj,bzly,xf,"12",xdsj,xx);
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


    private void InitView(View view) {

        MainActivity m= (MainActivity) getActivity();
        userid=m.userid;
        xx=m.xx;

       spinner= (Spinner) view.findViewById(R.id.dnspinerdnsw);
        editTextdz= (EditText) view.findViewById(R.id.dnetdz);
        editTextphone= (EditText) view.findViewById(R.id.dnetphone);
        editTexttime= (EditText) view.findViewById(R.id.dnettime);
        editTextbzly= (EditText) view.findViewById(R.id.dnetbzly);
        editTextxf= (EditText) view.findViewById(R.id.dnetxf);
        button= (Button) view.findViewById(R.id.dnbtok);

    }
}