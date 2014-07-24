package com.luoyi.android.study.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.image.SmartImageView;
import com.luoyi.android.study.R;

public class ShowImageView extends Activity {
	
	private SmartImageView iv_image ;
	
	private String [] images = {"http://img0.bdstatic.com/img/image/shouye/gxbxsjjiaojzll.jpg","http://img0.bdstatic.com/img/image/shouye/gxxhz-11592230590.jpg","http://img0.bdstatic.com/img/image/shouye/mnqc-11842648673.jpg"};
	private int i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_imageview);
		iv_image = (SmartImageView) findViewById(R.id.iv_image);
	}

	 public void change(View view){
		 iv_image.setImageUrl(images[getNext()]);
	 }
	 
	 private int getNext(){
		 i++;
		 if(i>= images.length){
			 i=0;
		 }
		 return i;
	 }
}
