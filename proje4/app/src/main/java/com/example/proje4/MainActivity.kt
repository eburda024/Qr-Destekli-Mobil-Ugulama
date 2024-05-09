package com.example.proje4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edpass: EditText
    private lateinit var btnGiris: Button


    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        var sayac = 0
        sqLiteHelper = SQLiteHelper(this)
        btnGiris.setOnClickListener {
            sayac++
            if (sayac == 6) {
                intent = Intent(this, AdminEkle::class.java)
                startActivity(intent)
            }
            giris()
        }
    }

    private fun initView() {
        edName = findViewById(R.id.edAdminAdi)
        edpass = findViewById(R.id.edAdminSifre)
        btnGiris = findViewById(R.id.btn_giris111)
    }

    private fun giris() {
        val name = edName.text.toString()
        val pass = edpass.text.toString()

        if (name.isNotEmpty() && pass.isNotEmpty()) {
            val adminList = sqLiteHelper.getAllAdmin()
            var girisBasariliMi = false
            for (admin in adminList) {
                if (admin.name.equals(name) && admin.pass.equals(pass)) {
                    girisBasariliMi = true
                    break
                }
            }

            if (girisBasariliMi) {
                Toast.makeText(this, "Giriş Başarılı..", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SecimTablosu::class.java)
                intent.putExtra("adminName", name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Şifre veya Ad Yanlış", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Lütfen Geçerli Bilgiler Giriniz.", Toast.LENGTH_SHORT).show()
        }
    }

}