package com.itunesampleapp.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.api.models.Result
import java.util.concurrent.locks.Lock


@Database(
    entities = [Result::class],
     version = 1
)
abstract class VideoDatabase:RoomDatabase() {
    abstract fun getVideos():VideoDao

    companion object{
        @Volatile
        private var instance :VideoDatabase?= null
        private val LOCK = Any()
        operator fun invoke(context:Context) =instance?: synchronized(LOCK){
            instance?:createDatabase(context).also{ instance = it}

        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                VideoDatabase::class.java,
                "videos_db.db"
            ).build()
    }
}