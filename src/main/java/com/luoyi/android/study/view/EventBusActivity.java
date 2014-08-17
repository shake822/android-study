/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.luoyi.android.study.utils.ChangeImgEvent;

import de.greenrobot.event.EventBus;

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
 * @version 2014年8月11日 zhaoqunqi
 */
public class EventBusActivity extends Activity implements OnClickListener {
    
    private Button btn_post;
    
    /**
     * @param savedInstanceState
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成方法存根注释，方法实现时请删除此注释
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLt = new RelativeLayout(getApplication());
        btn_post = new Button(getApplicationContext());
        btn_post.setText("Post");
        btn_post.setWidth(100);
        btn_post.setHeight(100);
        btn_post.setOnClickListener(this);
        relativeLt.addView(btn_post);
        setContentView(relativeLt);
        EventBus.getDefault().register(this);
        initListener();
    }
    
    private void initListener() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        intentFilter.addDataScheme("package");
        registerReceiver(broadcastReceiver, intentFilter);
    }
    
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        
        @Override
        public void onReceive(Context context, Intent intent) {
            Uri uri = intent.getData();
            String pkg = uri.getSchemeSpecificPart();
            System.out.println("卸载了" + pkg);
        }
        
    };
    
    public void postEvent(View view) {
        EventBus.getDefault().post(new ChangeImgEvent(1));
    }
    
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    
    /**
     * 事件的相应在异步线程中
     * 
     * @param event
     */
    public void onEventAsync(ChangeImgEvent event) {
        if (event.getType() == 1) {
            System.out.println("-------------+++++++++++ begin");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-------------+++++++++++ end");
        }
    }
    
    /**
     * 事件相应在主线程
     * 
     * @param event
     */
    public void onEventMainThread(ChangeImgEvent event) {
        if (event.getType() == 1) {
            System.out.println("onEventMainThread -------------+++++++++++ ");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("onEventMainThread -------------+++++++++++ end");
        }
    }
    
    /**
     * 事件的响应在后台线程
     * 
     * @param event
     */
    public void onEventBackgroundThread(ChangeImgEvent event) {
        if (event.getType() == 1) {
            System.out.println("onEventBackgroundThread -------------+++++++++++ ");
        }
    }
    
    /**
     * @param v
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        postEvent(v);
    }
}
