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
    var errorMessage: String? = null

    fun getAllPositions() = GlobalScope.launch(Dispatchers.IO) {
        val pos: List<Position>? = try {
            repository.getAllPositions()
        } catch (e: Exception) {
            createErrorMessage(e)
            null
        }

        positions.postValue(pos)
    }

    fun searchPositions(search: String) = GlobalScope.launch(Dispatchers.IO) {
        val pos: List<Position>? = try {
            repository.searchPositions(search)
        } catch (e: Exception) {
            createErrorMessage(e)
            null
        }

        positions.postValue(pos)
    }

    fun createErrorMessage(e: Exception) {
        when (e) {
            is UnknownHostException -> {
                errorMessage = "Connection error."
            }
            is SocketTimeoutException -> {
                errorMessage = "Connection error."
            }
            is HttpException -> {
                errorMessage = "Connection error."
            }
            is ConnectException -> {
                errorMessage = "Connection error."
            }
            else -> {
                errorMessage = "Unknown error occurred."
            }
        }
    }
}