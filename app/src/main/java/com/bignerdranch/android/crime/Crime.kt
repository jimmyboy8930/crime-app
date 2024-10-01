package com.bignerdranch.android.crime

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID
// @Entity representing the table in  database.
// Each property in the class would be annotated with @ColumnInfo,
// and the primary key with @PrimaryKey
@Entity
data class Crime(
    @PrimaryKey val id: UUID,
    val title : String,
    val date : Date,
    var isSolved : Boolean
)
