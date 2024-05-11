package com.example.wtechtec.microa.com.dd;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wtechtec.microa.R;
import com.example.wtechtec.microa.com.second.Meun;
import com.example.wtechtec.microa.com.second.MyFragment;


/**
 * Created by Coder-pig on 2015/8/28 0028.
 */
public class MainActivity extends android.support.v4.app.Fragment implements View.OnClickListener{

    //UI Object
    private TextView txt_topbar;
    private TextView DD;
    private TextView YJ;
    private FrameLayout ly_content;

    private Button dl2;
    private Button dj2;
    private Button dg2;
    private Button dn2;

    private ImageView fb;
private  View view;
public  String xx;
    private android.support.v4.app.FragmentTransaction fManager;
  public  String userid;
   private  DDListActivity fg1;
    private  YJListActivity fg2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_maindd, container, false);

        Meun meun= (Meun) getActivity();
   xx=meun.userIfo.getXx();
        userid=meun.userIfo.getUserid();
        bindViews();
        DD.performClick();   //模拟一次点击，既进去后选择第一项

        fb = (ImageView) view.findViewById(R.id.fb);
        fb.setOnClickListener(this);
        setfManager(new DDListActivity());

        fb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        fb.setBackgroundResource(R.drawable.fb102);
                        break;
                    case MotionEvent.ACTION_UP:
                        fb.setBackgroundResource(R.drawable.fb101);
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
        return view;
    }

    //UI组件初始化与事件绑定
    private void bindViews() {

        DD = (TextView) view.findViewById(R.id.DD);
        YJ = (TextView)view. findViewById(R.id.YJ);
        ly_content = (FrameLayout)view. findViewById(R.id.ly_content);

        setfManager(new DDListActivity());

        DD.setOnClickListener(this);
        YJ.setOnClickListener(this);

        DD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();

                setfManager(new DDListActivity());
                DD.setSelected(true);
            }
        });

        YJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();


                setfManager(new YJListActivity());
                YJ.setSelected(true);
            }
        });
    }

    //重置所有文本的选中状态
    private void setSelected(){
        DD.setSelected(false);
        YJ.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(android.support.v4.app.FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
    }

    @Override
    public void onClick(View v) {

    }

    public void setfManager(android.support.v4.app.Fragment fragment){
        fManager = getFragmentManager().beginTransaction();
        fManager.replace(R.id.ly_contentdd,fragment);
        fManager.commit();
    }
}

