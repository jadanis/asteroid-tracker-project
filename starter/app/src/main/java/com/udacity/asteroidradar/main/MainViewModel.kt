package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = AsteroidRepository(database)

    private val _asteroidResponse = MutableLiveData<String>()

    private val _imageResponse = MutableLiveData<PictureOfDay>()
    val imageResponse: LiveData<PictureOfDay>
        get() = _imageResponse

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
//        viewModelScope.launch{
//            repository.refreshAsteroids()
//        }
        //_asteroids.value = asteroidList
        getImage()
        getAsteroids()
    }

    //per lesson 3.7
    private fun getAsteroids() {
        viewModelScope.launch {
            try {
                var asteroidResult = AsteroidApi.retrofitService.getProperties()
                Log.i("ViewModel: ",asteroidResult)
                _asteroids.value = parseAsteroidsJsonResult(JSONObject(asteroidResult))
            } catch (e: Exception){
                e.message?.let{ Log.i("ViewModel: ",it)}
            }
        }
    }

    //lesson 3.9
    private fun getImage() {
        viewModelScope.launch {
            try {
                var imageResult = AsteroidApi.retrofitService.getImage()
                _imageResponse.value = imageResult
            } catch (e: Exception){
                e.message?.let { Log.i("ViewModel: ", it) }
            }
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroid.value = asteroid
    }

    fun onNavigated() {
        _navigateToAsteroid.value = null
    }

}