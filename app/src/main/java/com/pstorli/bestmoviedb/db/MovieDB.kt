package com.pstorli.bestmoviedb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pstorli.bestmoviedb.model.Genre
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.Consts.MOVIE_DB_NAME
import com.pstorli.bestmoviedb.Converters

@Database (
    entities = [Movie::class, Movies::class, Genre::class, Genres::class], version = 1, exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MovieDB () : RoomDatabase (){
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var INSTANCE: MovieDB? = null
        private val LOCK = Any()

        /**
         * Get an instance of the db. If already created, return that.
         */
        fun getInstance (context: Context): MovieDB? = INSTANCE ?: synchronized(LOCK)
        {
            return INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder (context.getApplicationContext(), MovieDB::class.java, MOVIE_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}