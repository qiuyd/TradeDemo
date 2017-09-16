package com.example.qiuyd.tradedemo;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;
import com.open.androidtvwidget.view.RelativeMainLayout;
import com.open.androidtvwidget.view.ViewPagerTV;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MainUpView mainUpView;
    private LinearLayout mainlay,dxds,jjc,ksth;
    private RelativeMainLayout imagelist;
    private ImageView haibao,image1,image2,image3,image4,image5,setting;
    private TextView textview;
    private boolean isLooper;
    private int index;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainUpView = (MainUpView)findViewById(R.id.mainUpView1);
        mainlay = (LinearLayout)findViewById(R.id.main_lay);
        imagelist = (RelativeMainLayout)findViewById(R.id.imagelist);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        textview = (TextView)findViewById(R.id.textview);

        textview.setSelected(true);


        //开启一个线程，用于循环,海报循环
        new Thread(new Runnable() {
            @Override
            public void run() {
                isLooper = true;
                index = 2;
                while (isLooper){
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(index%2==0){
                                image1.setImageResource(R.drawable.image1_2);
                                index++;
                            }else{
                                image1.setImageResource(R.drawable.image1_1);
                                index++;
                            }
                        }
                    });
                }
            }
        }).start();



        mainUpView.setEffectBridge(new EffectNoDrawBridge());
        mainUpView.setUpRectResource(R.drawable.bg_focus_select);
        if(util.is1080p(MainActivity.this)){
            mainUpView.setDrawUpRectPadding(new Rect(27,27,30,30));
        }else if(util.is720p(MainActivity.this)){
            mainUpView.setDrawUpRectPadding(new Rect(19,19,21,21));
        }



        mainlay.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if(newFocus != null){
                    newFocus.bringToFront();
                }




                float scale = 1.05f;
                mainUpView.setFocusView(newFocus,oldFocus,scale);
            }
        });


        for(int i=0;i<imagelist.getChildCount();i++){
            imagelist.getChildAt(i).setOnClickListener(this);
        }

        if(util.is720p(MainActivity.this)){
            Toast.makeText(MainActivity.this,"720P",Toast.LENGTH_SHORT).show();
        }else if(util.is1080p(MainActivity.this)){
            Toast.makeText(MainActivity.this,"1080P",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image1:
                Toast.makeText(MainActivity.this,"这只是一个demo而已，点击了image1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image2:
            //    Toast.makeText(MainActivity.this,"这只是一个demo而已，点击了image2",Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this,WebviewActivity.class);
                intent.putExtra("url","file:///android_asset/html/LDJ.html");
                startActivity(intent);
                break;
            case R.id.image3:
            //    Toast.makeText(MainActivity.this,"这只是一个demo而已，点击了image3",Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this,WebviewActivity.class);
                intent.putExtra("url","file:///android_asset/html/vedio_show.html?id=img4");
                startActivity(intent);
                break;
            case R.id.image4:
            //    Toast.makeText(MainActivity.this,"这只是一个demo而已，点击了image4",Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this,WebviewActivity.class);
                intent.putExtra("url","http://www.baidu.com/");
                startActivity(intent);
                break;
            case R.id.image5:
                Toast.makeText(MainActivity.this,"这只是一个demo而已，点击了image5",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isDestroyed() {
        isLooper = false;
        return super.isDestroyed();
        
    }
}
