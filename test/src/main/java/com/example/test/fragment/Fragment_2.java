package com.example.test.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.SmartFragment;
import com.app.SmartRecyclerAdapter;
import com.app.ULog;
import com.app.annotation.BindFieldName;
import com.app.annotation.BindMultiData;
import com.app.annotation.creater.BindItemDefiner;
import com.app.annotation.creater.BindLayoutCreater;
import com.app.annotation.creater.BindLayoutCreaterHeader;
import com.app.annotation.creater.BindLayoutCreaters;
import com.app.annotation.creater.BindView;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.IRequestPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;
import com.example.test.R;
import com.example.test.global.Urls;
import com.example.test.model.Accounts;
import com.example.test.model.Rooms;

import java.util.List;

@BindLayoutCreater(creater = Fragment_2.MyCreater.class)
public class Fragment_2 extends SmartFragment {


    @BindView(R.layout.fragment_2)
    public static class MyCreater extends LayoutCreater {


        @BindLayoutCreaters({@BindLayoutCreater(creater = Vp1Creater.class),
                @BindLayoutCreater(creater = Vp2Creater.class),
                @BindLayoutCreater(creater = Vp3Creater.class),
                @BindLayoutCreater(creater = Vp4Creater.class)})
        @BindView(R.id.vp)
        public ViewPager viewPager;


        @Override
        public void onViewCreated() {
        }

        @Override
        public void onDataPrepared() {

        }
    }


    @BindView(R.layout.layout_vp1)
    public static class Vp1Creater extends LayoutCreater<Rooms> {


        @BindView(R.id.lv_content)
        @BindFieldName("result.rooms")
        @BindMultiData(2)
        @BindLayoutCreater(creater = LvCreater.class)
        @BindLayoutCreaterHeader(creater = LvHeaderCreater.class)
        public ListView lv_content;

        @Override
        public void onInitData() {
            PresenterManager.getInstance().findPresenter(getContext(), IRequestPresenterBridge.class).request(Urls.PATTERN_HOT_ROOM, null, new IRequestPresenter.DataCallBack() {
                @Override
                public void onDataComming(boolean isCache,Object object) {
                    setContentData((Rooms) object);
                }
            });
        }

        @Override
        public void onViewCreated() {

        }

        @Override
        public void onDataPrepared() {
            Rooms.Result.Room numOne = getContentData().getResult().getRooms().remove(0);
            LayoutCreater headerCreater = getHeaderCreater(lv_content);
            headerCreater.setContentData(numOne);
        }

        @BindView(R.layout.layout_header_img)
        public static class LvHeaderCreater extends LayoutCreater<Rooms.Result.Room> {


            @BindFieldName(value = "imgPathM")
            @BindView(R.id.iv_icon)
            public ImageView iv_icon;

            @Override
            public void onDataPrepared() {
            }
        }


        @BindView(R.layout.layout_room)
        public static class LvCreater extends LayoutCreater<Rooms.Result.Room> {


            @BindView(R.id.hot_item_image_01)
            @BindFieldName(value = "imgPathM", index = 0)
            public ImageView hot_item_image_01;

            @BindView(R.id.hot_item_image_02)
            @BindFieldName(value = "imgPathM", index = 1)
            public ImageView hot_item_image_02;

            @Override
            public void onDataPrepared() {
            }
        }

    }


    @BindView(R.layout.layout_vp2)
    public static class Vp2Creater extends LayoutCreater<Rooms> {

        @BindView(R.id.rcv)
        @BindFieldName("result.rooms")
        @BindItemDefiner(Vp2Definer.class)
        public RecyclerView rcv;

        @Override
        public void onViewCreated() {
            super.onViewCreated();
            rcv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        @Override
        public void onInitData() {
            PresenterManager.getInstance().findPresenter(getContext(), IRequestPresenterBridge.class).request(Urls.PATTERN_HOT_ROOM2, null, new IRequestPresenter.DataCallBack() {
                @Override
                public void onDataComming(boolean isCache,Object object) {
                    ULog.out("onDataComming：" + object);
                    setContentData((Rooms) object);
                }
            });
        }

        @Override
        public void onDataPrepared() {
            Rooms.Result.Room numOne = getContentData().getResult().getRooms().remove(0);
            setRecyclerViewHeaderData(rcv, 0, numOne);
            Rooms.Result.Room numTwo = getContentData().getResult().getRooms().remove(10);
            setRecyclerViewFooterData(rcv, 0, numTwo);
        }

        public static class Vp2Definer extends SmartRecyclerAdapter.SimpleItemDefiner {
            @Override
            public void defineHeader(List<Class<? extends LayoutCreater>> headers) {
                headers.add(RcvHeaderCreater.class);
            }

            @Override
            public void defineFooter(List<Class<? extends LayoutCreater>> footers) {
                footers.add(RcvHeaderCreater.class);
            }

            @Override
            public Class<? extends LayoutCreater> defineItem(List<Object> allData, int position) {
                return RcvItemCreater.class;
            }
        }

        @BindView(R.layout.layout_header_img)
        public static class RcvHeaderCreater extends LayoutCreater<Rooms.Result.Room> {
            @BindFieldName(value = "imgPathM")
            @BindView(R.id.iv_icon)
            public ImageView iv_icon;

            @Override
            public void onDataPrepared() {
            }
        }

        @BindView(R.layout.layout_room_single)
        public static class RcvItemCreater extends LayoutCreater<Rooms.Result.Room> {
            @BindView(R.id.hot_item_image_01)
            @BindFieldName(value = "imgPathM", index = 0)
            public ImageView hot_item_image_01;

            @Override
            public void onDataPrepared() {
            }
        }
    }


    @BindView(R.layout.layout_vp3)
    public static class Vp3Creater extends LayoutCreater<Accounts> {


        @BindView(R.id.tv_content)
        public TextView textView;

        @Override
        public void onDataPrepared() {
        }
    }


    @BindView(R.layout.layout_vp4)
    public static class Vp4Creater extends LayoutCreater<Accounts> {


        @BindView(R.id.tv_content)
        public TextView textView;

        @Override
        public void onDataPrepared() {
        }
    }


}
