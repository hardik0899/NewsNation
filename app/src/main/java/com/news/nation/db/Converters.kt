package com.news.nation.db

import androidx.room.TypeConverter
import com.news.nation.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String = source.name

    @TypeConverter
    fun toSource(name : String) : Source = Source(name,name)
}