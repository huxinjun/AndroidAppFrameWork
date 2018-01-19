package com.app.presenter.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.app.ULog;
import com.app.presenter.IImagePresenter;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2018/1/19.
 */

public class GlideImagePresenter implements IImagePresenter {

    private Context mContext;
    private Option option;
    private long maxMemorySize;
    @Override
    public void setContext(Context context) {
        mContext=context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void setMemorySize(long size) {
        this.maxMemorySize=size;
    }

    @Override
    public void setGlobleOption(Option option) {
        this.option=option;
    }

    @Override
    public Option getGlobleOption() {
        return option;
    }

    @Override
    public Bitmap getImage(String url, OnImageDownloadOkListener listener) {
        return null;
    }

    @Override
    public void setImage(ImageView target, String url) {
//        ULog.out("setImage:"+url+",mContext:"+mContext.getClass().getName());
        Picasso.with(getContext()).load(url).into(target);
    }

    @Override
    public Bitmap compressImage(String imageUrl, Rect rect) {
        return null;
    }
}
