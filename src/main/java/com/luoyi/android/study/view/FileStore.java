package com.luoyi.android.study.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.luoyi.android.study.R;

public class FileStore extends Activity {

	private final static String TAG = "FileStore";
	private EditText et_content;
	private TextView tv_content;
	private RadioGroup rg_saveType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_filestore);
		et_content = (EditText) findViewById(R.id.et_content);
		tv_content = (TextView) findViewById(R.id.tv_content);
		rg_saveType = (RadioGroup) findViewById(R.id.rg_saveType);
	}

	public void readInfo(View view) {
		RadioButton radioButton = (RadioButton)findViewById(rg_saveType.getCheckedRadioButtonId());
		if(this.getString(R.string.Shared).equals(radioButton.getText())){
			SharedPreferences sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
			tv_content.setText(sp.getString("content", "没有值"));
		}else if(this.getString(R.string.sd).equals(radioButton.getText())){
			XmlPullParser  parser = Xml.newPullParser();
			File file = new File(Environment.getExternalStorageDirectory(),"data.xml");
			InputStream in =null;
			try {
				in = new FileInputStream(file);
				parser.setInput(in, "utf-8");
				 
				int type = parser.getEventType();
				while(type != XmlPullParser.END_DOCUMENT){
					switch(type){
						case XmlPullParser.START_TAG:
							if("content".equals(parser.getName())){
								tv_content.setText(parser.nextText());
							}
						break;
					}
					type = parser.next();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						//do nothing
					}
				}
			}
			
		}
	}

	public void saveInfo(View view) {
		String text = et_content.getText().toString();
		RadioButton radioButton = (RadioButton)findViewById(rg_saveType.getCheckedRadioButtonId());
		Log.i(TAG, "选择的Id"+radioButton.getText());
		if(this.getString(R.string.Shared).equals(radioButton.getText())){
			SharedPreferences sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString("content", text);
			editor.commit();
			Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
		}else if(this.getString(R.string.sd).equals(radioButton.getText())){
			File file = new File(Environment.getExternalStorageDirectory(),"data.xml");
			FileOutputStream os  = null;
			try {
			    os = new FileOutputStream(file);
				XmlSerializer  xs = Xml.newSerializer();
				xs.setOutput(os, "utf-8");
				xs.startDocument("utf-8", true);
				xs.startTag(null, "content");
				xs.text(text);
				xs.endTag(null, "content");
				xs.endDocument();
			} catch (Exception e) {
				Toast.makeText(this, "SD保存失败", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}finally{
				if(os != null){
					try {
						os.close();
					} catch (IOException e) {
						//do nothing
					}
				}
			}
			
		}
	}

}
