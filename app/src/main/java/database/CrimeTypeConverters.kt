package database

import androidx.room.TypeConverter
import java.util.Date

// This class is used by Room to convert between types that are not natively supported in the database and their corresponding
// representation in the database, which are compatible types. In this case, it's converting between `Date` objects and `Long` values.
class CrimeTypeConverters {
    // This function converts a `Date` object to a `Long` value representing its timestamp (the number of milliseconds since January 1, 1970, 00:00:00 GMT).
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    // This function converts a `Long` value representing a timestamp (the number of milliseconds since January 1, 1970, 00:00:00 GMT) to a `Date` object.
    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
}