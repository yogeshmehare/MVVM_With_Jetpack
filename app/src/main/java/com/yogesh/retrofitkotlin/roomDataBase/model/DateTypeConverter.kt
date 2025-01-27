package com.yogesh.retrofitkotlin.roomDataBase.model

import androidx.room.TypeConverter
import java.util.*

object DateTypeConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
