package com.example.proje4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proje4.MainActivity

class AdminEkle : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edSifre: EditText
    private  lateinit var edSifre1: EditText
    private lateinit var btn_onayla: Button
    private lateinit var btn_cikis: Button
    private lateinit var btn_goster:Button
    private lateinit var sqLiteHelper: SQLiteHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_admin_ekle)
        initView()
        sqLiteHelper=SQLiteHelper(this)
        btn_cikis.setOnClickListener {
            val intent= Intent(this,SecimTablosu::class.java)
            startActivity(intent)
        }
        btn_onayla.setOnClickListener { onayla() }
        btn_goster.setOnClickListener{
            val intent= Intent(this,AdminGoster::class.java)
            startActivity(intent)
        }

    }



    private fun initView(){
        edName=findViewById(R.id.edEklenecekAdminAd)
        edSifre=findViewById(R.id.edEklenecekSifre1)
        edSifre1= findViewById(R.id.edEklenecekSifre2)
        btn_cikis=findViewById(R.id.btn_cikis)
        btn_onayla=findViewById(R.id.btn_onayla)
        btn_goster=findViewById(R.id.btn_goster)
    }

    private fun onayla(){
        val name=edName.text.toString()
        val sifre1=edSifre.text.toString()
        val sifre2=edSifre1.text.toString()
        if (name.isNotEmpty()&&sifre1.isNotEmpty()&&sifre2.isNotEmpty()){
            if (!sifre1.equals(sifre2)){
                Toast.makeText(this, "Şifreler Aynı değil", Toast.LENGTH_SHORT).show()
            }
            else{
                val admin= Admin(name= name, pass = sifre1)
                val status= sqLiteHelper.insertAdmin(admin)
                if (status>-1){
                    Toast.makeText(this, "Admin Ekleme Başarılı.", Toast.LENGTH_SHORT).show()
                    clearEditText()
                }else{
                    Toast.makeText(this, "Admin Ekleme Başarısız.", Toast.LENGTH_SHORT).show()
                }
            }

        }else{
            Toast.makeText(this, "Lütfen Boş Alanları Doldurun.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearEditText() {
        edName.text.clear()
        edSifre.text.clear()
        edSifre1.text.clear()
        edName.requestFocus()
    }
}