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
import com.luoyi.android.study.utils.ChangeImgEvent;
import com.luoyi.android.study.view.ActionBarActivity;
import com.luoyi.android.study.view.ButtonActivity;
import com.luoyi.android.study.view.EditPictureActivity;
import com.luoyi.android.study.view.EventBusActivity;
import com.luoyi.android.study.view.FileStore;
import com.luoyi.android.study.view.GridViewActivity;
import com.luoyi.android.study.view.NotificationActivity;
import com.luoyi.android.study.view.SendSMS;
import com.luoyi.android.study.view.ShowImageView;
import com.luoyi.android.study.view.SimpleAdaper;
import com.luoyi.android.study.view.SpinnerActivity;
import com.luoyi.android.study.view.TabsDemoActivity;
import com.luoyi.android.study.view.ViewPagesActivity;
import com.luoyi.android.study.view.WeiXinDemoActivity;

import de.greenrobot.event.EventBus;

public class HelloAndroidActivity extends Activity {

	private ListView lv_main;

	private List<Demo> demoList;

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
			demoList = MainDemoList.getMainDemoList(getAssets().open(
					"DemoList.xml"));
		} catch (IOException e) {
			demoList = Collections.emptyList();
		}
		// 创建一个List集合，其元素是Map
		MainListViewAdapter adapter = new MainListViewAdapter(this, demoList);
		lv_main.setAdapter(adapter);

		lv_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Demo demo = demoList.get(position);
				if ("SendSMS".equals(demo.getId())) {
					startNewActivity(SendSMS.class);
				} else if ("FileStore".equals(demo.getId())) {
					startNewActivity(FileStore.class);
				} else if ("Adapter".equals(demo.getId())) {
					startNewActivity(SimpleAdaper.class);
				} else if ("ShowImageView".equals(demo.getId())) {
					startNewActivity(ShowImageView.class);
				} else if ("ShowGrid".equals(demo.getId())) {
					startNewActivity(GridViewActivity.class);
				} else if ("Notification".equals(demo.getId())) {
					startNewActivity(NotificationActivity.class);
				} else if ("ActionBar".equals(demo.getId())) {
					startNewActivity(ActionBarActivity.class);
				} else if ("Spinner".equals(demo.getId())) {
					startNewActivity(SpinnerActivity.class);
				} else if ("WeiXinDemo".equals(demo.getId())) {
					startNewActivity(WeiXinDemoActivity.class);
				} else if ("EditPicture".equals(demo.getId())) {
					startNewActivity(EditPictureActivity.class);
				} else if ("ViewPages".equals(demo.getId())) {
					startNewActivity(ViewPagesActivity.class);
				} else if ("EventBus".equals(demo.getId())) {
					startNewActivity(EventBusActivity.class);
				} else if ("ButtonDemo".equals(demo.getId())) {
					startNewActivity(ButtonActivity.class);
				} else if ("TabsDemo".equals(demo.getId())) {
					startNewActivity(TabsDemoActivity.class);
				}
			}
		});
		EventBus.getDefault().register(this);
	}

	private void startNewActivity(Class<?> className) {
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

	public void onEventAsync(ChangeImgEvent event) {
		if (event.getType() == 1) {
			System.out
					.println(" HelloAndroidActivity -------------+++++++++++ begin");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out
					.println(" HelloAndroidActivity -------------+++++++++++ end");
		}
	}
}
