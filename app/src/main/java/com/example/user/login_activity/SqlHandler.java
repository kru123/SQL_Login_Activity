package com.example.user.login_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SqlHandler extends SQLiteOpenHelper {

    public static  final  String Database_Name ="Students.db";
    public static  final  String Table_Name = "Students_table";
    public static  final  String Col1 ="STUDENT_ID";
    public static  final  String Col2 ="NAME";
    public static  final  String Col3 ="COLLEGE_NAME";
    public static  final  String Col4 ="EMAIL";


    public SqlHandler(Context context) {
        super(context, Database_Name, null, 1);

    }

    //Creating database table in local server ID is Primary key which will auto increment as database goes on adding.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ Table_Name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,COLLEGE_NAME TEXT,EMAIL TEXT)");

    }



    //Upgrade method to drop the table if the name exits.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }

    //Method to insert date in table

    public boolean onInsert(String name,String college_name,String email){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,college_name);
        contentValues.put(Col4,email);
        long insert=db.insert(Table_Name,null,contentValues);
        if(insert == -1){
            return false;
        }
        return true;

    }
}
