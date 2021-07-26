package com.example.dependtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gytestlib.Depend

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Depend.getTest()
    }
}