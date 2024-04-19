package com.example.andoridtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.andoridtask.internetconnection.ConnectivityLiveData
import com.example.andoridtask.paging.QuotePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var internet_checker: TextView
    lateinit var viewModel: QuoteViewModel
    lateinit var quotePagingAdapter: QuotePagingAdapter
    lateinit var connectivityLiveData: ConnectivityLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.quoteList)
        internet_checker = findViewById(R.id.internet_checker)
        quotePagingAdapter= QuotePagingAdapter()
        viewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        connectivityLiveData= ConnectivityLiveData(application)
        connectivityLiveData.observe(this, Observer { isAvailable ->
            if(isAvailable) {
                internet_checker.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            else
            {
                internet_checker.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        })
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = quotePagingAdapter
        viewModel.list.observe(this, Observer {
            Log.d("dsdads",it.toString())
            quotePagingAdapter.submitData(lifecycle,it)
        })
    }
}