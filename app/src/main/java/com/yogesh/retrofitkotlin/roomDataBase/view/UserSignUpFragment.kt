package com.yogesh.retrofitkotlin.roomDataBase.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.databinding.FragmentUserSignupBinding
import com.yogesh.retrofitkotlin.roomDataBase.model.UserDatabase
import com.yogesh.retrofitkotlin.roomDataBase.model.UserEntity
import com.yogesh.retrofitkotlin.roomDataBase.model.adapter.UserDataAdapter
import com.yogesh.retrofitkotlin.roomDataBase.viewmodel.UserSignUpViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class UserSignUpFragment : Fragment() {

    private lateinit var viewModel: UserSignUpViewModel
    private lateinit var binding: FragmentUserSignupBinding
    lateinit var myCalendar : MyCalender

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_signup,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
        binding.mViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext());
        binding.recyclerView.adapter = UserDataAdapter(requireContext(), ArrayList<UserEntity>().toList())

//        viewModel.userList.observe(viewLifecycleOwner) {
//            val t = it
//            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext());
//            binding.recyclerView.adapter = UserDataAdapter(requireContext(),it)
//        }

        val mDate = Date()
        myCalendar = MyCalender(mDate.date,mDate.month,mDate.year)
        val date =
            OnDateSetListener { view, year, month, day ->
                myCalendar = MyCalender(day,month, year)
                updateLabel()
            }


        binding.editTextUserBirthDateSelector.editText?.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                date,myCalendar.year,myCalendar.month,myCalendar.date
            ).show()
            Log.d("UserDB","HIHI")
        }

        val database = UserDatabase.getDBInstance(activity?.applicationContext!!)
        viewModel.userDatabase = database

        database.userDao().getUsers().observe(this.viewLifecycleOwner) {
            val t = it
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext());
            binding.recyclerView.adapter = UserDataAdapter(requireContext(),it)
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        viewModel.userBirthDate.value = dateFormat.format(Date(myCalendar.year,myCalendar.month,myCalendar.date))
        binding.editTextUserBirthDateSelector.editText?.setText(dateFormat.format(Date(myCalendar.year,myCalendar.month,myCalendar.date)))
    }

}

data class MyCalender(val date:Int,val month:Int,val year:Int)