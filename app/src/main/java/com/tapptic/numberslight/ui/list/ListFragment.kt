package com.tapptic.numberslight.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapptic.numberslight.R
import com.tapptic.numberslight.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listViewModel = ViewModelProviders.of(activity!!, (activity as MainActivity).factory).get(ListViewModel::class.java)

        val adapter = ListAdapter(activity as MainActivity)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL))

        listViewModel.numbers().observe(this, Observer {
            if (it != null) {
                adapter.updateList(it)
            }
        })
    }
}