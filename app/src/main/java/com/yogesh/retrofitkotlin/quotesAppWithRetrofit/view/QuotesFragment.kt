package com.yogesh.retrofitkotlin.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yogesh.retrofitkotlin.R
import com.yogesh.retrofitkotlin.databinding.FragmentQuotesBinding
import com.yogesh.retrofitkotlin.model.`interface`.RetrofitService
import com.yogesh.retrofitkotlin.model.models.MainRepository
import com.yogesh.retrofitkotlin.viewmodel.QuotesViewModel
import com.yogesh.retrofitkotlin.viewmodel.QuotesViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class QuotesFragment : Fragment() {

    private lateinit var viewModel: QuotesViewModel
    private lateinit var binding : FragmentQuotesBinding
    var mColors = arrayOf(
        "FFEBEE", "FFCDD2", "EF9A9A", "E57373", "EF5350", "F44336", "E53935",  //reds
        "D32F2F", "C62828", "B71C1C", "FF8A80", "FF5252", "FF1744", "D50000",
        "FCE4EC", "F8BBD0", "F48FB1", "F06292", "EC407A", "E91E63", "D81B60",  //pinks
        "C2185B", "AD1457", "880E4F", "FF80AB", "FF4081", "F50057", "C51162",
        "F3E5F5", "E1BEE7", "CE93D8", "BA68C8", "AB47BC", "9C27B0", "8E24AA",  //purples
        "7B1FA2", "6A1B9A", "4A148C", "EA80FC", "E040FB", "D500F9", "AA00FF",
        "EDE7F6", "D1C4E9", "B39DDB", "9575CD", "7E57C2", "673AB7", "5E35B1",  //deep purples
        "512DA8", "4527A0", "311B92", "B388FF", "7C4DFF", "651FFF", "6200EA",
        "E8EAF6", "C5CAE9", "9FA8DA", "7986CB", "5C6BC0", "3F51B5", "3949AB",  //indigo
        "303F9F", "283593", "1A237E", "8C9EFF", "536DFE", "3D5AFE", "304FFE",
        "E3F2FD", "BBDEFB", "90CAF9", "64B5F6", "42A5F5", "2196F3", "1E88E5",  //blue
        "1976D2", "1565C0", "0D47A1", "82B1FF", "448AFF", "2979FF", "2962FF",
        "E1F5FE", "B3E5FC", "81D4fA", "4fC3F7", "29B6FC", "03A9F4", "039BE5",  //light blue
        "0288D1", "0277BD", "01579B", "80D8FF", "40C4FF", "00B0FF", "0091EA",
        "E0F7FA", "B2EBF2", "80DEEA", "4DD0E1", "26C6DA", "00BCD4", "00ACC1",  //cyan
        "0097A7", "00838F", "006064", "84FFFF", "18FFFF", "00E5FF", "00B8D4",
        "E0F2F1", "B2DFDB", "80CBC4", "4DB6AC", "26A69A", "009688", "00897B",  //teal
        "00796B", "00695C", "004D40", "A7FFEB", "64FFDA", "1DE9B6", "00BFA5",
        "E8F5E9", "C8E6C9", "A5D6A7", "81C784", "66BB6A", "4CAF50", "43A047",  //green
        "388E3C", "2E7D32", "1B5E20", "B9F6CA", "69F0AE", "00E676", "00C853",
        "F1F8E9", "DCEDC8", "C5E1A5", "AED581", "9CCC65", "8BC34A", "7CB342",  //light green
        "689F38", "558B2F", "33691E", "CCFF90", "B2FF59", "76FF03", "64DD17",
        "F9FBE7", "F0F4C3", "E6EE9C", "DCE775", "D4E157", "CDDC39", "C0CA33",  //lime
        "A4B42B", "9E9D24", "827717", "F4FF81", "EEFF41", "C6FF00", "AEEA00",
        "FFFDE7", "FFF9C4", "FFF590", "FFF176", "FFEE58", "FFEB3B", "FDD835",  //yellow
        "FBC02D", "F9A825", "F57F17", "FFFF82", "FFFF00", "FFEA00", "FFD600",
        "FFF8E1", "FFECB3", "FFE082", "FFD54F", "FFCA28", "FFC107", "FFB300",  //amber
        "FFA000", "FF8F00", "FF6F00", "FFE57F", "FFD740", "FFC400", "FFAB00",
        "FFF3E0", "FFE0B2", "FFCC80", "FFB74D", "FFA726", "FF9800", "FB8C00",  //orange
        "F57C00", "EF6C00", "E65100", "FFD180", "FFAB40", "FF9100", "FF6D00",
        "FBE9A7", "FFCCBC", "FFAB91", "FF8A65", "FF7043", "FF5722", "F4511E",  //deep orange
        "E64A19", "D84315", "BF360C", "FF9E80", "FF6E40", "FF3D00", "DD2600",
        "EFEBE9", "D7CCC8", "BCAAA4", "A1887F", "8D6E63", "795548", "6D4C41",  //brown
        "5D4037", "4E342E", "3E2723",
        "FAFAFA", "F5F5F5", "EEEEEE", "E0E0E0", "BDBDBD", "9E9E9E", "757575",  //grey
        "616161", "424242", "212121",
        "ECEFF1", "CFD8DC", "B0BBC5", "90A4AE", "78909C", "607D8B", "546E7A",  //blue grey
        "455A64", "37474F", "263238"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_quotes,container,false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val timeToPostpone = requireActivity().intent.getIntExtra("timePlz",0)
//        val handler = Handler(Looper.myLooper()!!)
//        handler.postDelayed({
//            Toast.makeText(context,"IntentExtra Received", Toast.LENGTH_LONG).show()
//        }, timeToPostpone.toLong())

        val retrofitService = RetrofitService.getInstance()
        val repository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(this, factory = QuotesViewModelFactory(repository))[QuotesViewModel::class.java]


        binding.next.setOnClickListener {
            viewModel.IncrementQuoteIndex();
            setQuoteTextWithQuoteIndex();
        }

        binding.prev.setOnClickListener {
            viewModel.DecrementQuoteIndex()
            setQuoteTextWithQuoteIndex();
        }

        setQuoteText()
        setQuoteTextColor()

        viewModel.quotesList.observe(viewLifecycleOwner){
            if (it.isNotEmpty()) {
                binding.quote.text = it[viewModel.quoteIndex].quote
                binding.author.text = it[viewModel.quoteIndex].author
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                Log.d("Retrofit", viewModel.quotesList.value?.size.toString())
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.getAllMovies()


//        val quotesApi = RetrofitService.getInstance()
//        // launching a new coroutine
//        GlobalScope.launch {
//            val result = quotesApi.getQuoteList()
//            Log.d("Retrofit1: ", result.body().toString())
//        }

    }

    private fun setQuoteText() {
        binding.quote.text = "Life is Hell, Work hard till the day you become capable to turn this hell to Heaven"
        binding.author.text = "Yogesh"
    }

    private fun setQuoteTextWithQuoteIndex() {
        setQuoteTextColor()
        binding.quote.text = viewModel.quotesList.value?.get(viewModel.quoteIndex)?.quote
        binding.author.text = viewModel.quotesList.value?.get(viewModel.quoteIndex)?.author
    }

    fun setQuoteTextColor(){
        binding.quote.setTextColor(Color.parseColor ("#"+mColors[Random().nextInt(254)]))
        binding.author.setTextColor(Color.parseColor ("#"+mColors[Random().nextInt(254)]))
    }

}