package com.ekite.android.testdaggerhilt.nasa.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.api.load
import com.ekite.android.testdaggerhilt.databinding.ActivityNasaBinding
import com.ekite.android.testdaggerhilt.utils.Status
import com.ekite.android.testdaggerhilt.utils.hide
import com.ekite.android.testdaggerhilt.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NasaActivity: AppCompatActivity() {

    private val nasaViewModel : NasaViewModel by viewModels()
    private lateinit var binding: ActivityNasaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNasaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        nasaViewModel.getPictureOfTheDay()
    }

    private fun setupObservers() {
        nasaViewModel.pictureOfTheDay.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.nasaProgressbar.hide()
                    binding.nasaImage.load(it.data?.url)
                    binding.nasaImage.show()
                }
                Status.ERROR -> {
                    binding.nasaProgressbar.hide()
                }
                Status.LOADING -> {
                    binding.nasaProgressbar.show()
                }
            }
        })
    }

}