package com.example.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.SmartActivity;
import com.app.annotation.action.OnClick;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.annotation.fragment.Fragment;
import com.app.presenter.impl.layout.LayoutCreater;
import com.example.test.R;
import com.example.test.fragment.Fragment_1;
import com.example.test.utils.ULog;

@BindLayoutCreater(creater=MainActivity.MainActivityCreater.class)
public class MainActivity extends SmartActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ULog.out("onCreateonCreateonCreateonCreateonCreateonCreateonCreateonCreateonCreateonCreateonCreate");
    }

    @BindView(R.layout.activity_main)
    public class MainActivityCreater extends LayoutCreater {

        @Fragment(clazz=Fragment_1.class)
        @BindView(R.id.fl_container)
        public FrameLayout fl_container;

        @BindView(R.id.btn_1)
        @OnClick("btn1click")
        public Button btn_1;

        @BindView(R.id.btn_2)
        public Button btn_2;

        @Override
        public void onDataPrepared() {
        }


        public void btn1click() {
            Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT);
        }

        @OnClick(viewId = R.id.btn_1)
        public void btn2click() {
            Toast.makeText(getContext(),"2",Toast.LENGTH_SHORT);
        }


    }
}
