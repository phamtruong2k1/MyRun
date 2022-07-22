package com.xd.haanhle.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDatabaseDao {

    @Insert
    suspend fun insertEntry(entry: Entry)

    @Query("SELECT * FROM entry_table")
    fun getAllEntries(): Flow<List<Entry>>

    @Query("DELETE FROM entry_table")
    suspend fun deleteAll()

    @Query("DELETE FROM entry_table WHERE id = :key") //":" indicates that it is a Bind variable
    suspend fun deleteEntry(key: Long)

//    @Query ("SELECT id FROM entry_table WHERE id = :key")
//    suspend fun findEntry(key: Long)

}
