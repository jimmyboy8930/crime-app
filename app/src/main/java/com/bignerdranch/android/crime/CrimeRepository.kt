package com.bignerdranch.android.crime

import android.content.Context
import androidx.room.Room
import database.CrimeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

private const val DATABASE_NAME = "crime-database"
class CrimeRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope){
    private val database:CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()
    //suspend fun getCrimes(): List<Crime> = database.crimeDao().getCrimes()
    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()

    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)
    fun updateCrime(crime: Crime) {
        coroutineScope.launch {
            database.crimeDao().updateCrime(crime)
        }
    }

    suspend fun addCrime(crime: Crime){
        database.crimeDao().addCrime(crime)
    }
    companion object{
        private var INSTANCE: CrimeRepository? = null

        // CrimeRepository needs to be singleton
        fun initialize(context : Context){
            if(INSTANCE == null){
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get() : CrimeRepository{
            return INSTANCE ?:
            throw java.lang.IllegalStateException("CrimeRepository must be initialized")
        }
    }
}