package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private val _navigateToAsteroid = MutableLiveData<Asteroid>()
    val navigateToAsteroid: LiveData<Asteroid>
        get() = _navigateToAsteroid

    private val asteroid1 = Asteroid(0L, "68347", "2020-02-08", 0.0, 0.0, 100.0, 30.0, true)
    private val asteroid2 = Asteroid(1L, "XK351", "2020-07-15", 0.0, 0.0, 151.0, 66.0, false)
    private val asteroid3 = Asteroid(2L, "BA39", "2020-10-14", 0.0, 0.0, 700.0, 72.0, true)

    private val asteroidList = listOf<Asteroid>(
        asteroid1,
        asteroid2,
        asteroid3
    )

    init {
        _asteroids.value = asteroidList
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroid.value = asteroid
    }

    fun onNavigated() {
        _navigateToAsteroid.value = null
    }

}