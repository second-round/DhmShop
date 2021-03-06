package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.MainVpFgAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.fragment.wode.MyorderFragment;

public class MyorderActivity extends BaseActiity {

    @BindView(R.id.main_tablayout)
    TabLayout mainTablayout;
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.title)
    TextView title;

    String type;

    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private MyorderFragment myorderFragment;
    private MyorderFragment myorderFragment1;
    private MyorderFragment myorderFragment2;
    private MyorderFragment myorderFragment3;
    private MyorderFragment myorderFragment4;
    private MainVpFgAdapter mainVpFgAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.main));
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待发货");
        titles.add("待收货");
        titles.add("待评价");
        //四个fragments
        fragments = new ArrayList<>();
        myorderFragment = new MyorderFragment("0");
        myorderFragment1 = new MyorderFragment("1");
        myorderFragment2 = new MyorderFragment("2");
        myorderFragment3 = new MyorderFragment("3");
        myorderFragment4 = new MyorderFragment("4");
        fragments.add(myorderFragment);
        fragments.add(myorderFragment1);
        fragments.add(myorderFragment2);
        fragments.add(myorderFragment3);
        fragments.add(myorderFragment4);
        //设置适配器
        mainVpFgAdapter = new MainVpFgAdapter(getSupportFragmentManager(), fragments, titles);
        changeTextColor(mainTablayout);
        //绑定适配器
        mainVp.setAdapter(mainVpFgAdapter);
        mainVp.setOffscreenPageLimit(0);
        //设置联动
        mainTablayout.setSelectedTabIndicatorHeight(0);
        mainTablayout.setupWithViewPager(mainVp);
        setupTabIcons();//设置底部TabLayout的item
        mainTablayout.getTabAt(Integer.parseInt(this.type)).select();
        title.setText(titles.get(Integer.parseInt(this.type)));
    }
    private int position=0;
    @Override
    protected void initData() {
        mainTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                title.setText(titles.get(position));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private void changeTextColor(TabLayout tabLayout){
        try {
            //拿到tabLayout的mTabStrip属性
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            //拿mTabStrip属性里面的值
            Object mTabStrip = field.get(tabLayout);
            //通过mTabStrip对象来获取class类，不能用field来获取class类，参数不能写Integer.class
            Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(mTabStrip, Color.parseColor("#ffffff"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //有几个底部的item就写几个
    private void setupTabIcons() {
        //tablayout图文效果
        for (int i = 0; i < fragments.size(); i++) {
            mainTablayout.getTabAt(i).setCustomView(getView(i));
        }
    }

    @SuppressLint("NewApi")
    private View getView(int position) {
        View tabitem = LayoutInflater.from(this).inflate(R.layout.tabshop, null);
        TextView b =  tabitem.findViewById(R.id.b);
        TextView tv =  tabitem.findViewById(R.id.tabtv);
        tv.setText(titles.get(position));
        b.setBackgroundResource(R.drawable.selector_shop);
        return tabitem;


    }



}
