package com.example.sqliteexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	SQLiteOpenHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new MySqliteOpenHelper(this);
		for (int i=1;i<=10;i++)
		{
			String name = "user"+i;
			String passwd = "passwd"+i;
			int ages = i;
			insertUser(name,passwd,ages);
			//deleteUser(name);
			//updateUser(name,ages+5);
		}
		this.showAllUser();
		//this.deleteAllUser();
	}
	
	public void insertUser(String userName,String passWord,int age)
	{
		SQLiteDatabase sdb = helper.getReadableDatabase();
        String sql="insert into userinfo(username,password,age) values(?,?,?)";
        Object obj[]={userName,passWord,age};
        sdb.execSQL(sql, obj);
        sdb.close();
        return;
	}
	
	public void deleteUser(String userName)
	{
		SQLiteDatabase sdb = helper.getReadableDatabase();
		String sql="delete from userinfo where username=?";
		Object obj[]={userName};
		sdb.execSQL(sql,obj);
		sdb.close();
		return;
	}
	
	public void deleteAllUser()
	{
		SQLiteDatabase sdb = helper.getReadableDatabase();
		String sql = "delete from userinfo";
		Object obj[]={};
		sdb.execSQL(sql,obj);
		sdb.close();
		return;
	}
	
	public void updateUser(String userName, int age)
	{
		SQLiteDatabase sdb = helper.getReadableDatabase();
		String sql="update userinfo set age=? where username=?";
		Object obj[]={age,userName};
		sdb.execSQL(sql,obj);
		sdb.close();
		return;
	}
	
	public void showUser(String userName)
	{
		SQLiteDatabase sdb = helper.getReadableDatabase();
		String sql="select * from userinfo where username=?";
		Cursor cursor = sdb.rawQuery(sql, new String[] {userName});
		if(cursor.moveToFirst()==false)
		{
			cursor.close();
			return;
		}
		// 创建一个List集合，List集合的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		Map<String, Object> listHead = new HashMap<String, Object>();
		listHead.put("id","id");
		listHead.put("username","username");
		listHead.put("password","password");
		listHead.put("age","age");
		listItems.add(listHead);
		while (cursor.moveToNext())
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("id", cursor.getInt(0));	//用到int转换为string方法
			listItem.put("username", cursor.getString(1));
			listItem.put("password", cursor.getString(2));
			listItem.put("age", cursor.getInt(3));
			listItems.add(listItem);
		}
		
		// 创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.useritem,
				new String[] {"id", "username", "password", "age"},
				new int[] {R.id.userId, R.id.userName, R.id.passWord, R.id.age});
		ListView list = (ListView) findViewById(R.id.userList);
		// 为ListView设置Adapter
		list.setAdapter(simpleAdapter);
		sdb.close();
		return;
	}
	
	public void showAllUser()
	{
		SQLiteDatabase sdb = helper.getReadableDatabase();
		String sql="select * from userinfo";
		Cursor cursor = sdb.rawQuery(sql, new String[] {});
		
		// 创建一个List集合，List集合的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		Map<String, Object> listHead = new HashMap<String, Object>();
		listHead.put("id","id");
		listHead.put("username","username");
		listHead.put("password","password");
		listHead.put("age","age");
		listItems.add(listHead);
		while (cursor.moveToNext())
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("id", cursor.getInt(0));	//用到int转换为string方法
			listItem.put("username", cursor.getString(1));
			listItem.put("password", cursor.getString(2));
			listItem.put("age", cursor.getInt(3));
			listItems.add(listItem);
		}
		
		// 创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.useritem,
				new String[] {"id", "username", "password", "age"},
				new int[] {R.id.userId, R.id.userName, R.id.passWord, R.id.age});
		ListView list = (ListView) findViewById(R.id.userList);
		// 为ListView设置Adapter
		list.setAdapter(simpleAdapter);
		
		sdb.close();
		return;
	}
}
