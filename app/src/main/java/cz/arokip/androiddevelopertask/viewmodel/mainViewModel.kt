package cz.arokip.androiddevelopertask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import cz.arokip.androiddevelopertask.Repository
import cz.arokip.androiddevelopertask.data.Position
import kotlinx.coroutines.Dispatchers

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val repository: Repository = Repository()

    val positions: LiveData<List<Position>> = liveData(Dispatchers.IO) {
        val meta = repository.getAllPositions()
        emit(meta)
    }

}