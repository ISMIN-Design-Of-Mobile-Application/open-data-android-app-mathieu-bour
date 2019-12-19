package fr.mathieubour.fetedelascience.data

import retrofit2.Call
import retrofit2.http.GET

/**
 * The Retrofit interface to query the dataset, hosted on Google Cloud Storage
 */
interface EventsHttpService {
    /**
     * Get the complete JSON dataset
     */
    @GET("dataset.json")
    fun list(): Call<List<Event>>


    /**
     * Get the an excerpt (10 events) of JSON dataset
     */
    @GET("dataset.excerpt.json")
    fun listExcerpt(): Call<List<Event>>
}