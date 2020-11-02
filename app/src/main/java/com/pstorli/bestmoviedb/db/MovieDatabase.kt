package com.pstorli.bestmoviedb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies

@Database (
    entities = [Movie::class, Movies::class], version = 1, exportSchema = false
)

@TypeConverters(com.pstorli.bestmoviedb.util.Converters::class)
abstract class MovieDatabase : RoomDatabase (){
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            MovieDatabase::class.java, "movie_database").build()
    }
}