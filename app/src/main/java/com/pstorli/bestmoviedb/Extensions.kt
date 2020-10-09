/*
 * Add our extensions here.
 */

package com.pstorli.bestmoviedb

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import com.pstorli.bestmoviedb.model.Movie
import com.squareup.picasso.Picasso
import com.pstorli.bestmoviedb.util.Consts

/**
 * Log an error message.
 */
fun String.logError()
{
  Log.e (Consts.TAG, this)
}

/**
 * Load an image.
 */
fun ImageView.loadImage (movie: Movie, activity: Activity?)
{
  // Get res id.
  val resId: Int = getResources().getIdentifier(
    movie.image,
    "drawable",
    activity?.getPackageName() ?: "com.pstorli.bestmoviedb"
  )

  // Load local image?
  if (resId > 0) {
    // /////////////////////////////////////////////////////////////////////////////////
    // Load image from resId in drawabe folder.
    // /////////////////////////////////////////////////////////////////////////////////
    setImageResource(resId)
  }

  // Remote image.
  else {
    // /////////////////////////////////////////////////////////////////////////////////
    // Load remote image.
    // /////////////////////////////////////////////////////////////////////////////////
    Picasso.get().load(movie.image).into(this)
  }
}
