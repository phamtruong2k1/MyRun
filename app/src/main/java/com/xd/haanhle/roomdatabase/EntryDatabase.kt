package com.xd.haanhle.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Entry::class], version = 2)
abstract class EntryDatabase:RoomDatabase() {
    abstract val entryDatabaseDao : EntryDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getInstance (context: Context): EntryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        EntryDatabase::class.java, "entry_table").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
