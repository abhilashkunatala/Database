package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "student.db";
    public static final String Table_Name = "student_table";
    // As the table contains four coloumns
    public static final String col_1 = "Id";
    public static final String col_2 = "Name  ";
    public static final String col_3 = "Surname";
    public static final String col_4= "Marks";

    //default super constructer
     public DatabaseHelper(Context context) {
        super(context, Database_Name,null , 1);
        // when ever this constructor  is called database is created

         SQLiteDatabase db = this.getWritableDatabase();
         //to create database and table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         // to create a table

        db.execSQL("create table " + Table_Name+"(Id INTEGER PRIMARY KEY AUTOINCREMENT ,Name TEXT ,Surname TEXT,Marks INTEGER )");
        //Creating the table with student name and adding the columns and their type and Defining the primary key
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " +Table_Name);
        onCreate(db);


    }


    //method to insert the data
    public boolean insertData(String Name , String Surname, String Marks ){
        SQLiteDatabase db = this.getWritableDatabase();
        //to create database and table

        ContentValues contentValues = new ContentValues();
        // by usimg the content value we insert the data  to coloumns

        contentValues.put(col_2,Name);
        contentValues.put(col_3,Surname);
        contentValues.put(col_4,Marks);
        long result = db.insert(Table_Name,null,contentValues);
        if(result== -1){
            return false;

        }
        else
            return true;



    }

    public Cursor getAllData(){
         //cursor class is calss which provide randaom read write access to result ie res

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery( "select * from "+Table_Name,null);
            return res;

    }

    public boolean updatefunction(String  Id ,String Name , String Surname, String Marks  ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,Id);
        contentValues.put(col_2,Name);
        contentValues.put(col_3,Surname);
        contentValues.put(col_4,Marks);

        db.update(Table_Name, contentValues, "Id = ?",new String[]{Id});
        return true;
    }

    public Integer deletefunction(String Id ){
        SQLiteDatabase db = this.getWritableDatabase();
        // to get the instance
       return db.delete(Table_Name,"Id =?", new String[]{Id});
        // id =? beacuse user will enter the id .? is replaced b id and we create the string array to store that id



    }


}
