package com.app.presenter.impl.injector;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.app.SmartAbsListAdapter;
import com.app.presenter.IInjectionPresenter;
import com.app.presenter.impl.layout.LayoutCreater;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ViewInjector implements IInjectionPresenter {

    private WeakReference<Context> mContext;

    @Override
    public void setContext(Context context) {
        mContext = new WeakReference<Context>(context);
    }

    @Override
    public Context getContext() {
        return mContext.get();
    }

    @Override
    public void inject(View target, Object value) {
        //配置了@Injector注解后view的tag下会记录其使用的注入器类型
        Class<IInjectionPresenter> injectorClass = (Class<IInjectionPresenter>) target.getTag(LayoutCreater.TAG_INJECTOR_CLASS);

        IInjectionPresenter injector = null;
        try {
            //木有配置Injector注解
            if (injectorClass == null)
                //使用默认的数据注入器
                injector = DEFAULT_INJECTOR.get(target.getClass()).newInstance();
            else
                injector = injectorClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        injector.inject(target,value);
    }

}
