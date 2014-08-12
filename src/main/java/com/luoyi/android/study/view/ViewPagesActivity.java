/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * @author zhaoqunqi
 * @since 1.0
 * @version 2014年8月11日 zhaoqunqi
 */
public class ViewPagesActivity extends Activity {
    
    private List<View> listView;
    
    private ViewPager vp_demo_viewpager;
    
    /**
     * @param savedInstanceState
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_viewpager);
        vp_demo_viewpager = (ViewPager) findViewById(R.id.vp_demo_viewpager);
        LayoutInflater linflater = this.getLayoutInflater();
        View view1 = linflater.inflate(R.layout.demo_viewpager_card1, null);
        View view2 = linflater.inflate(R.layout.demo_viewpager_card2, null);
        View view3 = linflater.inflate(R.layout.demo_viewpager_card3, null);
        listView = new ArrayList<View>(3);
        listView.add(view1);
        listView.add(view2);
        listView.add(view3);
        
        PagerAdapter pagerAdapter = new PagerAdapter() {
            
            /**
             * @param container
             * @param position
             * @param object
             * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.ViewGroup, int, java.lang.Object)
             */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(listView.get(position));// 删除页卡
            }
            
            /**
             * @param container
             * @param position
             * @return
             * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.ViewGroup, int)
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = listView.get(position);
                container.addView(view);
                return view;
            }
            
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO 自动生成方法存根注释，方法实现时请删除此注释
                return arg0 == arg1;
            }
            
            @Override
            public int getCount() {
                // TODO 自动生成方法存根注释，方法实现时请删除此注释
                return listView.size();
            }
            
            /**
             * @param position
             * @return
             * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
             */
            @Override
            public CharSequence getPageTitle(int position) {
                // TODO 自动生成方法存根注释，方法实现时请删除此注释
                return "AAA" + position;
            }
        };
        vp_demo_viewpager.setAdapter(pagerAdapter);
    }
}
