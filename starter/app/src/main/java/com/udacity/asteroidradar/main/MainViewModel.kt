package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

//    private val _asteroids = MutableLiveData<List<Asteroid>>()
//    val asteroids: LiveData<List<Asteroid>>
//        get() = _asteroids
    private val asteroid1 = Asteroid(0L, "68347", "2020-02-08", 0.0, 0.0, 0.0, 0.0, true)
    private val asteroid2 = Asteroid(0L, "XK351", "2020-02-08", 0.0, 0.0, 0.0, 0.0, true)
    private val asteroid3 = Asteroid(0L, "BA39", "2020-02-08", 0.0, 0.0, 0.0, 0.0, true)

    val asteroids = listOf<Asteroid>(
        asteroid1,
        asteroid2,
        asteroid3
    )

}