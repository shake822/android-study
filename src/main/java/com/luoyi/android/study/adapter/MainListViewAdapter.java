package com.luoyi.android.study.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luoyi.android.study.model.Demo;

public class MainListViewAdapter extends BaseAdapter {
	
	private List<Demo> demoList;
	private Context context;
	public MainListViewAdapter(Context context,List<Demo> demoList){
		this.demoList = demoList;
		this.context  = context;
	}
	@Override
	public int getCount() {
		return demoList.size();
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
		Demo demo = demoList.get(position);
		TextView tv_title = new TextView(this.context);
		tv_title.setText(demo.getName());
		tv_title.setTextSize(20);
		return tv_title;
	}
}
