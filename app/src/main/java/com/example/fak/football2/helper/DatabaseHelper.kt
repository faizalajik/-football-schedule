package com.example.fak.football2.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.fak.football2.util.FavoriteConstan
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper
    (context,"favorite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteConstan.TABLE_MATCH,true,
            FavoriteConstan.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteConstan.MATCH_ID to TEXT + UNIQUE,
            FavoriteConstan.MATCH_DATE to TEXT,
            FavoriteConstan.HOME_ID to TEXT + UNIQUE,
            FavoriteConstan.AWAY_ID to TEXT + UNIQUE,
            FavoriteConstan.HOME_NAME to TEXT,
            FavoriteConstan.AWAY_NAME to TEXT,
            FavoriteConstan.HOME_GOAL to TEXT,
            FavoriteConstan.AWAY_GOAL to TEXT,
            FavoriteConstan.HOME_SHOT to TEXT,
            FavoriteConstan.AWAY_SHOT to TEXT,
            FavoriteConstan.HOME_KEEPER to TEXT,
            FavoriteConstan.AWAY_KEEPER to TEXT,
            FavoriteConstan.HOME_DEFENDER to TEXT,
            FavoriteConstan.AWAY_DEFENDER to TEXT,
            FavoriteConstan.HOME_MIDFIELD to TEXT,
            FavoriteConstan.AWAY_MIDFIELD to TEXT,
            FavoriteConstan.HOME_FORWARD to TEXT,
            FavoriteConstan.AWAY_FORWARD to TEXT,
            FavoriteConstan.HOME_SUBTITUTES to TEXT,
            FavoriteConstan.AWAY_SUBTITUTES to TEXT
            )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.dropTable(FavoriteConstan.TABLE_MATCH,true)
    }
}