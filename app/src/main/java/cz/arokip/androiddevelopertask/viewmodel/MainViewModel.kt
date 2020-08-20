package cz.arokip.androiddevelopertask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cz.arokip.androiddevelopertask.Repository
import cz.arokip.androiddevelopertask.data.Position
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository()

//    val positions: LiveData<List<Position>> = liveData(Dispatchers.IO) {
//        val data = repository.getAllPositions()
//        emit(data)
//    }

    val positions: MutableLiveData<List<Position>> = MutableLiveData()

    fun getAllPositions() = GlobalScope.launch(Dispatchers.IO) {
        positions.postValue(repository.getAllPositions())
    }
}