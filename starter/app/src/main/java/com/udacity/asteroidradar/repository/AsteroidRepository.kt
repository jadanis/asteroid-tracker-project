package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.database.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

class AsteroidRepository(private val database: AsteroidDatabase) {


    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }


    suspend fun refreshAsteroids(){
        withContext(Dispatchers.IO){
            try {
                var asteroidResult = AsteroidApi.retrofitService.getProperties()
                var networkAsteroids = parseAsteroidsJsonResult(JSONObject(asteroidResult))
                database.asteroidDao.insertAll(*networkAsteroids.asDatabaseModel())
            } catch (e: Exception){
                e.message?.let{ Timber.i(it)}
            }
        }
    }

    suspend fun clearOldAsteroids(){
        withContext(Dispatchers.IO){
            try {
                database.asteroidDao.clearOldAsteroids()
            } catch (e: Exception){
                e.message?.let{
                    Timber.i(it)
                }
            }
        }
    }

}