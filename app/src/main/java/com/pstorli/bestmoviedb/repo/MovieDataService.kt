package com.pstorli.bestmoviedb.repo

import com.pstorli.bestmoviedb.model.Movie

/**
 * This data service class servers up static / test data for use by those in need of it.
 */
object MovieDataService {

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Some of Pete's Favorite movies used for testing.
    // /////////////////////////////////////////////////////////////////////////////////////////////
    val movies = listOf (
        //    Title                           Image Name (in drawable)        Description - matches strings.xml
        Movie("2001 A Space Odessy",     "a2001",       "Humanity finds a mysterious object buried beneath the lunar surface and sets off to find its origins with the help of HAL 9000, the worlds most advanced super computer ..."),
        Movie("The Hunt for Red October","red_oct",     "A new, technologically-superior Soviet sub, the Red October, is heading for the U.S. coast under the command of Captain Marko Ramius. The American government thinks Ramius is planning to attack...."),
        Movie("Young Frankenstein",      "young_frank", "A young neurosurgeon inherits the castle of his grandfather, the famous Dr. Victor von Frankenstein. In the castle he finds a funny hunchback, a pretty lab assistant and the elder ..."),
        Movie("The Fog of War",          "fog_of_war",  "Using archival footage, United States Cabinet conversation recordings, and an interview of the 85-year-old Robert McNamara, THE FOG OF WAR depicts his life, from working as a War ..."),
    )
}