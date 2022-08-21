 package com.example.dts_minggu_11

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

 class MainActivity : AppCompatActivity() {
     private lateinit var db:DatabaseHelper
     private var data:ArrayList<Array<String>> = arrayListOf()
     private lateinit var adapter : Adapter
     private lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DatabaseHelper(this)
        rv = findViewById(R.id.recyclerview)
        rv.layoutManager = LinearLayoutManager(this)

        loadData()

        adapter = Adapter(data, this)
        rv.adapter = adapter

        val btnAdd : FloatingActionButton = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

     fun loadData() {
        val res = db.getAllContactsList()
         data = res
     }

     override fun onResume() {
         super.onResume()
         loadData()
         adapter = Adapter(data, this)
         rv.adapter = adapter
     }
}