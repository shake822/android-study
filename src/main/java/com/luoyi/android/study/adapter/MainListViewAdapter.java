package com.luoyi.android.study.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luoyi.android.study.R;
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

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Demo demo = demoList.get(position);
		RelativeLayout  rl = new RelativeLayout(this.context);
		TextView tv_title = new TextView(this.context);
		tv_title.setId(1);
		tv_title.setText(demo.getName());
		tv_title.setTextSize(20);
		rl.addView(tv_title);
		
		TextView tv_desc = new TextView(this.context);
		tv_desc.setText(demo.getDesc());
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				);
		layoutParams.addRule(RelativeLayout.BELOW,tv_title.getId());
		tv_desc.setLayoutParams(layoutParams);
		rl.addView(tv_desc);
		
		/**
		 * 
		
		ImageButton ib = new ImageButton(this.context);
		ib.setFocusable(false);
		RelativeLayout.LayoutParams ib_layoutParams = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				);
		ib_layoutParams.addRule(RelativeLayout.ALIGN_TOP,tv_title.getId());
		ib_layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ib.setImageDrawable(this.context.getResources().getDrawable(R.drawable.right));
		ib.setLayoutParams(ib_layoutParams);
		rl.addView(ib);
		 */
		ImageView iv = new ImageView(this.context);
		RelativeLayout.LayoutParams iv_layoutParams = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
				);
		iv_layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,tv_title.getId());
		iv_layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		iv_layoutParams.rightMargin=10;
		iv.setImageDrawable(this.context.getResources().getDrawable(R.drawable.right));
		iv.setLayoutParams(iv_layoutParams);
		rl.addView(iv);
		return rl;
	}
}
