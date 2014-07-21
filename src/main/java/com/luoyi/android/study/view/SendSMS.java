package com.luoyi.android.study.view;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.luoyi.android.study.R;

public class SendSMS extends Activity implements OnClickListener {
	
	private EditText ed_number;
	private EditText ed_content;
	private Button btn_send;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_sendsms);
		ed_number = (EditText) findViewById(R.id.et_number);
		ed_content = (EditText) findViewById(R.id.ed_content);
		btn_send = (Button)findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		 switch(v.getId()){
		 	case R.id.btn_send:
		 		sendSms();
		 	break;
		 		
		 }
	}
	
	private void  sendSms(){
		String number = ed_number.getText().toString();
		String content = ed_content.getText().toString();
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, content, null, null);
	}
}
