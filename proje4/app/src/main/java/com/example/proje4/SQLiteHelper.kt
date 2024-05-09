package com.example.proje4

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context):SQLiteOpenHelper(context ,DATABASENAME,null,DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION=1
        private const val  DATABASENAME = "urunler.db"
        private const val TBL_URUNLER= "tbl_urunler"
        private const val TBL_ADMIN="tbl_admin"
        private const val ID="id"
        private const val NAME="name"
        private const val Desc="desc"
        private  const val Pass="pass"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE "+ TBL_URUNLER+ "("+
                ID + " TEXT PRIMARY KEY," + NAME + " TEXT,"+
                Desc + " TEXT" +")")
        p0?.execSQL("CREATE TABLE $TBL_ADMIN ($ID INTEGER PRIMARY KEY, $NAME TEXT, $Pass TEXT)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TBL_URUNLER")
        p0!!.execSQL("DROP TABLE IF EXİSTS $TBL_ADMIN")
        onCreate(p0)
    }
    fun insertUrun(urunler: Urunler):Long{
        val db= this.writableDatabase

        val contentValues=ContentValues()
        contentValues.put(ID,urunler.id)
        contentValues.put(NAME,urunler.name)
        contentValues.put(Desc,urunler.desc)

        val success= db.insert(TBL_URUNLER,null,contentValues)
        db.close()
        return success
    }


    fun insertAdmin(admin: Admin):Long{
        val db= this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID,admin.id)
        contentValues.put(NAME,admin.name)
        contentValues.put(Pass,admin.pass)

        val success=db.insert(TBL_ADMIN,null,contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllUrunler():ArrayList<Urunler>{
        val urunlerList: ArrayList<Urunler> = ArrayList()
        val selectQuery= "SELECT * FROM $TBL_URUNLER"
        val db= this.readableDatabase
        val cursor: Cursor?
        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Long
        var name: String
        var desc :String
        if (cursor.moveToFirst()){
            do {
                id= cursor.getLong(cursor.getColumnIndex("id"))
                name=cursor.getString(cursor.getColumnIndex("name"))
                desc= cursor.getString(cursor.getColumnIndex("desc"))
                val urun= Urunler(id= id,name=name,desc=desc)
                urunlerList.add(urun)
            }while (cursor.moveToNext())
        }
        return urunlerList
    }
    fun deleteUrun(urunId: Long): Int {
        val db = this.writableDatabase
        return db.delete(TBL_URUNLER, "$ID=?", arrayOf(urunId.toString()))
    }
    @SuppressLint("Range")
    fun getAllAdmin(): ArrayList<Admin>{
        val adminList:ArrayList<Admin> = ArrayList()
        val selectQuery="SELECT $ID, $NAME,$Pass FROM $TBL_ADMIN"
        val db= this.readableDatabase
        val cursor:Cursor?
        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var pass: String
        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(cursor.getColumnIndex("id"))
                name=cursor.getString(cursor.getColumnIndex("name"))
                pass=cursor.getString(cursor.getColumnIndex("pass"))
                val admin =Admin(id=id ,name= name, pass = pass)
                adminList.add(admin)
            }while (cursor.moveToNext())
        }
        return adminList
    }



    @SuppressLint("Range")
    fun getUrunByBarkod(barkod: String): Urunler {

        val selectQuery="SELECT * FROM $TBL_URUNLER WHERE $ID = '$barkod'"
        val db=this.readableDatabase
        var urun: Urunler =Urunler()
        val cursor: Cursor
        try {
            cursor=db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return urun
        }
        var id: Long
        var name: String
        var desc: String
        if (cursor.moveToFirst()){
            do {
                id=cursor.getLong(cursor.getColumnIndex("id"))
                name=cursor.getString(cursor.getColumnIndex("name"))
                desc=cursor.getString(cursor.getColumnIndex("desc"))
                var urun=Urunler(id=id,name=name,desc=desc)
                return urun

            }while (cursor.moveToNext())
        }
        return urun
    }
    fun deleteAdmin(adminId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TBL_ADMIN, "$ID = ?", arrayOf(adminId.toString()))
    }

}