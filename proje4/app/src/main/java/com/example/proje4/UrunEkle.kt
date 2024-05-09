package com.example.proje4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class UrunEkle : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edDesc: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    private lateinit var sqlitehelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urun_ekle)
        initView()
        sqlitehelper= SQLiteHelper(this)
        btnAdd.setOnClickListener { addUrun() }
        btnView.setOnClickListener { gosterUrun() }
    }
    private fun addUrun(){
        val name= edName.text.toString()
        val desc=edDesc.text.toString()
        if (desc.isNotEmpty()&&name.isNotEmpty()){
            val urun= Urunler(name= name, desc = desc)
            val status= sqlitehelper.insertUrun(urun)
            if (status>-1){
                Toast.makeText(this, "Ürün Ekleme Başarılı.", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else{
                Toast.makeText(this, "Ürün Ekleme Başarısız.", Toast.LENGTH_SHORT).show()

            }


        }else{
            Toast.makeText(this, "Lütfen Gerekli Bilgileri Doldurun", Toast.LENGTH_SHORT).show()
        }

    }
    private fun clearEditText(){
        edName.text.clear()
        edDesc.text.clear()
        edName.requestFocus()
    }
    private fun gosterUrun(){
        var intentGosterUrun=Intent(this,UrunGoster::class.java)
        startActivity(intentGosterUrun)
    }
    private fun initView(){
        edName=findViewById(R.id.editTextİsimGiris)
        edDesc=findViewById(R.id.multiAutoCompleteTextViewUrunAciklama)
        btnAdd= findViewById(R.id.urunEkleBtn)
        btnView=findViewById(R.id.urunGosterBtn)
    }
}