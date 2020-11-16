package com.pstorli.bestmoviedb

import android.util.Log
import com.pstorli.bestmoviedb.Consts.TAG
import com.pstorli.bestmoviedb.Consts.TMDB_API_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BestMovieDBUnitTests {

    // Some test data.
    val GENRES = " [28,12,16,18,14,36 ]"

    val httpClient      = OkHttpClient.Builder().build()

    val testRequest     = Request.Builder().url(TMDB_API_URL).build()

    private fun OkHttpTest (): String
    {
        val response: okhttp3.Response = httpClient.newCall(testRequest).execute()
        val responseString = response.body?.string()?:""

        return responseString
    }

    @Test
    fun dbTests() {
        OkHttpTest ()
    }

    @Test
    fun extensionTests() {
        val result = GENRES.trimBraces()
        val msg = "Before: $GENRES \nafter $result"
        msg.logInfo()
        Log.i(TAG, msg)

    }
}