package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bignerdranch.android.crime.Crime

// This annotation marks the class as a Room Database. It includes a list of entities associated with this database and its version.
// In this case, the only entity is `Crime::class` and the database version is set to 1.
@Database(entities = [Crime::class], version = 1)
// This annotation tells Room to use the specified TypeConverters class to convert between non-primitive types and database-compatible types.
// `CrimeTypeConverters::class` is being used to convert types that aren't natively supported by SQLite.
@TypeConverters(CrimeTypeConverters::class)
// The abstract class `CrimeDatabase` extends `RoomDatabase`, making it part of Room's database infrastructure.
// This class will be used to create the database instance and provide DAOs.
abstract class CrimeDatabase : RoomDatabase() {
    // This abstract method has no body and returns an instance of `CrimeDao`.
    // Room will generate the implementation for this method, allowing you to access the `CrimeDao` functions to interact with the database.
    abstract fun crimeDao(): CrimeDao
}