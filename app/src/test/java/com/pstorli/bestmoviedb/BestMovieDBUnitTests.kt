package com.pstorli.bestmoviedb

import android.util.Log
import com.pstorli.bestmoviedb.util.Consts.TAG
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BestMovieDBUnitTests {

    // Some test data.
    val GENRES = " [28,12,16,18,14,36 ]"

    @Test
    fun extensionTests() {
        val result = GENRES.trimBraces()
        val msg = "Before: $GENRES \nafter $result"
        msg.logInfo()
        Log.i(TAG, msg)

    }
}