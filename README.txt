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

  A nice feature of the refresh button, is that the app only queries the network
  for data if it is not already cached in the database. When the refresh button is pressed,
  the contents of the database are cleared and new data from the network is fetched
  an cached into the database.

  Also, concerning the model classes, you can try to use the same model classes for the
  network, view and database access, but each has some quirks so it is better to have
  a generic model class that the db and network models can be converted to/from.
  The choice is between the complete flexibility of data/model classes for each
  type of data source and the pain of converting between all the different mocdels and
  the other choice is to have one model and adjust it so that everyone ius happy,
  not always the easiest or a possible thing to do. This app started out with the idea
  of one model, and experimented with models for each data source.

  The reality is that there are not only black and white,
  but an infinite number of shades of grey between the two.

  Lastly, concerning room and retrofit. Room seems to dislike lists of children and
  if retrofit gets a 200 (success) error code but no data, issue is probably
  that conversion process has failed and retrofit will not give you any info.

  Try re-generating network model classes directly from json and use those.
  Android Studio plugin JsonToKotlinClass to create data classes ALT-K or right click
  package -> New -> Kotlin data class from JSON

Very Impotent Stuff:
  If you just git, from github, add local.properties
    tmdb_api_key ="{your moviedb api key here}"
    sdk.dir      ="{your android sdk dir here}"

Resolved Issues Version 1004:
  0000 Changed genre/tv/list to genre/movie/list in Services.kt
  0000 Added shared prefs to keep track of page we are on.
  0000 Added old timey film countdown pic for loading.
  0000 Added refresh icon to toolbar to cause room/sqllite db to refresh from json tmdb call
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

  0000 Added Android Studio plugin JsonToKotlinClass to create data classes
       ALT-K or right click package -> New -> Kotlin data class from JSON

  0000 To Test Movie URL, get and call remote website with correct url and params in browser.
     For sample data see file: JSON_PopularMovies.json
     URL: https://api.themoviedb.org/3/movie/popular

  0000 To Test Genre URL, get and call remote website with correct url and params in browser.
       For sample data see file: JSON_MovieGenres.json
       URL: https://api.themoviedb.org/3/genre/tv/list

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
  0000 Need to update drawable\architecture.png

Tips:
  Consistency is your best friend, watch for repeating patterns in the flow.
  Evaluate first, accept next, then more forward, if needed.
  Don't give up, patience really is a virtue.

Desired Features:
  These are nice and possible, but the main idea of this app is to illustrate
  Coroutines, database access, network access and how to layer everything correctly
  to reduce dependancies. Added more functionality may make these ideas harder to see, see?
  0000 Add sql lite repository, DBDataSource - to keep track of user tastes without exposing PPI
  0000 Add search ability.
  0000 Add next/prev page buttons
  0000 Add snack bar to show messages.
  0000 Make back button and refresh button change color (black -> white) when pressed or hover
  0000 Add Custom Dialog to show about, also add about to overflow menu.
  0000 Would be nice when we refresh not to wipe Genres, but then again they may have also changed
       so perhaps I should remove this whole comment? Anyhow, that is whatr is currently happening.

Helpful URLS:
  https://www.themoviedb.org/?language=en-US
  https://www.baeldung.com/retrofit
  https://gabrieltanner.org/blog/android-room
  https://101android.com/room-persistence-library-implementation/
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



