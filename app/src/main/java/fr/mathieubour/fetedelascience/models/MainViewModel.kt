package fr.mathieubour.fetedelascience.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mathieubour.fetedelascience.API
import fr.mathieubour.fetedelascience.data.Event
import fr.mathieubour.fetedelascience.data.EventDao
import fr.mathieubour.fetedelascience.data.EventsHttpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The main model which is bound to the MainActivity
 * @see fr.mathieubour.fetedelascience.MainActivity
 */
class MainViewModel : ViewModel() {
    // Retrofit builder
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API)
        .build()
    private val eventsHttpService: EventsHttpService =
        retrofit.create(EventsHttpService::class.java)

    // Room has to be instated in the activity
    private lateinit var eventDao: EventDao

    /**
     * The event list as MutableLiveData which allow to observe it
     */
    val eventsList: MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>().also {
            reloadEvents()
        }
    }

    /**
     * Assuming the event dao is set on the activity creation, display the database immediately
     */
    fun setEventDao(eventDao: EventDao) {
        this.eventDao = eventDao

        if (eventsList.value == null || eventsList.value!!.isEmpty()) {
            // If the list is not already populated, push the data from teh database to the list
            eventsList.value = this.eventDao.getAll()
        }
    }

    /**
     * Reload the events from the API, and override the database
     */
    fun reloadEvents() {
        eventsHttpService.list().enqueue(object : Callback<List<Event>> {
            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                // throw t // I don't really know what to do here, so I suppressed it
            }

            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                val events: List<Event> = response.body()!! // Cannot be realistically null
                eventsList.value = events // update the LiveData
                eventDao.insertAll(events) // override the existing database
            }
        })
    }
}