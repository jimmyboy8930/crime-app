package com.bignerdranch.android.crime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }




//    overide fun onViewCreated (view: ){
//        binding.apply{
//            crime = crime.copy(title = text.toString())
//        }
//        crimeDate.setText(crime.title)
//        crimeDate.setOnClickListener{
//            crimeDate.setText()
//        }
//        crimeSolved.setOnCheckedChangeListener {_, isChecked ->
//            crime = crime.copy(isSolved = isChecked)
//        }
//    }
}