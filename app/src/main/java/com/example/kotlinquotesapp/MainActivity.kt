package com.example.kotlinquotesapp

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

        onPrevious.setOnClickListener{
            setQuote(mainViewModel.previousQuote())
        }

        onNext.setOnClickListener{
            setQuote(mainViewModel.nextQuote())
        }

        onShare.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
            startActivity(intent)
        }
    }
    fun setQuote(quote: Quote){
        quotes.text = quote.text
        quote_author.text = quote.author
    }

}