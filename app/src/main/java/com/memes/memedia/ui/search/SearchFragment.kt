package com.memes.memedia.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
//import androidx.lifecycle.ViewModelProviders
import com.memes.memedia.R

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
//       ---I COMMENTED THIS OUT BECAUSE IT REFERENCES A VIEW THAT DOES NOT EXIST AND I DON'T KNOW WHAT THE INTENTION WAS BEHIND THIS---
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        searchViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}