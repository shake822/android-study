package com.luoyi.android.study.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonDBHelper {

	private PersionSQLiteOpenHelper PersonSQLiteOpenHelper;
	
	public PersonDBHelper(Context context){
		PersonSQLiteOpenHelper = new PersionSQLiteOpenHelper(context,"Persion.db",null,1);
	}
	
	public SQLiteOpenHelper getSQLiteOpenHelper(){
		return PersonSQLiteOpenHelper;
	}
	
	private class PersionSQLiteOpenHelper extends SQLiteOpenHelper{
		public PersionSQLiteOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.e("PersionDBHelper", "onCreate");
			db.execSQL("create table Person (id varchar(40) primary key,name varchar(40),tel varchar(40))");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.e("PersionDBHelper", "onUpgrade");
		}

	}
	
}
