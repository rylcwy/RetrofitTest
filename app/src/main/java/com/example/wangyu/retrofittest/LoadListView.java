package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class LoadListView extends ListView implements AbsListView.OnScrollListener {
    private View footer;
    int totalItemCount;
    int lastVisibieItem;
    boolean isLoading;
    IloadListener iloadListener;


    public LoadListView (Context context, AttributeSet attrs){
        super(context,attrs);
        initView(context);
    }

    public LoadListView(Context context){
        super(context);
        initView(context);
    }

    public LoadListView(Context context,AttributeSet attrs,int defStyle){
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        footer=inflater.inflate(R.layout.foot_view,null);
        footer.findViewById(R.id.footer_layout).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(totalItemCount==lastVisibieItem && scrollState==SCROLL_STATE_IDLE){
            if(!isLoading){
                isLoading=true;
                footer.findViewById(R.id.footer_layout).setVisibility(View.VISIBLE);
                iloadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibieItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void setInterface(IloadListener iLoadListener) {

        this.iloadListener = iLoadListener;
    }

    public interface IloadListener {
        public void onLoad();
    }

    public void loadComplete() {
        isLoading = false;
        footer.findViewById(R.id.footer_layout).setVisibility(View.GONE);

    }

}
