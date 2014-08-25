/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.study.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luoyi.android.study.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 25, 2014
 */
public class TestFragment extends Fragment {

	/** FIXME */
	static final String MSG = "MSG";
	/** FIXME */
	private String defaultMsg = "hello shake ";

	/**
	 * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
	 * 
	 * @param msg
	 * @return
	 */
	public static TestFragment instance(String msg) {
		TestFragment t = new TestFragment();
		Bundle bundle = new Bundle();
		bundle.putString(MSG, msg);
		t.setArguments(bundle);
		return t;
	}

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.demo_textview, null);
		TextView tv = (TextView) view.findViewById(R.id.tv_demo_textview);
		tv.setText(defaultMsg);
		return view;
	}

	/**
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		defaultMsg = getArguments().getString(MSG, defaultMsg);
	}
}
