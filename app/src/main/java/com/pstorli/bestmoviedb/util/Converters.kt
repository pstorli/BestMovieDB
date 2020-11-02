package com.pstorli.bestmoviedb.util

import androidx.room.TypeConverter

class Converters
{
    /**
     * Pass in a list of genres ids (List<Int>),
     * return a comma separated String
     */
    @TypeConverter
    fun fromGenres(value: List<Int>): String {
        // list<Int> to string.
        return value.joinToString { it -> "\'${it}\'" }
    }

    /**
     * Pass in a json string of genre ids,
     * return a List of genre ids List<Int>
     */
    @TypeConverter
    fun toGenres(value: String): List<Int> {
        // String to List<Int>
        return value.split(",", " ").map(String::toInt)
    }
}