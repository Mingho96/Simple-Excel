# Simple-Excel
安卓仿excel表格，支持横向滑动


#准备：
##第一步
```
allprojects {
    repositories {
        google()
        jcenter()
        //这里添加
        maven { url'https://jitpack.io'}
    }
}
```

##第二步
```      
	 implementation 'com.github.Mingho96:Simple-Excel:v1.0.0'

```

#使用
##布局
```
 <com.mingho.simple_excel.widget.SimpleExcel
       android:id="@+id/se"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"/>
```
##方法

SimpleExcel类

setHeaderColor(String headerColor);//设置首行背景颜色

setHeader(List<String> header, int width, int height);//设置首行内容，width:单元格宽度，height:高度

setAdapter(OneDataAdapter adapter);//设置适配器

OneDataAdapter类

setObjects(List<List<String>> objects);//设置数据

setLeftTitle(List<String> leftTitle);//设置行标题(首列数据)

setWHGlobal(int width,int  height);//统一设置设置列宽和行高(首列除外)

setWidthEachColumn(List<Integer> widthList);//为每一列设置列宽 （首列除外）

setForegroundColor(String foregroundColor);//统一设置单元格前景色

setForegroundColorList(List<List<String>> foregroundColorList);//设置每一个单元格的前景色

测试用例：
```
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
```
