package com.luoyi.android.study.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.luoyi.android.study.R;
import com.luoyi.android.study.fragment.TestFragment;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 25, 2014
 */
public class TabsDemoActivity extends FragmentActivity {

	private ViewPager mViewPager;

	ArrayList<Fragment> mFragmentList;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_tab_main);
		findViews();
		initView();
	}

	/**
	 * 查询View
	 * 
	 */
	private void findViews() {
		mViewPager = (ViewPager) findViewById(R.id.vp_tab_demo);
	}

	private void initView() {
		mFragmentList = new ArrayList<Fragment>();
		mFragmentList.add(TestFragment.instance("one"));
		mFragmentList.add(TestFragment.instance("two"));
		mFragmentList.add(TestFragment.instance("three"));
		mFragmentList.add(TestFragment.instance("four"));
		mViewPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager()));
	}

	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		/**
		 * 构造函数
		 * 
		 * @param fm
		 */
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		/**
		 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int paramInt) {
			// TODO Auto-generated method stub
			return mFragmentList.get(paramInt);
		}

		/**
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mFragmentList.size();
		}

	}
}
