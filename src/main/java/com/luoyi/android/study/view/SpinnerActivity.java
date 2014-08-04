package com.luoyi.android.study.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.luoyi.android.study.R;

public class SpinnerActivity extends Activity {

	private Spinner sp_province;
	private Spinner sp_city;

	private List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_spinner);
		list.add("北京");
		list.add("上海");
		list.add("深圳");
		list.add("福州");
		list.add("厦门");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		sp_province = (Spinner) findViewById(R.id.sp_province);
		sp_province.setAdapter(adapter);
		sp_province.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), list.get(position),
						Toast.LENGTH_LONG).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(),"你妹啊，选个啊",
						Toast.LENGTH_LONG).show();
			}

		});
		sp_city = (Spinner) findViewById(R.id.sp_city);
		
		
		AutoCompleteTextView ac_tv = (AutoCompleteTextView) findViewById(R.id.ac_tv);
		ac_tv.setAdapter(adapter);
		
	}
}
