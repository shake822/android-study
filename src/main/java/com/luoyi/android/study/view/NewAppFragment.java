/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luoyi.android.study.R;
import com.luoyi.android.study.model.AppInfo;

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
public class NewAppFragment extends Fragment {

	private ArrayList<AppInfo> appList = new ArrayList<AppInfo>(); // 用来存储获取的应用信息数据

	private ListView lv_localApps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		PackageManager pm = getActivity().getPackageManager();
		List<PackageInfo> packages = pm
				.getInstalledPackages(PackageManager.GET_ACTIVITIES);
		for (PackageInfo packageInfo : packages) {
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
				continue;
			}
			AppInfo tmpInfo = new AppInfo();
			tmpInfo.appName = packageInfo.applicationInfo.loadLabel(pm)
					.toString();
			tmpInfo.packageName = packageInfo.packageName;
			tmpInfo.versionName = packageInfo.versionName;
			tmpInfo.versionCode = packageInfo.versionCode;
			tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(pm);
			appList.add(tmpInfo);
		}
	}

	/**
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自动生成方法存根注释，方法实现时请删除此注释
		View view = inflater.inflate(R.layout.demo_weixin_newapp, null);
		lv_localApps = (ListView) view.findViewById(R.id.lv_localApps);
		lv_localApps.setAdapter(new MyListAdapter());
		return view;
	}

	public class MyListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return appList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			AppInfo appInfo = appList.get(position);
			View v = getActivity().getLayoutInflater().inflate(
					R.layout.demo_weixin_app_item, null);
			((ImageView) v.findViewById(R.id.iv_appIcon))
					.setImageDrawable(appInfo.appIcon);
			((TextView) v.findViewById(R.id.tv_appName))
					.setText(appInfo.appName);
			((TextView) v.findViewById(R.id.tv_appPackage))
					.setText(appInfo.packageName);
			return v;
		}
	}

}
