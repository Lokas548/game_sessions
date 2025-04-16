package com.mironov.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class NotInLobbyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_not_in_lobby, container, false)
        return root


    }

    fun onBackPressed() {
        Toast.makeText(requireContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show()
    }

}