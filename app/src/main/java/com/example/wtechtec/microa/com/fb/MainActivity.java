package com.example.wtechtec.microa.com.fb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wtechtec.microa.R;

/**
 * Created by Coder-pig on 2015/8/28 0028.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //UI Object
    private TextView txt_topbar;
    private TextView DL;
    private TextView DJ;
    private TextView DG;
    private TextView DN;
    private FrameLayout ly_content;
    private ImageView imageButtonback;
    public   String userid,xx;
    //Fragment Object

    private FragmentTransaction fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ddmain);
        ActionBar actionBar = MainActivity.this.getSupportActionBar();
        actionBar.hide();
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
        xx=intent.getStringExtra("xx");
        bindViews();
        imageButtonback= (ImageView) findViewById(R.id.acimgbtddback);
        imageButtonback.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        imageButtonback.setImageResource(R.drawable.backt);
                        break;
                    case  MotionEvent.ACTION_UP:
                        imageButtonback.setImageResource(R.drawable.back);
                        finish();
                        break;


                }
                return  true;
            }
        });
        DL.performClick();   //模拟一次点击，既进去后选择第一项
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        /*txt_topbar = (TextView) findViewById(R.id.txt_topbar);*/
        DL = (TextView) findViewById(R.id.DL);
        DJ = (TextView) findViewById(R.id.DJ);
        DG = (TextView) findViewById(R.id.DG);
        DN = (TextView) findViewById(R.id.DN);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        setfManager(new DLActivity());

        DL.setOnClickListener(this);
        DJ.setOnClickListener(this);
        DG.setOnClickListener(this);
        DN.setOnClickListener(this);

        DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();
                hideAllFragment(fManager);
                setfManager(new DLActivity());
                DL.setSelected(true);
            }
        });

        DJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();
                hideAllFragment(fManager);
                setfManager(new DJActivity());
                DJ.setSelected(true);
            }
        });

        DG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();
                hideAllFragment(fManager);
                setfManager(new DGActivity());
                DG.setSelected(true);
            }
        });

        DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected();
                hideAllFragment(fManager);
                setfManager(new DNActivity());
                DN.setSelected(true);
            }
        });
    }


    //重置所有文本的选中状态
    private void setSelected(){
        DL.setSelected(false);
        DJ.setSelected(false);
        DG.setSelected(false);
        DN.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){

    }


    @Override
    public void onClick(View v) {

    }

    public void setfManager(Fragment fragment){
        fManager = getFragmentManager().beginTransaction();
        fManager.replace(R.id.ly_content, fragment);
        fManager.commit();
    }
}

