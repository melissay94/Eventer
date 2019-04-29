package com.example.partyer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.partyer.R
import com.example.partyer.data.PartyItem
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PartyListAdapter(
    private val context: Context,
    private val onClickAction: (partyList: List<PartyItem>, filesDir: File) -> Unit
): RecyclerView.Adapter<PartyListViewHolder>() {

    var partyData: List<PartyItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_party_entry, parent, false)
        return PartyListViewHolder(view) {partyList, filesDir ->
            onClickAction(partyList, filesDir)
        }
    }

    override fun getItemCount(): Int = partyData.size

    override fun onBindViewHolder(holder: PartyListViewHolder, position: Int) {
        if (partyData.isNullOrEmpty()) {
            holder.onBindEmptyState()
        } else {
            holder.onBindData(partyData[position])
        }
    }
}

class PartyListViewHolder(
    view: View,
    private val onClickAction: (partyList: List<PartyItem>, filesDir: File) -> Unit,
    private val partyList: List<PartyItem>): RecyclerView.ViewHolder(view) {

    private val title = itemView.findViewById<TextView>(R.id.party_name)
    private val partyDescription = itemView.findViewById<TextView>(R.id.party_description)
    private val startTime = itemView.findViewById<TextView>(R.id.party_start_time)
    private val endTime = itemView.findViewById<TextView>(R.id.party_end_time)
    private val removeButton = itemView.findViewById<Button>(R.id.remove_party_button)

    fun onBindData(partyItem: PartyItem) {
        title.text = partyItem.name
        partyDescription.text = partyItem.description
        val startDate = SimpleDateFormat("EEE, MMM d 'at' h:mm aaa", Locale.getDefault()).format(partyItem.startDate)
        startTime.text = startDate
        val endDate = SimpleDateFormat("EEE, MMM d 'at' h:mm aaa", Locale.getDefault()).format(partyItem.endDate)
        endTime.text = endDate

        removeButton.setOnClickListener {
            onClickAction(itemView.context.filesDir)
        }
    }
    
    fun onBindEmptyState() {
        title.text = itemView.context.getString(R.string.empty_party_list_title)
        partyDescription.text = itemView.context.getString(R.string.empty_party_list_description)
    }

    private fun removePartyFromList(partyItem: PartyItem): List<PartyItem> {
        if (pa)
    }
}