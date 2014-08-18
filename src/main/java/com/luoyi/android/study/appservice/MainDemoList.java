package com.luoyi.android.study.appservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

import com.luoyi.android.study.model.Demo;

/**
 * 读取Demo的资源文件
 * 
 * @author zhaoqunqi
 * 
 */
public class MainDemoList {

	/**
	 * 获取所有的Demo列表
	 * 
	 * @param inputStream
	 *            XML文件流
	 * @return List{Demo}
	 * 
	 */
	public static List<Demo> getMainDemoList(InputStream inputStream) {
		List<Demo> allDemoList = null;
		XmlPullParser parser = Xml.newPullParser();
		Demo demo = null;
		try {
			parser.setInput(inputStream, "utf-8");
			int type = parser.getEventType();
			while (type != XmlPullParser.END_DOCUMENT) {
				String tagName = parser.getName();
				switch (type) {
				case XmlPullParser.START_TAG:
					if ("DemoList".equals(tagName)) {
						allDemoList = new ArrayList<Demo>(16);
					} else if ("Demo".equals(tagName)) {
						demo = new Demo();
						demo.setId(parser.getAttributeValue(0));
						demo.setName(parser.getAttributeValue(1));
					} else if ("desc".equals(tagName)) {
						demo.setDesc(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if ("Demo".equals(tagName)) {
						allDemoList.add(demo);
						demo = null;
					}
					break;
				}
				type = parser.next();
			}
		} catch (XmlPullParserException e) {
			Log.e("MainDemoList", "读取XML错误");
			return Collections.emptyList();
		} catch (IOException e) {
			Log.e("MainDemoList", "读取XML错误");
			return Collections.emptyList();
		}
		return allDemoList;
	}
}
