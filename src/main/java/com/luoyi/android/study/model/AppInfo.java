package com.luoyi.android.study.model;

import java.io.Serializable;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class AppInfo implements Serializable {
	public String appName = "";
	public String packageName = "";
	public String versionName = "";
	public int versionCode = 0;
	public Drawable appIcon = null;

	public void print() {
		Log.v("app", "Name:" + appName + " Package:" + packageName);
		Log.v("app", "Name:" + appName + " versionName:" + versionName);
		Log.v("app", "Name:" + appName + " versionCode:" + versionCode);
	}
}
