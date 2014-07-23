package com.luoyi.android.study;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.luoyi.android.study.adapter.MainListViewAdapter;
import com.luoyi.android.study.appservice.MainDemoList;
import com.luoyi.android.study.model.Demo;
import com.luoyi.android.study.view.FileStore;
import com.luoyi.android.study.view.SendSMS;
import com.luoyi.android.study.view.SimpleAdaper;

public class HelloAndroidActivity extends Activity {

	private ListView lv_main;
	
	private List<Demo> demoList ;

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_main = (ListView) findViewById(R.id.lv_main);
		try {
			demoList = MainDemoList.getMainDemoList(getAssets().open("DemoList.xml"));
		} catch (IOException e) {
			demoList = Collections.emptyList();
		}
		// 创建一个List集合，其元素是Map
		MainListViewAdapter adapter = new MainListViewAdapter(this,demoList);
		lv_main.setAdapter(adapter);
		lv_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Demo demo = demoList.get(position);
				if("SendSMS".equals(demo.getId())){
					startNewActivity(SendSMS.class);
				}else if("FileStore".equals(demo.getId())){
					startNewActivity(FileStore.class);
				}else if("Adapter".equals(demo.getId())){
					startNewActivity(SimpleAdaper.class);
				}
			}
		});
	}
	
	private void startNewActivity(Class<?> className){
		Intent intent = new Intent();  
		intent.setClass(HelloAndroidActivity.this, className);  
		startActivity(intent); 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.luoyi.android.study.R.menu.main, menu);
		return true;
	}

}
