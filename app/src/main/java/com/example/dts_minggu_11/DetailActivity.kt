package com.example.dts_minggu_11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    private lateinit var db:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data : Array<String> = intent.getStringArrayExtra("data")!!
        db = DatabaseHelper(this)

        val name : TextView = findViewById(R.id.d_name)
        val number : TextView = findViewById(R.id.d_number)

        name.text = data[0]
        number.text = data[1]

        val btnDel : Button = findViewById(R.id.btn_delete)
        val btnUpdate : Button = findViewById(R.id.btn_update)

        btnDel.setOnClickListener {
            val res = db.deleteContact(data[2])
            if(res > 0){
                Toast.makeText(this, "Berhasil menghapus kontak !", Toast.LENGTH_LONG).show()
                finish()
            } else{
                Toast.makeText(this, "Gagal menghapus kontak !", Toast.LENGTH_LONG).show()
            }
        }

        btnUpdate.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
            finish()
        }
    }
}