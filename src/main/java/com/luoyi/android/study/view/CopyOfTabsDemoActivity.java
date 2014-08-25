package com.luoyi.android.study.view;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.luoyi.android.study.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 25, 2014
 */
public class CopyOfTabsDemoActivity extends Activity {

	private ViewPager mViewPager;

	private MyTabListener tabListener;
	private final static int TAB_COUNT = 3;

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
		ArrayList<View> listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.demo_button, null));
		listViews.add(mInflater.inflate(R.layout.demo_spinner, null));
		listViews.add(mInflater.inflate(R.layout.demo_imageview, null));
		mViewPager.setAdapter(new MyPagerAdapter(listViews));
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
		tabListener = new MyTabListener();
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// actionBar.set
		for (int i = 0; i < TAB_COUNT; i++) {
			Tab tab = actionBar.newTab();
			tab.setText("Tab " + i);
			tab.setTabListener(tabListener);
			actionBar.addTab(tab);
		}
	}

	private class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		/**
		 * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return "ssss" + position;
		}

	}

	private class MyPageChangeListener implements
			ViewPager.OnPageChangeListener {

		/**
		 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
		 */
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		/**
		 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int,
		 *      float, int)
		 */
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		/**
		 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
		 */
		@Override
		public void onPageSelected(int arg0) {
			// getActionBar().getTabAt(arg0).select();
			mViewPager.setCurrentItem(arg0);
			System.out.println(" onPageSelected " + arg0);
		}

	}

	private class MyTabListener implements TabListener {

		/**
		 * @see android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar.Tab,
		 *      android.app.FragmentTransaction)
		 */
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			System.out.println(tab.getText() + "  onTabSelected");
			mViewPager.setCurrentItem(tab.getPosition());
		}

		/**
		 * @see android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar.Tab,
		 *      android.app.FragmentTransaction)
		 */
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			System.out.println(tab.getText() + "  onTabUnselected");
		}

		/**
		 * @see android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar.Tab,
		 *      android.app.FragmentTransaction)
		 */
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			System.out.println(tab.getText() + "  onTabReselected");

		}

	}
}
