# BestMovieDB

Name:            BestMovieDB
Date:            27 Sep 2020

Author:          Pete Storli
Email:           pstorli@gmail.com
Phone:           971-888-2534
Address:         18529 Chemawa Lane NE, Silverton, OR 97381

Description:
  Create an app that shows the user a list of popular movies.
  When a movie is clicked, the user should be taken to a details page.

Creation Reason:
  To create an app that illustrates a common set of operations,
  showing a list and then showing details of selected item.
  The list is fetched from a web service, so background work was needed.
  The latest techniques and technologies were used to show of
  what an app can be if only the best tools, libraries and methodologies are used.

  Some techniques used here were clean mvvm architecture, custom toolbar, recycler views,
  view models, new google screen navigation framework, nav_graph.xml, nav controller, fragments,
  kotlin coroutines and extension functions, live data, observers, view holders,
  data sources, repositories, web services, rest and json
  suppport library usage: Okhttp, Retrofit, Moshi / GSON, Kotlin Coroutines, Picasso / Glide
  testing using junit and expresso using idling resources

Resolved Issues Version 1003:
  0000 Added the following suppport libraries:
    Okhttp, Retrofit, Moshi / GSON, Kotlin Coroutines, Picasso / Glide

  0000

Resolved Issues Version 1002:
  0000 Added live data observer to viewmodel
  0000 Updated drawable/architecture.png
  0000 Added back button to toolbar
  0000 Added coroutines between MovieViewModel and MovieRepository

Resolved Issues Version 1001:
  0000 Added toolbar icon and larger toolbar text

Resolved Issues Version 1000:
  0000 Init Commit
  0000 Can move between list and detail fragfments with button clicks
       using new google navigation framework.

Known Issues:
  0000 Try to keep selected movie the same between reloads, if possible.

Desired Features
  0000 Add sql lite repository, DBDataSource

Helpful URLS:
  https://www.themoviedb.org/?language=en-US
  https://developer.android.com/jetpack/androidx/releases/navigation
  https://blog.mindorks.com/exploring-android-view-pager2-in-android
  https://android.jlelse.eu/android-networking-in-2019-retrofit-with-kotlins-coroutines-aefe82c4d777
  https://github.com/navi25/RetrofitKotlinDeferred/

Tips, tricks and backflips!

  // Sample themoviedb request
  https://api.themoviedb.org/3/movie/550?api_key=${local.properties.TMDB_API_KEY}


