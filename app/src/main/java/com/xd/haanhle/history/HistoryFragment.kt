package com.xd.haanhle.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xd.haanhle.R
import com.xd.haanhle.roomdatabase.*

class HistoryFragment: Fragment() {
    private lateinit var arrayList: ArrayList<Entry>
    private lateinit var lvShow : ListView
    lateinit var adapter: HistoryAdapter

    private lateinit var database: EntryDatabase
    private lateinit var databaseDao: EntryDatabaseDao
    private lateinit var repository: EntryRepository
    private lateinit var viewModelFactory: EntryViewModelFactory
    private lateinit var entryViewModel: EntryViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        lvShow = view.findViewById(R.id.lvShow)

        database = EntryDatabase.getInstance(view.context)
        databaseDao = database.entryDatabaseDao
        repository = EntryRepository(databaseDao)
        viewModelFactory = EntryViewModelFactory(repository)
        entryViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(EntryViewModel::class.java)

        arrayList = ArrayList()
        adapter = HistoryAdapter(requireActivity(),requireActivity(), arrayList)
        lvShow.adapter = adapter

        entryViewModel.allEntriesLiveData.observe(requireActivity(), Observer { it ->
            adapter.replace(it)
            adapter.notifyDataSetChanged()
        })

        adapter.notifyDataSetChanged()

        return view
    }
}