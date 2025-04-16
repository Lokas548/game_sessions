package com.mironov.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.mironov.myapplication.R

class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val navController = NavHostFragment.findNavController(this)
        val searchButton = root.findViewById<Button>(R.id.filterBtn)

        searchButton.setOnClickListener{
            println("1")
            navController.navigate(R.id.lobbyFilterFragment)
        }

        return root

    }


}
