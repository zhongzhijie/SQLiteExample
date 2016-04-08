package com.example.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper{

	//���ݿ�����
    private static final String DBNAME="sqlitetest.db";
	//����
    private static final String USERINFO="userinfo";

    private static final int TESTVERSION=1;
                
    public MySqliteOpenHelper(Context context) {
        super(context, DBNAME, null, TESTVERSION);
        // TODO Auto-generated constructor stub
    }

	//��ʼ����������
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    	String sql1="create table "+USERINFO+"(id integer primary key autoincrement,"
    			+ "username varchar(20), password varchar(20), age integer)";
    	db.execSQL(sql1);
    }
	//ʧ�ܺ�ɾ�������´���
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
           if(newVersion>oldVersion)
           {
               String sql1="drop table if exists"+USERINFO;
               db.execSQL(sql1);
               this.onCreate(db);
           }
    }
}