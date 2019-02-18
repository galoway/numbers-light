package com.tapptic.numberslight.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tapptic.numberslight.R
import com.tapptic.numberslight.ui.detail.DetailFragment
import com.tapptic.numberslight.ui.detail.DetailViewModel
import com.tapptic.numberslight.ui.list.ListAdapter
import com.tapptic.numberslight.ui.list.ListFragment
import com.tapptic.numberslight.ui.list.ListViewModel
import com.tapptic.numberslight.utils.ConnectionLiveData
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ListAdapter.OnItemClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!resources.getBoolean(R.bool.isTablet)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            //Portrait orientation
            val firstFragment = ListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, firstFragment).commit()
        }

        val listViewModel = ViewModelProviders.of(this, factory).get(ListViewModel::class.java)

        //Check internet connectivity
        val connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer {
            if (it) {
                noInternet.visibility = View.GONE
                listViewModel.loadNumbers()
            } else {
                noInternet.visibility = View.VISIBLE
            }
        })
    }

    override fun onItemClick(name: String) {
        val detailViewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        detailViewModel.loadNumber(name)

        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            //Portrait orientation
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}
