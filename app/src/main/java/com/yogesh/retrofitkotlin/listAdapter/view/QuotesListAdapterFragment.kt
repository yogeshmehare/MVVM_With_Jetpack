package com.yogesh.retrofitkotlin.listAdapter.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.databinding.FragmentQuotesListAdapterBinding
import com.yogesh.retrofitkotlin.listAdapter.model.QuotesRepository
import com.yogesh.retrofitkotlin.listAdapter.model.RetrofitHelper
import com.yogesh.retrofitkotlin.listAdapter.model.adaptter.QuotesListAdapter
import com.yogesh.retrofitkotlin.listAdapter.viewmodel.QuotesListAdapterViewModel
import com.yogesh.retrofitkotlin.listAdapter.viewmodel.QuotesListAdapterViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuotesListAdapterFragment : Fragment() {

    private lateinit var viewModel: QuotesListAdapterViewModel
    private lateinit var binding: FragmentQuotesListAdapterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quotes_list_adapter,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitHelper.getInstance()
        val repository = QuotesRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            factory = QuotesListAdapterViewModelFactory(repository)
        )[QuotesListAdapterViewModel::class.java]



        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->

            if (quotes != null) {
                Log.d("Retrofit", quotes.results.toString())

                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                val adapter = QuotesListAdapter(requireContext())
                binding.recyclerView.adapter = adapter

                val quotes1 = quotes.results.subList(6, 11)
                val quotes2 = quotes.results.subList(6, 7) + quotes.results.subList(9, 15)

                adapter.submitList(quotes1)

                viewLifecycleOwner.lifecycleScope.launch {
                    delay(5000)
                    adapter.submitList(quotes2)
                }
            }

        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

}