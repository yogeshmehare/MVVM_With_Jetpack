package com.yogesh.retrofitkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yogesh.retrofitkotlin.dataStore.view.DataStoreActivity
import com.yogesh.retrofitkotlin.databinding.ActivityMainBinding
import com.yogesh.retrofitkotlin.dependencyInjection.view.DependencyInjectionActivity
import com.yogesh.retrofitkotlin.listAdapter.view.QuotesListAdapterActivity
import com.yogesh.retrofitkotlin.quotesAppWithRetrofit.view.QuotesActivity
import com.yogesh.retrofitkotlin.roomDataBase.view.UserSignUpActivity
import com.yogesh.retrofitkotlin.workManager.WorkManagerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var appArrayList: ArrayList<MyAppModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)

        gridView = findViewById(R.id.gridView)
        appArrayList = ArrayList()

        setAppArrayListData()

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
                2 -> {
                    val roomDatabaseIntent = Intent(this, UserSignUpActivity::class.java)
                    startActivity(roomDatabaseIntent)
                }
                3 -> {
                    val quotesListAdapterIntent = Intent(this, QuotesListAdapterActivity::class.java)
                    startActivity(quotesListAdapterIntent)
                }
                4 -> {
                    val dependencyInjectionIntent = Intent(this, DependencyInjectionActivity::class.java)
                    startActivity(dependencyInjectionIntent)
                }
                5 -> {
                    val workmanagerIntent = Intent(this, WorkManagerActivity::class.java)
                    startActivity(workmanagerIntent)
                }
            }
        }


        binding.logoUrl = "https://docs.google.com/uc?export=download&id=1ll4ifNyUOTmTITdTnJ7w172DHnEpY_fa"
    }

    private fun setAppArrayListData() {
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[0], R.drawable.whatsapp))
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[1], R.drawable.whatsapp))
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[2], R.drawable.whatsapp))
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[3], R.drawable.whatsapp))
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[4], R.drawable.whatsapp))
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[5], R.drawable.whatsapp))
        appArrayList.add(MyAppModel(resources.getStringArray(R.array.android_concepts)[6], R.drawable.whatsapp))
    }


}

