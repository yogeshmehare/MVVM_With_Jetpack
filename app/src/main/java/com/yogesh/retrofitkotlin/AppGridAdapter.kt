package com.yogesh.retrofitkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


data class MyAppModel(val appName:String, val appImage:Int)

class AppGridAdapter(context: Context, appArrayList: ArrayList<MyAppModel>) :
    ArrayAdapter<MyAppModel?>(context, 0, appArrayList as List<MyAppModel?>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        }

        val myApp: MyAppModel? = getItem(position)
        val appName = listitemView!!.findViewById<TextView>(R.id.appName)
        val appImage = listitemView.findViewById<ImageView>(R.id.appImage)

        appName.text = myApp?.appName
        appImage.setImageResource(myApp?.appImage!!)
        return listitemView
    }
}
