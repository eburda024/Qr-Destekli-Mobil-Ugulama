package com.example.proje4
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminGoster : AppCompatActivity() {
    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var btn_gosterAdmin: Button
    private lateinit var btn_cikisAdmin: Button

    private lateinit var adminAdapter: AdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_goster)
        initView()

        recyclerView = findViewById(R.id.recyclerviewAdmin)
        adminAdapter = AdminAdapter(sqLiteHelper.getAllAdmin(), ::onDeleteButtonClick)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adminAdapter

        btn_gosterAdmin.setOnClickListener {
            adminAdapter.updateData(sqLiteHelper.getAllAdmin())
        }
        btn_cikisAdmin.setOnClickListener {
            var intent1 = Intent(this, SecimTablosu::class.java)
            startActivity(intent1)
        }
    }

    private fun onDeleteButtonClick(admin: Admin) {
        val silinecekSira = sqLiteHelper.deleteAdmin(admin.id)
        if (silinecekSira > 0) {
            Toast.makeText(this, "Silme Başarılı", Toast.LENGTH_SHORT).show()
            adminAdapter.updateData(sqLiteHelper.getAllAdmin())
        } else {
            Toast.makeText(this, "Silme Başarısız", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        btn_gosterAdmin = findViewById(R.id.buttonGosterAdmin)
        btn_cikisAdmin = findViewById(R.id.buttonCikisAdmin)
        sqLiteHelper = SQLiteHelper(this)
    }
}
