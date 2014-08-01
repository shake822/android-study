
package com.luoyi.android.study.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
    
    @SuppressLint("NewApi")
    public void sendNotification(View view) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Notification notification = new Notification(R.drawable.btn_star_big_on_selected, "测试", System.currentTimeMillis());
        Notification notification = new Notification.Builder(getApplicationContext()).setContentTitle("New mail from ").setContentText(" 测试")
            .setSmallIcon(R.drawable.btn_star_big_on_pressed).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
            .build();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:111"));
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        notification.contentIntent = pi;
        nm.notify(1, notification);
        
    }
}
