package com.mingho.simplexcel;

import android.os.Bundle;


import com.mingho.simple_excel.adapter.OneDataAdapter;
import com.mingho.simple_excel.widget.SimpleExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleExcel se;
    private OneDataAdapter adapter;
    private List<List<String>> data = new ArrayList<>();
    private List<String> leftTitle = new ArrayList<>();
    List<String> header = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLeftTitle();
        initView();
        setHeader();
        setData();
    }

    private void setData() {
        List<String> sexList = Arrays.asList("男", "女", "男", "男", "女", "女", "女");
        List<String> ageList = Arrays.asList("23", "21", "28", "31", "19", "18", "22");
        List<String> heightList = Arrays.asList("166", "154", "178", "182", "170", "165", "166");
        List<String> weightList = Arrays.asList("50", "40", "70", "75", "60", "55", "55");


        for (int i = 0; i < sexList.size(); i++) {
            List<String> oneData = new ArrayList<>();
            oneData.add(sexList.get(i));
            oneData.add(ageList.get(i));
            oneData.add(heightList.get(i));
            oneData.add(weightList.get(i));
            data.add(oneData);
        }
        adapter.notifyDataSetChanged();
    }

    private void setHeader() {
        header.add("性别");
        header.add("年龄");
        header.add("身高");
        header.add("体重");
        se.setHeaderColor("#ffeeff");
        se.setHeader(header,240,150);
    }

    private void setLeftTitle() {
        leftTitle= Arrays.asList("张三", "李四", "王五", "赵六", "王芬", "李红", "杜娟");
    }

    private void initView() {
        se = (SimpleExcel) findViewById(R.id.se);
        adapter=new OneDataAdapter(this);
        adapter.setLeftTitle(leftTitle);
        adapter.setForegroundColor("#efefef");
        adapter.setObjects(data);
        adapter.setWHGlobal(240,150);
        se.setAdapter(adapter);
    }
}
