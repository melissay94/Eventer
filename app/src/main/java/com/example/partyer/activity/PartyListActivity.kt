package com.example.partyer.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.partyer.R
import com.example.partyer.adapter.PartyListAdapter
import com.example.partyer.data.PartyItem
import com.example.partyer.data.ReadFile
import com.example.partyer.viewmodel.PartyListViewModel
import com.google.gson.stream.JsonReader
import kotlinx.android.synthetic.main.activity_party_list.*
import java.io.InputStreamReader
import java.lang.Exception

class PartyListActivity: BaseActivity() {

    private lateinit var adapter: PartyListAdapter

    private lateinit var partyListViewModel: PartyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party_list)

        adapter = PartyListAdapter(this) { partyList, filesDir ->
            partyListViewModel.removePartyFromList(partyList, filesDir)
        }
        adapter.partyData = getPartyData()

        party_list_view.adapter = adapter
        party_list_view.layoutManager = LinearLayoutManager(this)

        val itemDivider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val divider = getDrawable(R.drawable.party_list_divider)
        divider?.let { itemDivider.setDrawable(divider) }
        party_list_view.addItemDecoration(itemDivider)

    }

    override fun initializeViewModel() {
        partyListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PartyListViewModel::class.java)
    }

    private fun getPartyData(): List<PartyItem> {
        return try {
            val reader = InputStreamReader(this.openFileInput("user_party_info.json"), "UTF-8")
            partyListViewModel.getUserPartyData(JsonReader(reader))
        } catch (exception: Exception) {
            Log.e("readFile", "File couldn't be read ${exception.message}")
            emptyList()
        }
    }
}