package com.yogesh.retrofitkotlin.roomDataBase.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.roomDataBase.model.UserEntity
import com.yogesh.retrofitkotlin.databinding.UserListItemBinding

class UserDataAdapter constructor(val context: Context?,val userList: List<UserEntity>) :
    RecyclerView.Adapter<UserDataAdapter.UserViewHolder>() {

    private var mClickListener: ItemClickListener? = null

    override fun getItemCount(): Int = userList.size+1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding : UserListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.user_list_item, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if(position==0){
            holder.userName.text = "Name"
            holder.userEmail.text = "Email"
            holder.userBirthDate.text = "Birth Date"
        }else{
            holder.userName.text = userList[position-1].userName
            holder.userEmail.text = userList[position-1].userEmail
            holder.userBirthDate.text = userList[position-1].userBirthDate.toString()
        }

    }

    inner class UserViewHolder(var binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root),View.OnClickListener  {

        var userName: TextView = binding.name
        var userEmail: TextView = binding.email
        var userBirthDate: TextView = binding.birthDate

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(binding.root, position = absoluteAdapterPosition)
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}



//class MyRecyclerViewAdapter internal constructor(context: Context?, data: List<String>) :
//    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
//    private val mData: List<String>
//    private val mInflater: LayoutInflater
//    private var mClickListener: ItemClickListener? = null
//
//    // inflates the row layout from xml when needed
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view: View = mInflater.inflate(R.layout.recyclerview_row, parent, false)
//        return ViewHolder(view)
//    }
//
//    // binds the data to the TextView in each row
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val animal = mData[position]
//        holder.myTextView.text = animal
//    }
//
//    // total number of rows
//    override fun getItemCount(): Int {
//        return mData.size
//    }
//
//    // stores and recycles views as they are scrolled off screen
//    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
//        View.OnClickListener {
//        var myTextView: TextView
//        override fun onClick(view: View?) {
//            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
//        }
//
//        init {
//            myTextView = itemView.findViewById(R.id.tvAnimalName)
//            itemView.setOnClickListener(this)
//        }
//    }
//
//    // convenience method for getting data at click position
//    fun getItem(id: Int): String {
//        return mData[id]
//    }
//
//    // allows clicks events to be caught
//    fun setClickListener(itemClickListener: ItemClickListener?) {
//        mClickListener = itemClickListener
//    }
//
//    // parent activity will implement this method to respond to click events
//    interface ItemClickListener {
//        fun onItemClick(view: View?, position: Int)
//    }
//
//    // data is passed into the constructor
//    init {
//        mInflater = LayoutInflater.from(context)
//        mData = data
//    }
//}
