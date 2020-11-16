package com.pstorli.bestmoviedb

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
        // Strip off blanks.
        val valueNoBlanks = value.replace(" ","")

        // Strip off single quotes.
        val valueNoSingleQuotes = valueNoBlanks.replace("'","")

        // String to List<Int>
        val result: List<Int> = valueNoSingleQuotes.split(",").map(String::toInt)

        return result
    }
}