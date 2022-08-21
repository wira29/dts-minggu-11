package com.example.dts_minggu_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        db = DatabaseHelper(this)
        val name : EditText = findViewById(R.id.input_name)
        val number : EditText = findViewById(R.id.input_number)

        val btnSave : Button = findViewById(R.id.btn_save)
        btnSave.setOnClickListener {

            if(name.text.isEmpty()){
                name.error = "Nama tidak boleh kosong !"
                name.isFocusable
                return@setOnClickListener
            } else if( number.text.isEmpty()){
                number.error = "Nomor tidak boleh kosong !"
                number.isFocused
                return@setOnClickListener
            }

            val res = db.addContactDetail(name.text.toString(), number.text.toString())
            if (res >= 0){
                Toast.makeText(this, "Berhasil menambahkan kontak !", Toast.LENGTH_LONG).show()
                finish()
            } else{
                Toast.makeText(this, "Gagal menambahkan kontak !", Toast.LENGTH_LONG).show()
            }
        }
    }
}