package fr.mathieubour.fetedelascience.http

import fr.mathieubour.fetedelascience.data.Event
import retrofit2.Call
import retrofit2.http.GET

interface EventsHttpService {
    @GET("dataset.json")
    fun list(): Call<List<Event>>
}