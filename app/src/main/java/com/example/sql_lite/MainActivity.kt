package com.example.sql_lite

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),Adapter.OnMyClickListener {

    lateinit var lists: ArrayList<Subject>
    lateinit var db: SQLhelper
    lateinit var data: Cursor
    lateinit var adapter: Adapter
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lists = ArrayList()
        db = SQLhelper(applicationContext)
        data = db.getData

        adapter = Adapter(lists, applicationContext, this)
        recycler = findViewById(R.id.recycler_main)

        recycler.layoutManager = GridLayoutManager(this,2)
        recycler.adapter = adapter

        val go = findViewById<Button>(R.id.btn_main)

        go.setOnClickListener {
            startActivity(Intent(this, AddSubject::class.java))
        }
    }

    private fun showData() {
        if (data.count == 0) {
            Toast.makeText(this, "there is no data", Toast.LENGTH_SHORT).show()
        } else {
            while (data.moveToNext()) {
                lists.add(Subject(data.getString(0), data.getString(1), data.getString(2)))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showData()
    }

    override fun onPause() {
        super.onPause()
        adapter.notifyDataSetChanged()
        this.finish()
    }

    /*override fun onItemClick(position: Int) {
        val itemId = lists[position].id
        db.delData(itemId)
        lists.remove(lists[position])
        adapter.notifyItemRemoved(position)
        showData()
    }
     */

    override fun deleteAction(position: Int) {
        val itemId = lists[position].id
        db.delData(itemId)
        lists.remove(lists[position])
        adapter.notifyItemRemoved(position)
        showData()
    }
}