package com.yogesh.retrofitkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yogesh.retrofitkotlin.dataStore.view.DataStoreActivity
import com.yogesh.retrofitkotlin.databinding.ActivityMainBinding
import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.view.QuotesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)

        gridView = findViewById(R.id.gridView)
        val appArrayList: ArrayList<MyAppModel> = ArrayList()

        appArrayList.add(MyAppModel("Retrofit", R.drawable.whatsapp))
        appArrayList.add(MyAppModel("Data Store", R.drawable.whatsapp))
        appArrayList.add(MyAppModel("C++", R.drawable.whatsapp))
        appArrayList.add(MyAppModel("Python", R.drawable.whatsapp))
        appArrayList.add(MyAppModel("Javascript", R.drawable.whatsapp))
        appArrayList.add(MyAppModel("DSA", R.drawable.whatsapp))

        val adapter = AppGridAdapter(this, appArrayList)
        gridView.adapter = adapter

        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val quotesIntent: Intent = Intent(this, QuotesActivity::class.java).apply {
                        putExtra("timePlz", 4000)
                    }
                    startActivity(quotesIntent)
                }
                1 -> {
                    val dataStoreIntent = Intent(this, DataStoreActivity::class.java)
                    startActivity(dataStoreIntent)
                }
            }
        }
    }


}