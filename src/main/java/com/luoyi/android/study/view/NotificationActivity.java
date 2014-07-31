package com.luoyi.android.study.view;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;

import com.luoyi.android.study.R;

public class NotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_notification);
	}
	
	public void sendNotification(View view){
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//		Notification  notification = new Notification(R.drawable.btn_star_big_on_selected, "测试", System.currentTimeMillis());
//		nm.notify(0, notification);
//		Notification.Builder nb = new Notification.Builder(getApplicationContext());
		
		
	}
}
