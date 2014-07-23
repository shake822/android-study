package com.luoyi.android.study.dao.test;

import java.util.List;
import java.util.UUID;

import android.test.AndroidTestCase;
import android.util.Log;

import com.luoyi.android.study.dao.PersonDao;
import com.luoyi.android.study.model.Person;

public class PersonDaoTest extends AndroidTestCase {

	
	
	public void testSavePerson(){
	    PersonDao personDao = new PersonDao(getContext());
		Person person = new Person();
		person.setId(UUID.randomUUID().toString());
		person.setName("Shake");
		person.setTel("13800138000");
		personDao.savePerson(person);
	}
	
	public void testfindAllPerson(){
		PersonDao personDao = new PersonDao(getContext());
		List<Person> lstPerson = personDao.findAllPerson();
		Log.i("PersonInfo",lstPerson.size()+"");
		for(Person person :lstPerson){
			Log.i("PersonInfo", person.toString());
		}
	}
}
