package com.mingho.simple_excel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mingho.simple_excel.R;

import java.util.ArrayList;
import java.util.List;

public class OneDataAdapter extends BaseAdapter {

    private List<List<String>> objects = new ArrayList<>();
    private List<String> leftTitle=new ArrayList<>();
    private ItemScrollListener listener;
    private Context context;
    private LayoutInflater layoutInflater;
    public List<ViewHolder> viewHolders=new ArrayList<>();
    private int height=50;
    private int width=200;
    private List<List<String>> foregroundColorList=new ArrayList<>();
    private String foregroundColor="#ffffff";
    private List<Integer> widthList=new ArrayList<>();
    private String leftTitleColor="#efefef";

    public OneDataAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据
     * @param objects
     */
    public void setObjects(List<List<String>> objects) {
        this.objects = objects;
    }

    /**
     * 设置行标题(首列内容)
     * @param leftTitle
     */
    public void setLeftTitle(List<String> leftTitle) {
        this.leftTitle = leftTitle;
    }

    /**
     *  统一设置列宽和行高（首列除外）
     * @param width
     * @param height
     */
    public void setWHGlobal(int width,int  height){
        this.height=height;
        this.width=width;
    }

    /**
     * 为每一列设置列宽 （首列除外）
     * @param widthList
     */
    public void setWidthEachColumn(List<Integer> widthList){
        this.widthList=widthList;
    }


    /**
     * 统一设置单元格前景色
     * @param foregroundColor
     */
    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    /**
     * 设置每一个单元格的前景色
     * @param foregroundColorList
     */
    public void setForegroundColorList(List<List<String>> foregroundColorList) {
        this.foregroundColorList = foregroundColorList;
    }

    public void setListener(ItemScrollListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public List<String> getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_one_data, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position),position, (ViewHolder) convertView.getTag());
        return convertView;
    }

    @SuppressLint("NewApi")
    private void initializeViews(List<String> oneDataValues,int pos, ViewHolder holder) {
        //TODO implement
        holder.llOne.removeAllViews();
        holder.tvLeft.setText(leftTitle.get(pos));
        holder.tvLeft.setWidth(width);
        holder.tvLeft.setHeight(height);
        holder.tvLeft.setBackgroundColor(Color.parseColor("#efefef"));
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(1,1,1,1);
        holder.tvLeft.setLayoutParams(layoutParams);
        holder.tvLeft.setGravity(Gravity.CENTER);
        viewHolders.add(holder);
        for (int i=0;i<oneDataValues.size();i++){
            TextView tvValue=new TextView(context);
            Log.i("widthList.size()----->", String.valueOf(widthList.size()));
            if (widthList.size()>0){
                tvValue.setWidth(widthList.get(i));
            }else {
                tvValue.setWidth(width);
            }
            tvValue.setHeight(height);

            if (foregroundColorList.size()>0){
                tvValue.setBackgroundColor(Color.parseColor(foregroundColorList.get(pos).get(i)));
            }else {
                tvValue.setBackgroundColor(Color.parseColor(foregroundColor));
            }
            tvValue.setLayoutParams(layoutParams);
            tvValue.setGravity(Gravity.CENTER);
            tvValue.setText(oneDataValues.get(i));
            holder.llOne.addView(tvValue);
        }

        holder.hScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
               if (listener!=null){
                   listener.onItemScroll(scrollX,scrollY,viewHolders);
               }
            }
        });

    }

    public class ViewHolder {
        public HorizontalScrollView hScroll;
      public LinearLayout llOne;
      public TextView tvLeft;

        public ViewHolder(View view) {
            hScroll = (HorizontalScrollView) view.findViewById(R.id.h_scroll);
            llOne = (LinearLayout) view.findViewById(R.id.ll_one);
            tvLeft = (TextView) view.findViewById(R.id.left_title);
        }
    }

    public interface ItemScrollListener{
       void onItemScroll(int scrollX, int scrollY, List<ViewHolder> viewHolders);
    }
}
