package com.yogesh.retrofitkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.view.QuotesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesIntent:Intent = Intent(this, QuotesActivity::class.java).apply {
            putExtra("timePlz",4000)
        }
        startActivity(quotesIntent)
    }


}