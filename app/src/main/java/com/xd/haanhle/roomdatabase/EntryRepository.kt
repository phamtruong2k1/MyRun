package com.xd.haanhle.roomdatabase

import com.xd.haanhle.roomdatabase.Entry
import com.xd.haanhle.roomdatabase.EntryDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EntryRepository(private val entryDatabaseDao: EntryDatabaseDao) {

    val allEntries: Flow<List<Entry>> = entryDatabaseDao.getAllEntries()

    fun insert(entry: Entry) {
        CoroutineScope(Dispatchers.IO).launch{
            entryDatabaseDao.insertEntry(entry)
        }
    }

    fun delete(id: Long) {
        CoroutineScope(Dispatchers.IO).launch{
            entryDatabaseDao.deleteEntry(id)
        }
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch{
            entryDatabaseDao.deleteAll()
        }
    }

//    fun findEntry(id: Long) {
//        CoroutineScope(Dispatchers.IO).launch{
//            entryDatabaseDao.findEntry(id)
//        }
//    }
}
