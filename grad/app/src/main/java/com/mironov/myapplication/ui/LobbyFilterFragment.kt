package com.mironov.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LobbyFilterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_lobby_filter, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.filterRecycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        recycler.adapter

        return root


    }

    fun onBackPressed() {
        Toast.makeText(requireContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show()
    }

}