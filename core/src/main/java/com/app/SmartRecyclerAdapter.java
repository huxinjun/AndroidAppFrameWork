package com.app;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.app.annotation.creater.BindView;
import com.app.presenter.ILayoutPresenter;
import com.app.presenter.ILayoutPresenterBridge;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.layout.LayoutCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 精选界面的卡片推荐房间适配器
 * Created by xinjun on 2018/4/17.
 */

public class SmartRecyclerAdapter extends RecyclerView.Adapter<SmartRecyclerAdapter.ViewHolder> {

    public static final int ITEM_VIEW_TYPE_HEADER=0;
    public static final int ITEM_VIEW_TYPE_ITEM=1;
    public static final int ITEM_VIEW_TYPE_FOOTER=2;

    /**
     * 这个Adapter被附加的适配器视图
     */
    private RecyclerView mRecyclerView;

    /**
     * 自定义自条目创建器
     */
    private ItemDefiner mItemDefiner;

    /**
     * 所有的数据
     */
    private List<Object> mAllDatas;

    /**
     * context
     */
    private Context mContext;

    /**
     * 布局id对应的LayoutCreater的class
     */
    private HashMap<Integer, Class<? extends LayoutCreater>> viewTypes = new HashMap<>();


    /**
     * header集合
     */
    private List<Class<? extends LayoutCreater>> mHeaderCreaters = new ArrayList<>();
    /**
     * footer集合
     */
    private List<Class<? extends LayoutCreater>> mFooterCreaters = new ArrayList<>();


    public SmartRecyclerAdapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        mRecyclerView = recyclerView;
        if (mRecyclerView != null) {
            mAllDatas = (List<Object>) recyclerView.getTag(LayoutCreater.TAG_ITEMS_DATA);
            Class<? extends ItemDefiner> mItemDefinerClazz = (Class<? extends ItemDefiner>) recyclerView.getTag(LayoutCreater.TAG_DEFINER_ITEM_CLASS);
            try {
                mItemDefiner = mItemDefinerClazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            mItemDefiner.defineHeader(mHeaderCreaters);
            mItemDefiner.defineFooter(mFooterCreaters);

            initLayoutManager();
        }
    }

    /**
     * 处理gridview的header，footer不是占一整行的问题
     */
    private void initLayoutManager() {
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if(layoutManager!=null){
            if(layoutManager instanceof GridLayoutManager){
                //处理gridview的header，footer不是占一整行的问题
                GridLayoutManager gridLayoutManager= (GridLayoutManager) layoutManager;
                int spanCount = gridLayoutManager.getSpanCount();
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if(position<mHeaderCreaters.size() || position>=mHeaderCreaters.size()+mAllDatas.size())
                            return spanCount;
                        else
                            return 1;
                    }
                });
            }
        }
    }

    //临时使用
    LayoutCreater tempCreater;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据类声明中的注解创建相应的LayoutCreater
        Class<? extends LayoutCreater> createrClass = viewTypes.get(viewType);
        try {
            PresenterManager.getInstance().findPresenter(mContext, ILayoutPresenterBridge.class).inflate(createrClass, new ILayoutPresenter.InflateCallBack() {
                @Override
                public void onCompleted(LayoutCreater instance) {
                    tempCreater = instance;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ViewHolder(tempCreater);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(isHeader(position)){
            Object tag = mRecyclerView.getTag(LayoutCreater.TAG_RECYCLERVIEW_HEADER_DATA);
            if(tag!=null){
                List datas= (List) tag;
                holder.creater.setContentData(datas.get(position));
            }
        }
        else if(isFooter(position)){
            Object tag = mRecyclerView.getTag(LayoutCreater.TAG_RECYCLERVIEW_FOOTER_DATA);
            if(tag!=null){
                List datas= (List) tag;
                holder.creater.setContentData(datas.get(position-(mHeaderCreaters.size()+mAllDatas.size())));
            }
        }
        else
            holder.creater.setContentData(mAllDatas.get(position-mHeaderCreaters.size()));
    }

    @Override
    public int getItemCount() {
        return mAllDatas == null ? mHeaderCreaters.size() + mFooterCreaters.size() : mAllDatas.size() + mHeaderCreaters.size() + mFooterCreaters.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class<? extends LayoutCreater> createrClass=null;
        if(isHeader(position))
            createrClass = mHeaderCreaters.get(position);
        else if(isFooter(position))
            createrClass = mFooterCreaters.get(position-(mHeaderCreaters.size()+mAllDatas.size()));
        else
            createrClass = mItemDefiner.defineItem(mAllDatas, position);
        BindView annotation = createrClass.getAnnotation(BindView.class);
        if (annotation == null)
            throw new RuntimeException("请在LayoutCreater[" + createrClass.getName() + "]类上添加BindView用来指定布局");
        viewTypes.put(annotation.value(), createrClass);
        return annotation.value();
    }


    /**
     * 判断某个item是否是header
     */
    public boolean isHeader(int position) {
        return position < mHeaderCreaters.size();
    }

    /**
     * 判断某个item是否是footer
     */
    public boolean isFooter(int position) {
        return position >= getItemCount()- mFooterCreaters.size();
    }


    /**
     * VIEWHODER 其功能被LayoutCreater替代
     * Created by xinjun on 2018/4/19 19:37
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private LayoutCreater creater;

        public ViewHolder(LayoutCreater creater) {
            super(creater.getContentView());
            this.creater = creater;
        }
    }

    /**
     * 条目定义接口
     * Created by xinjun on 2018/4/19 16:41
     */
    public interface ItemDefiner {
        void defineHeader(List<Class<? extends LayoutCreater>> headers);

        void defineFooter(List<Class<? extends LayoutCreater>> footers);

        Class<? extends LayoutCreater> defineItem(List<Object> allData, int position);
    }

    /**
     * 条目定义类
     * 默认实现了header和footer的定义
     * Created by xinjun on 2018/4/19 16:41
     */
    public static abstract class SimpleItemDefiner implements ItemDefiner {
        @Override
        public void defineHeader(List<Class<? extends LayoutCreater>> headers) {
        }

        @Override
        public void defineFooter(List<Class<? extends LayoutCreater>> footers) {
        }
    }
}
