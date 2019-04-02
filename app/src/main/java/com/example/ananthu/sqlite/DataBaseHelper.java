package com.example.ananthu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NAME = "student";
    public static final String COL_1 = "rollno";
    public static final String COL_2 = "name";
    public static final String COL_3 = "age";
    public static final String COL_4 = "mark";
    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(rollno integer primary key,name varchar(20),age integer,mark double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("create table if not exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String rollno,String name,String age,String mark)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,rollno);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,mark);
       long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return  false;
        else
            return  true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return  c;
    }
    public boolean updateData(String rollno,String name,String age,String mark)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,rollno);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,mark);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"rollno = ?",new String[]{rollno});
        return  true;
    }
    public Integer deleteData(String rollno)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       int a= sqLiteDatabase.delete(TABLE_NAME,"rollno = ?",new String[]{rollno});
       return  a;
    }
}
