package com.example.dts_minggu_11

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    companion object {
        private val DATABASE_NAME : String = "contact_database";
        private val DATABASE_VERSION : Int = 1;
        private val TABLE_CONTACTS : String = "contacts";
        private val KEY_ID : String = "id";
        private val KEY_FIRSTNAME : String = "name"
        private val KEY_NUMBER : String = "number"

        private val CREATE_TABLE_STUDENTS : String = "CREATE TABLE " + TABLE_CONTACTS  + "(" + KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, " + KEY_NUMBER + " TEXT);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_STUDENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS '" + TABLE_CONTACTS +"'")
        onCreate(db)
    }

    fun addContactDetail(name : String, number : String) : Long {
        val db : SQLiteDatabase = this.writableDatabase
        val values : ContentValues = ContentValues()
        values.put(KEY_FIRSTNAME, name)
        values.put(KEY_NUMBER, number)

        return db.insert(TABLE_CONTACTS, null, values)
    }

    fun deleteContact(id : String) : Int {
        val db:SQLiteDatabase = this.writableDatabase
        return db.delete(TABLE_CONTACTS, KEY_ID + "=" + id, null)
    }

    fun updateContact(id: String, name:String, number:String) : Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values : ContentValues = ContentValues()
        values.put(KEY_FIRSTNAME, name)
        values.put(KEY_NUMBER, number)

        return db.update(TABLE_CONTACTS, values, KEY_ID + "=" + id, null)
    }

    @SuppressLint("Range")
    fun getAllContactsList(): ArrayList<Array<String>> {
        var contactsList : ArrayList<Array<String>> = arrayListOf()
        var id : String = ""
        var name : String = ""
        var number : String = ""
        val selectQuery : String = "SELECT * FROM " + TABLE_CONTACTS
        val db : SQLiteDatabase = this.readableDatabase
        val c : Cursor = db.rawQuery(selectQuery, null)

        if(c.moveToFirst()){
            do {
                id = c.getInt(c.getColumnIndex(KEY_ID)).toString()
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME))
                number = c.getString(c.getColumnIndex(KEY_NUMBER))
                contactsList.add(arrayOf(name, number, id))
            }while (c.moveToNext())
        }
        return  contactsList
    }
}