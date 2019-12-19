package fr.mathieubour.fetedelascience.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mathieubour.fetedelascience.data.Event
import fr.mathieubour.fetedelascience.http.EventsHttpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://storage.googleapis.com/public.mathieu-bour.fr/projects/emse-3a-android/")
        .build()
    private val eventsHttpService: EventsHttpService =
        retrofit.create(EventsHttpService::class.java)

    private val eventsList: MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>().also {
            loadEvents()
        }
    }

    val selectedEvent: MutableLiveData<Event> by lazy {
        MutableLiveData<Event>()
    }

    fun getEventsList(): LiveData<List<Event>> = eventsList

    /**
     * Since loadEvents is already asynchronous, we use retrofit's execute method
     */
    private fun loadEvents() {
        eventsHttpService.list().enqueue(object : Callback<List<Event>> {
            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.body() != null) {
                    eventsList.value = response.body()
                }
            }
        })
    }
}