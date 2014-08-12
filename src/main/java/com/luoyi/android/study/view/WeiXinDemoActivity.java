/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;
import com.luoyi.android.study.R;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author 作者
 * @since 1.0
 * @version 2014-8-5 作者
 */
public class WeiXinDemoActivity extends FragmentActivity {
    
    /**
     * PagerSlidingTabStrip的实例
     */
    private PagerSlidingTabStrip tabs;
    
    private MyAppFragment myAppFragment;
    
    private NewAppFragment newAppFragment;
    
    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;
    
    /**
     * @param savedInstanceState
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_weixin);
        dm = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(pager);
        setTabsValue();
    }
    
    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(getResources().getColor(R.color.bule));
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(getResources().getColor(R.color.bule));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        // tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
        // 取消点击Tab时的背景色
        // tabs.setTabBackground(stateListDrawable);
    }
    
    public class MyPagerAdapter extends FragmentPagerAdapter {
        
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        
        private final String[] titles = { "我的应用", "应用商店" };
        
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
        
        @Override
        public int getCount() {
            return titles.length;
        }
        
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (myAppFragment == null) {
                        myAppFragment = new MyAppFragment();
                    }
                    return myAppFragment;
                case 1:
                    if (newAppFragment == null) {
                        newAppFragment = new NewAppFragment();
                    }
                    return newAppFragment;
                default:
                    return null;
            }
        }
        
    }
    
    public void invokeRightFragment(String str) {
        newAppFragment.invokeByFragmentOne(str);
    }
}
