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

  Shows how to design kotlin android app that shows popular movies from tmdb.
  (https://www.themoviedb.org)

  The latest techniques and technologies were used to show off what an app can be if only
  the best tools, libraries and methodologies are used.

  Keywords: TMDB Kotlin Coroutines MVVM Glide Room RecyclerView Models NavController
  Fragments Retrofit Rest JSON OKhttp Expresso Junit

Resolved Issues Version 1004:
  0000 !Added refresh icon to toolbar to cause room/sqllite db to refresh from json tmdb call
  0000 !Cached movie result in a local database using Room so that the user
       does not lose their results if the remote tmdb popular movies changes.
       They have to press the new refresh button on the toolbar.
  0000 Added json to/from string room transform classes to handle list of List<Genre>
  0000 Added room relationship and bridge classes (Movie, Movies and Genres and Genre)
       to handle list of movies also (Genres and Genre classes to handle int genres)

Resolved Issues Version 1003:
  0000 Have movie list and movie details coming from themoviedb using retrofit and co-routines
  0000 Added the following suppport libraries:
    Okhttp, Retrofit, Moshi / GSON, Kotlin Coroutines, Glide (faster than Picasso)

  0000 Added Android Studio plugin JsonToKotlinClass to create moshi data classes
       ALT-K or right click package -> New -> Kotlin data class from JSON

  0000 To Test Movie URL, get and call remote website with correct url and params in browser.
     For sample data see file: JSON_PopularMovies.json
     URL: https://api.themoviedb.org/3/movie/popular?api_key=BuildConfig.TMDB_API_KEY&language=en-US&page=1

  0000 To Test Genre URL, get and call remote website with correct url and params in browser.
       For sample data see file: JSON_MovieGenres.json
       URL: https://api.themoviedb.org/3/genre/tv/list?api_key=BuildConfig.TMDB_API_KEY&language=en-US

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
  0000 Add sql lite repository, DBDataSource - to keep track of user tastes without exposing PPI
  0000 Add search ability.

Helpful URLS:
  https://www.themoviedb.org/?language=en-US
  https://www.baeldung.com/retrofit
  https://gabrieltanner.org/blog/android-room
  https://developer.android.com/codelabs/android-room-with-a-view-kotlin
  https://android.jlelse.eu/android-architecture-components-room-introduction-4774dd72a1ae
  https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
  https://mobikul.com/android-room-persistence-library/
  https://itnext.io/how-to-create-m-n-relationship-with-room-and-kotlin-ddbdebf0ee38
  https://dev.to/normanaspx/android-room-how-works-one-to-many-relationship-example-5ad0
  https://www.journaldev.com/13639/retrofit-android-example-tutorial
  https://developer.android.com/jetpack/androidx/releases/navigation
  https://blog.mindorks.com/exploring-android-view-pager2-in-android
  https://android.jlelse.eu/android-networking-in-2019-retrofit-with-kotlins-coroutines-aefe82c4d777
  https://github.com/navi25/RetrofitKotlinDeferred/
  https://www.vogella.com/tutorials/Retrofit/article.html



