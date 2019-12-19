package fr.mathieubour.fetedelascience.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM event WHERE id = :eventId")
    fun find(eventId: String): Event?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(events: List<Event>)
}