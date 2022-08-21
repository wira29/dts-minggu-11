package com.example.dts_minggu_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditActivity : AppCompatActivity() {
    private lateinit var db:DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        db = DatabaseHelper(this)

        val data : Array<String> = intent.getStringArrayExtra("data")!!

        val name : EditText = findViewById(R.id.input_name)
        val number : EditText = findViewById(R.id.input_number)
        name.text = data[0].toEditable()
        number.text = data[1].toEditable()

        val btnSave : Button = findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            val res = db.updateContact(data[2], name.text.toString(), number.text.toString())
            if(res > 0){
                Toast.makeText(this, "Berhasil mengedit kontak !", Toast.LENGTH_LONG).show()
                finish()
            } else{
                Toast.makeText(this, "Gagal mengedit kontak !", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}