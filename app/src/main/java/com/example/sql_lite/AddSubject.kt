package com.example.sql_lite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddSubject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)

        val dB = SQLhelper(applicationContext)
        val titleInput = findViewById<EditText>(R.id.title_edit_text)
        val descInput = findViewById<EditText>(R.id.desc_edit_text)
        val add = findViewById<Button>(R.id.add_btn)

        add.setOnClickListener {
            val titleText = titleInput.text.toString().trim()
            val descText = descInput.text.toString().trim()
            dB.putData(titleText,descText)
            Toast.makeText(this,"data is inserted successfully",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        this.finish()
    }
}