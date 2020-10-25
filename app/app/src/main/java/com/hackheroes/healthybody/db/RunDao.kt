package com.hackheroes.healthybody.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC LIMIT 1")
    fun getLatestRunByDate(): LiveData<Run>

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>
}
