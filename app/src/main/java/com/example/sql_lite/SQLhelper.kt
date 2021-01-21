package com.example.sql_lite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.View

class SQLhelper(context: Context) : SQLiteOpenHelper(context,db_name,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $tb_name (ID INTEGER PRIMARY KEY AUTOINCREMENT,S_title TEXT,S_desc TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tb_name")
    }

    fun putData(title_text: String,desc_text: String){
        val dB = this.writableDatabase
        val values = ContentValues()
        values.put(title,title_text)
        values.put(desc,desc_text)
        dB.insert(tb_name,null,values)
    }

    val getData: Cursor get() {
        val dB = this.readableDatabase
        return dB.rawQuery("select * from $tb_name",null)
    }

    fun delData(itemId: String): Int {
        val dB = this.writableDatabase
        val result = dB.delete(tb_name, "$id = ?", arrayOf(itemId))
        dB.close()
        return result
    }

    companion object{
        const val db_name = "subjects.db "
        const val tb_name = "Subject"
        const val id = "ID"
        const val title = "S_title"
        const val desc = "S_desc"
    }
}