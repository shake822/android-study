package com.luoyi.android.study.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.luoyi.android.study.R;

public class SimpleAdaper extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("iconid", R.drawable.btn_star_big_off_disable_focused);
		map.put("name", "HAHA");
		map.put("desc", "只是简单测试下");
		data.add(map);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("iconid", R.drawable.btn_star_big_off_pressed);
		map1.put("name", "测试二");
		map1.put("desc", "这次又点不简单了");
		data.add(map1);
		
		ListView lv_main = (ListView) findViewById(R.id.lv_main);
		lv_main.setAdapter(new SimpleAdapter(this, data, R.layout.list_item, new String[]{"iconid","name","desc"}, new int[]{R.id.iv_icon,R.id.tv_name,R.id.tv_desc}));
	}

	
}
