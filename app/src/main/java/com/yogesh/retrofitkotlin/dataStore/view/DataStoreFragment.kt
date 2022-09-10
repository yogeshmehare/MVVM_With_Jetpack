package com.yogesh.retrofitkotlin.dataStore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.dataStore.model.MyDataStore
import com.yogesh.retrofitkotlin.dataStore.module.DataStoreViewModelFactory
import com.yogesh.retrofitkotlin.dataStore.viewmodel.DataStoreViewModel
import com.yogesh.retrofitkotlin.databinding.FragmentDataStoreBinding
import dagger.hilt.android.AndroidEntryPoint

class DataStoreFragment : Fragment() {

    private lateinit var binding : FragmentDataStoreBinding
    private lateinit var viewModel:DataStoreViewModel

    private lateinit var dataStore: MyDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_data_store,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStore = MyDataStore(context = requireContext())
        viewModel = ViewModelProvider(this,DataStoreViewModelFactory(dataStore)).get(DataStoreViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

    }

}