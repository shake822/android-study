/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

package com.luoyi.android.study.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.luoyi.android.study.R;

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
 * @version 2014年8月7日 zhaoqunqi
 */
public class EditPictureActivity extends Activity {
    
    private ImageView iv_editPicture;
    
    private Bitmap bm;
    
    /**
     * 相片浏览的比例
     */
    private int scanSize = 1;
    
    /**
     * @param savedInstanceState
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成方法存根注释，方法实现时请删除此注释
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_editpicture);
        iv_editPicture = (ImageView) findViewById(R.id.iv_editpicture);
    }
    
    private void loadCanvasForPicture(Bitmap bitmap) {
        // bitmap = BitmapFactory.decodeFile("sdcard/100.jpg");
        Log.i("EditPictureActivity", bitmap.getWidth() + "  " + bitmap.getHeight());
        bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bm);
        // 在画布上画上背景图
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        final Paint paint = new Paint();
        paint.setStrokeWidth(5);
        // paint.setTextSize(200);
        paint.setColor(Color.RED);
        // canvas.drawText("s撒旦法撒旦法速度发生的发生的发生地方", 10, 10, paint);
        iv_editPicture.setImageBitmap(bm);
        iv_editPicture.setOnTouchListener(new OnTouchListener() {
            
            int startX;
            
            int startY;
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        
                        int stopX = (int) event.getX();
                        int stopY = (int) event.getY();
                        Log.i("EditPictureActivity", stopX + " " + stopY + "  " + event.getXPrecision() * getWindow().getAttributes().width);
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        iv_editPicture.setImageBitmap(bm);
                        break;
                }
                return true;
            }
        });
    }
    
    public void savePicture(View view) {
        // initPicture();
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        try {
            OutputStream stream = new FileOutputStream(file);
            bm.compress(CompressFormat.JPEG, 100, stream);
            stream.close();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            sendBroadcast(intent);
            Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        
    }
    
    public void selectPicture(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }
    
    /**
     * @param requestCode
     * @param resultCode
     * @param data
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            Bitmap bitmap;
            try {
                Options options = new BitmapFactory.Options();
                // options.inSampleSize = 5;
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
                loadCanvasForPicture(bitmap);
                // iv_editPicture.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
