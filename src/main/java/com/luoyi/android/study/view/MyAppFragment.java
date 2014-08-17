/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

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
 * @version 2014年8月5日 zhaoqunqi
 */
public class MyAppFragment extends Fragment {
    
    private GridView gridView;
    
    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_weixin_myapp, null);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        ((Button) view.findViewById(R.id.btn_invokeRightFragment)).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                invokeRight(v);
            }
        });
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.btn_star_big_off_disable_focused);// 添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));// 按序号做ItemText
            lstImageItem.add(map);
        }
        // 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(inflater.getContext(), // 没什么解释
            lstImageItem,// 数据来源
            R.layout.demo_gridview_item,// night_item的XML实现
            
            // 动态数组与ImageItem对应的子项
            new String[] { "ItemImage", "ItemText" },
            
            // ImageItem的XML文件里面的一个ImageView,两个TextView ID
            new int[] { R.id.ItemImage, R.id.ItemText });
        // 添加并且显示
        gridView.setAdapter(saImageItems);
        return view;
    }
    
    public void invokeRight(View view) {
        ((WeiXinDemoActivity) getActivity()).invokeRightFragment("sdfsdfsdfsdf");
    }
}
