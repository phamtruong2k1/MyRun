package com.xd.haanhle.roomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.xd.haanhle.roomdatabase.Entry
import com.xd.haanhle.roomdatabase.EntryRepository
import kotlin.IllegalArgumentException

class EntryViewModel(private val repository: EntryRepository): ViewModel() {
    val allEntriesLiveData: LiveData<List<Entry>> = repository.allEntries.asLiveData()

    fun insert(entry: Entry) {
        repository.insert(entry)
    }

    fun deleteFirst() {
        val entryList = allEntriesLiveData.value
        if (entryList != null && entryList.size > 0 ) {
            val id = entryList[0].id
            repository.delete(id)
        }
    }


    fun deleteEntry(id: Long) {
        val entryList = allEntriesLiveData.value
        if(entryList != null) {
            for (i in 0 .. entryList.size -1) {
                if (entryList[i].id == id)
                    repository.delete(id)
            }
        }
    }


    fun deleteAll() {
        val entryList = allEntriesLiveData.value
        if(entryList != null && entryList.size > 0) {
            repository.deleteAll()
        }
    }

    fun findEntry(id: Long) {
        val entryList = allEntriesLiveData.value
        if(entryList != null) {
            for (i in 0 .. entryList.size -1) {
                repository
            }
        }
    }

}

class EntryViewModelFactory(private val repository: EntryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EntryViewModel::class.java))
            return EntryViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
