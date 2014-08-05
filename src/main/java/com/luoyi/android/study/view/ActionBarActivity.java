/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

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
 * @version 2014年8月1日 作者
 */
@SuppressLint({ "NewApi", "ResourceAsColor" })
public class ActionBarActivity extends Activity {

	/**
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成方法存根注释，方法实现时请删除此注释
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_actionbar);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("我的应用");
		actionBar.setIcon(getResources().getDrawable(
				R.drawable.btn_star_big_on_pressed));
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.bg_blue));
		Tab tab1 = actionBar.newTab();
		tab1.setText("Tab选项卡一");
		ImageView view = new ImageView(getApplicationContext());
		// view.setBackground(getResources().getDrawable(R.drawable.bg_blue));
		view.setImageResource(R.drawable.ic_launcher);
		view.setScaleType(ScaleType.CENTER);
		// view.setBackgroundColor(android.R.color.white);
		tab1.setCustomView(view);
		tab1.setTabListener(new MyTabListener());
		actionBar.addTab(tab1);

		actionBar.addTab(actionBar.newTab().setText("Tab选项卡二")
				.setTabListener(new MyTabListener()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		Toast.makeText(getApplicationContext(), item.getTitle(),
				Toast.LENGTH_LONG).show();
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class MyTabListener implements TabListener {

		/**
		 * @param tab
		 * @param ft
		 * @see android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar.Tab,
		 *      android.app.FragmentTransaction)
		 */
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO 自动生成方法存根注释，方法实现时请删除此注释

		}

		/**
		 * @param tab
		 * @param ft
		 * @see android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar.Tab,
		 *      android.app.FragmentTransaction)
		 */
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO 自动生成方法存根注释，方法实现时请删除此注释

		}

		/**
		 * @param tab
		 * @param ft
		 * @see android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar.Tab,
		 *      android.app.FragmentTransaction)
		 */
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO 自动生成方法存根注释，方法实现时请删除此注释

		}

	}
}
