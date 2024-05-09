package com.example.proje4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UrunGoster : AppCompatActivity() {
    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var btn_goster111: Button
    private lateinit var btn_cikis11: Button

    private lateinit var urunAdapter: UrunAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urun_goster)
        initView()


        recyclerView = findViewById(R.id.recyclerviewUrun)
        urunAdapter = UrunAdapter(sqLiteHelper.getAllUrunler(), ::onDeleteButtonClick)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = urunAdapter

        btn_goster111.setOnClickListener {
            urunAdapter.updateData(sqLiteHelper.getAllUrunler())
        }
        btn_cikis11.setOnClickListener {
            var intent1 = Intent(this, SecimTablosu::class.java)
            startActivity(intent1)
        }
    }

    private fun onDeleteButtonClick(urun: Urunler) {
        val silinecekSira = sqLiteHelper.deleteUrun(urun.id)
        if (silinecekSira > 0) {
            Toast.makeText(this, "Silme Başarılı", Toast.LENGTH_SHORT).show()
            urunAdapter.updateData(sqLiteHelper.getAllUrunler())
        } else {
            Toast.makeText(this, "Silme Başarısız", Toast.LENGTH_SHORT).show()
        }
    }




    private fun initView() {
        btn_goster111 = findViewById(R.id.buttonGoster111)
        btn_cikis11 = findViewById(R.id.buttonCikis111)
        sqLiteHelper= SQLiteHelper(this)
    }
}
