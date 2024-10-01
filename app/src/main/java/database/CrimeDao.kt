package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.crime.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*
// The DAO (CrimeDao) is an interface that provides methods for accessing the database.
// Methods that interact with the database are annotated with queries written in SQL.
// Room will generate the implementation of these methods.
// Defines an interface for the data access object (DAO). This interface will be used by Room to access your data.
@Dao
interface CrimeDao {

    // Defines a query that selects all columns from the 'crime' table.
    // The function is declared to return a Flow, which is a type from Kotlin's coroutines that
    // represents a stream of data that can emit multiple values sequentially, as opposed to a single value.
    // In this case, it will emit every time the data in the 'crime' table changes.
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>> // Note: Flow is part of Kotlin's coroutine library for handling asynchronous stream of data.

    // Defines a query to select a crime from the 'crime' table by its ID.
    // The function is marked with 'suspend', which means it's a suspending function and must be called from a coroutine or another suspending function.
    // This query expects an ID of type UUID and will return a single Crime object corresponding to that ID.
    @Query("SELECT * FROM crime WHERE id = (:id)")
    suspend fun getCrime(id: UUID): Crime

    @Update
    suspend fun updateCrime(crime: Crime)

    @Insert
    suspend fun addCrime(crime: Crime)
}
