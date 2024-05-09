package com.example.proje4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlin.math.log

class SecimTablosu : AppCompatActivity() {

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_secim_tablosu)
        sqLiteHelper= SQLiteHelper(this)
        var adminName= intent.getStringExtra("adminName")
        var adminAd= findViewById<TextView>(R.id.tvAdmin)
        var intentIntegrator= IntentIntegrator(this)

        adminAd.text=adminName.toString()
        var btnAdminEkle= findViewById<Button>(R.id.btnAdminEkle)
        var btnUrunlerSayfasi=findViewById<Button>(R.id.btn_urunlerSayfasi)
        var btnBarkodOkut= findViewById<Button>(R.id.btnKodOkut)
        btnUrunlerSayfasi.setOnClickListener {
            var intentUrunler= Intent(this,UrunEkle::class.java)
            startActivity(intentUrunler)
        }
        btnAdminEkle.setOnClickListener {
            var intentAdminEkle= Intent(this,AdminEkle::class.java)
            startActivity(intentAdminEkle)
        }
        btnBarkodOkut.setOnClickListener {
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setPrompt("Barkod Okutun...")
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            intentIntegrator.initiateScan()

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var textIsim = findViewById<TextView>(R.id.textIsim)
        var textDetay = findViewById<TextView>(R.id.textDetay)
        var adminAd = findViewById<TextView>(R.id.tvAdmin)
        val intentSonuc: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (intentSonuc != null) {
            val barkodVerisi = intentSonuc.contents
            if (barkodVerisi != null) {
                val urun = sqLiteHelper.getUrunByBarkod(barkodVerisi)
                if (urun != null) {
                    Log.e("DEBUG", "Ürün Bulundu: $urun")
                    adminAd.text = "ID: ${urun.id}"

                    if (urun.name != null) {
                        textIsim.text = "Adı: ${urun.name}"
                    } else {
                        textIsim.text = "Adı: Belirtilmemiş"
                    }
                    if (urun.desc != null) {
                        textDetay.text = "Açıklama: ${urun.desc}"
                    } else {
                        textDetay.text = "Açıklama: Belirtilmemiş"
                    }

                } else {
                    Log.e("debug", "ürün yok1: $urun .")
                    Toast.makeText(this, "Böyle bir ürün yok!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }




}