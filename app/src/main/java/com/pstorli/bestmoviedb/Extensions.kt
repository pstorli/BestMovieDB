/*
 * Add our extensions here.
 */

package com.pstorli.bestmoviedb

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.util.Consts
import com.pstorli.bestmoviedb.util.Consts.TMDB_PHOTO_URL

/**
 * Log an error message.
 */
fun String.logError()
{
  Log.e (Consts.TAG, this)
}

/**
 * Log an info message.
 */
fun String.logInfo()
{
  Log.i (Consts.TAG, this)
}

/**
 * Load an image.
 */
fun ImageView.loadImage (movie: Movie, activity: Activity?)
{
    try {
        // Get res id.
        val resId: Int = getResources().getIdentifier(
          movie.poster_path,
          "drawable",
          activity?.getPackageName() ?: "com.pstorli.bestmoviedb"
        )

        // /////////////////////////////////////////////////////////////////////////////////////////
        // Load local image?
        // /////////////////////////////////////////////////////////////////////////////////////////
        if (resId > 0) {
          // /////////////////////////////////////////////////////////////////////////////////
          // Load image from resId in drawabe folder.
          // /////////////////////////////////////////////////////////////////////////////////
          setImageResource (resId)
        }

        // /////////////////////////////////////////////////////////////////////////////////////////
        // Remote image.
        // /////////////////////////////////////////////////////////////////////////////////////////
        else {

          // /////////////////////////////////////////////////////////////////////////////////
          // Load remote image using glide.
          // /////////////////////////////////////////////////////////////////////////////////
          val photoURL = TMDB_PHOTO_URL+movie.poster_path

          Glide.with(this)
                .load(photoURL)
                .centerCrop()
                .placeholder(R.drawable.a2001)
                .error(R.drawable.ic_error)
                .fallback(R.drawable.ic_launcher)
                .into(this)
        }
    }
    catch (ex: Exception) {
        ex.toString().logError()
    }
}
