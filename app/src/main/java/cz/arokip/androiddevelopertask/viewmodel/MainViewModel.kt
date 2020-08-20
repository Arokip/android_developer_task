package cz.arokip.androiddevelopertask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cz.arokip.androiddevelopertask.Repository
import cz.arokip.androiddevelopertask.data.Position
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository()

    val positions: MutableLiveData<List<Position>> = MutableLiveData()
    var errorPositionMessage: String? = null

    fun getAllPositions() = GlobalScope.launch(Dispatchers.IO) {
        val pos: List<Position>? = try {
            repository.getAllPositions()
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> {
                    errorPositionMessage = "Connection error."
                }
                is SocketTimeoutException -> {
                    errorPositionMessage = "Connection error."
                }
                is HttpException -> {
                    errorPositionMessage = "Connection error."
                }
                is ConnectException -> {
                    errorPositionMessage = "Connection error."
                }
                else -> {
                    errorPositionMessage = "Unknown error occurred."
                }
            }
            null
        }

        positions.postValue(pos)
    }
}