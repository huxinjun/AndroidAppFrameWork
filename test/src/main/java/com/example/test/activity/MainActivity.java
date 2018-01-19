package com.example.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.SmartActivity;
import com.app.ULog;
import com.app.annotation.action.OnClick;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindView;
import com.app.annotation.fragment.Fragment;
import com.app.annotation.request.DatasDeclareClass;
import com.app.annotation.storage.StorageRoot;
import com.app.presenter.IDataPresenterBridge;
import com.app.presenter.IFragmentPresenter;
import com.app.presenter.IFragmentPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;
import com.example.test.R;
import com.example.test.fragment.Fragment_1;
import com.example.test.fragment.Fragment_2;
import com.example.test.global.Datas;

@DatasDeclareClass(Datas.class)
@BindLayoutCreater(creater=MainActivity.MainActivityCreater.class)
@StorageRoot("xinjun")
public class MainActivity extends SmartActivity {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterManager.getInstance().findPresenter(getContext(), IDataPresenterBridge.class).request(Datas.data_account_list,null,null);
        PresenterManager.getInstance().findPresenter(getContext(), IDataPresenterBridge.class).request(Datas.data_room_list,null,null);
    }

    @BindView(R.layout.activity_main)
    public static class MainActivityCreater extends LayoutCreater {

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

        @Override
        public void onCreated() {
            super.onCreated();

        }

        public void btn1click() {
            Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
            PresenterManager.getInstance().findPresenter(getContext(), IFragmentPresenterBridge.class).changeFragment(new IFragmentPresenter.FragmentInfo(R.id.fl_container,Fragment_1.class));
        }

        @OnClick(viewId = R.id.btn_2)
        public void btn2click() {
            Toast.makeText(getContext(),"2",Toast.LENGTH_SHORT).show();
            PresenterManager.getInstance().findPresenter(getContext(), IFragmentPresenterBridge.class).changeFragment(new IFragmentPresenter.FragmentInfo(R.id.fl_container,Fragment_2.class));
        }


    }
}
