package com.bignerdranch.android.crime

import android.app.Application

class CriminalIntentApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}