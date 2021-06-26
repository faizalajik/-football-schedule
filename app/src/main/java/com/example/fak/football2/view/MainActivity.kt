package com.example.fak.football2.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fak.football2.R
import com.example.fak.football2.R.id.*
import com.example.fak.football2.view.fragment.FavoriteFragment
import com.example.fak.football2.view.fragment.LastFragment
import com.example.fak.football2.view.fragment.NextFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.con,
            LastFragment()
        )
            .commit()

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                prev -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.con,
                        LastFragment()
                    )
                        .commit()
                }
                next -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.con,
                        NextFragment()
                    )
                        .commit()
                }
                favorites -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.con,
                        FavoriteFragment()
                    )
                        .commit()
                }
            }
            true
        }

    }
}

