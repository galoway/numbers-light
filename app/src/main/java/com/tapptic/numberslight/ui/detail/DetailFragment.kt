package com.tapptic.numberslight.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.tapptic.numberslight.R
import com.tapptic.numberslight.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: Fragment() {
    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewModel = ViewModelProviders.of(activity!!, (activity as MainActivity).factory).get(DetailViewModel::class.java)

        detailViewModel.number().observe(this, Observer {
            Picasso.get().load(it.image).into(image)
            text.text = it.text
        })
    }
}