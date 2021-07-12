package com.itunesampleapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.api.models.Result

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(result :Result):Long

    @Query("SELECT * FROM videosList")
    fun getAllVideos():LiveData<List<Result>>



}