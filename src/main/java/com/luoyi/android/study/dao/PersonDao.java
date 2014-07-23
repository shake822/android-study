package com.luoyi.android.study.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.luoyi.android.study.model.Person;

public class PersonDao {

	private SQLiteOpenHelper personSQLiteOpenHelper;
	
	public PersonDao(Context context){
		personSQLiteOpenHelper = new PersonDBHelper(context).getSQLiteOpenHelper();
	}
	
	public void savePerson(Person person){
		SQLiteDatabase  db = personSQLiteOpenHelper.getWritableDatabase();
		db.execSQL("insert into person (id,name,tel) values(?,?,?)", new Object[]{person.getId(),person.getName(),person.getTel()});
		db.close();
	}
	
	public Person getPersonByID(String personID){
		Person person = null;
		SQLiteDatabase db = personSQLiteOpenHelper.getReadableDatabase();
		Cursor  cursor =db.rawQuery("select name,tel from person where id = ?",  new String[]{personID});
		if(cursor.moveToNext()){
			person = new Person();
			person.setId(personID);
			person.setName(cursor.getString(0));
			person.setTel(cursor.getString(1));
		}
		db.close();
		return person;
	}
	
	public void deletePersonByID(String personID){
		SQLiteDatabase  db = personSQLiteOpenHelper.getWritableDatabase();
		db.beginTransaction();
		db.execSQL("delete from person where id = ?", new Object[]{personID});
		db.endTransaction();
		db.close();
	}
	public List<Person> findAllPerson(){
		List<Person> lstPerson = new ArrayList<Person>(16);
		SQLiteDatabase db = personSQLiteOpenHelper.getReadableDatabase();
		Cursor  cursor =db.rawQuery("select id,name,tel from person", null);
		while(cursor.moveToNext()){
			lstPerson.add( new Person(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
		}
		db.close();
		return lstPerson;
	}
}
