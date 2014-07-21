package com.luoyi.android.study.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luoyi.android.study.R;

public class SendSMS extends Activity implements OnClickListener {
	
	private EditText ed_number;
	private EditText ed_content;
	private Button btn_send;
	private Button btn_call;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_sendsms);
		ed_number = (EditText) findViewById(R.id.et_number);
		ed_content = (EditText) findViewById(R.id.ed_content);
		btn_send = (Button)findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_call.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		 switch(v.getId()){
		 	case R.id.btn_send:
		 		sendSms();
		 	break;
		 	case R.id.btn_call:
		 		callTel();
		 	break;
		 }
	}
	
	/**
	 * 拨打电话
	 */
	private void callTel() {
		String number = ed_number.getText().toString().trim();
		Pattern pattern = Pattern.compile("^(\\d)+$"); 
		Matcher mat = pattern.matcher(number); 
		if(!mat.find()){
			Toast.makeText(getThisContext(), "电话号码格式不对", Toast.LENGTH_LONG).show();
			return;
		}
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+number));
		startActivity(intent);
	}
	
	/**
	 * 发送短信
	 */
	private void  sendSms(){
		String number = ed_number.getText().toString().trim();
		String content = ed_content.getText().toString().trim();
		Pattern pattern = Pattern.compile("^(\\d)+$"); 
		Matcher mat = pattern.matcher(number); 
		if(!mat.find()){
			Toast.makeText(getThisContext(), "电话号码格式不对", Toast.LENGTH_LONG).show();
			return;
		}
		if(content.length()==0){
			Toast.makeText(getThisContext(), "內容不能为空", Toast.LENGTH_LONG).show();
			return;
		}
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, content, null, null);
	}
	
	private Context getThisContext(){
		return this;
	}
}
