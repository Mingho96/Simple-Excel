package com.mingho.simple_excel.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mingho.simple_excel.R;
import com.mingho.simple_excel.adapter.OneDataAdapter;

import java.util.List;

/**
 * Author by Mingho,  Date on 2020/6/6.
 * PS: Not easy to write code, please indicate.
 */
public class SimpleExcel extends LinearLayout {
    private ListView lvData;
    private TextView description;
    private LinearLayout llHeader;
    private HorizontalScrollView headerScroll;
    private Context context;
    private OneDataAdapter adapter;
    private LayoutInflater layoutInflater;
    private String headerColor="#efefef";
    public SimpleExcel(Context context) {
        super(context);
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        initView();
    }

    public SimpleExcel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        initView();
    }

    public SimpleExcel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        layoutInflater=LayoutInflater.from(context);
        this.context=context;
        initView();
    }

    /**
     * 设置表头背景颜色
     * @param headerColor
     */
    public void setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
    }

    @SuppressLint("NewApi")
    public void setHeader(List<String> header, int width, int height) {
        description.setWidth(width);
        description.setHeight(height);
        for (int i=0;i<header.size();i++){
            TextView tvValue=new TextView(context);
            tvValue.setWidth(width);
            tvValue.setHeight(height);
            tvValue.setGravity(Gravity.CENTER);
            tvValue.setText(header.get(i));
            tvValue.setBackgroundColor(Color.parseColor(headerColor));
            //通过设置一定的边距，前景色和背景色的差异化，实现网格线的显示
            LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(1,1,1,1);
            tvValue.setLayoutParams(layoutParams);
            llHeader.addView(tvValue);
        }
        headerScroll.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                for (int i=0;i<adapter.viewHolders.size();i++)
                {
                    try{
                        OneDataAdapter.ViewHolder viewHolder=adapter.viewHolders.get(i);
                        viewHolder.hScroll.scrollTo(scrollX,scrollY);
                    }catch (NullPointerException e){
                        Log.e("onScrollChange","viewHolder 为空，可能adapter未初始化！");
                    }
                }
            }
        });
    }

    public void setAdapter(OneDataAdapter adapter) {
        this.adapter=adapter;
        adapter.setListener(new OneDataAdapter.ItemScrollListener() {
            @Override
            public void onItemScroll(int scrollX, int scrollY, List<OneDataAdapter.ViewHolder> holders) {
                for (int i = 0; i < holders.size(); i++) {
                    HorizontalScrollView hScroll = holders.get(i).hScroll;
                    hScroll.scrollTo(scrollX, scrollY);
                }
                headerScroll.scrollTo(scrollX,scrollY);
            }
        });
        lvData.setAdapter(adapter);
    }

    /**
     * 设置表头文字
     * @param description
     */
    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void initView() {
        View rootView=layoutInflater.inflate(R.layout.excel_layout,null);
        lvData = (ListView) rootView.findViewById(R.id.lv_data);
        description = (TextView) rootView.findViewById(R.id.description);
        llHeader = (LinearLayout)rootView. findViewById(R.id.ll_header);
        headerScroll = (HorizontalScrollView) rootView.findViewById(R.id.header_scroll);
        this.addView(rootView);
    }
}
