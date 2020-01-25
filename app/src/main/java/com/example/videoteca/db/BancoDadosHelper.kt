package com.example.videoteca.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.videoteca.db.ConstantDb.MOVIES_DB_NAME
import com.example.videoteca.db.ConstantDb.MOVIES_DB_TABLE
import org.jetbrains.anko.db.*

class BancoDadosHelper(context: Context) :ManagedSQLiteOpenHelper(ctx = context, name = MOVIES_DB_NAME,  version = 1) {
    private val scriptSQLCreate = arrayOf(
        "INSERT INTO $MOVIES_DB_TABLE VALUES(1,'O Resgate do Soldado Ryan',800200300);",
        "INSERT INTO $MOVIES_DB_TABLE VALUES(2,'Forest Gump',800200300);"
    )

    //singleton da classe
    companion object {
        private var instance: BancoDadosHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): BancoDadosHelper {
            if (instance == null) instance = BancoDadosHelper(ctx.getApplicationContext())
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MOVIES_DB_TABLE, true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "title" to TEXT,
            "release" to TEXT
        )

        scriptSQLCreate.forEach {sql -> db.execSQL(sql)}
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MOVIES_DB_TABLE, true)
        onCreate(db)
    }
}

val Context.database: BancoDadosHelper
get() = BancoDadosHelper.getInstance(getApplicationContext())
