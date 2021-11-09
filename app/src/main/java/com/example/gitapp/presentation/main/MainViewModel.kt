package com.example.gitapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapp.BaseViewModel
import com.example.gitapp.data.usecase.GetRepositoriesUseCase
import com.example.gitapp.domain.entity.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

private const val ERROR_MESSAGE = "Tente novamente."

class MainViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : BaseViewModel() {
    private val _states = MutableLiveData<MainViewState>()
    val states: LiveData<MainViewState>
        get() =_states

    fun getRepositories(page: Int) {
        launch {
            try {
                val response = getRepositoriesUseCase.execute(page)
                _states.value = MainViewState.ShowRepositories(response.repositories.toCollection(ArrayList()))
            } catch (exception: Exception) {
                _states.value = MainViewState.ShowError(ERROR_MESSAGE)
            }
        }
    }
}

sealed class MainViewState {
    data class ShowRepositories(val repositories: ArrayList<Repository>): MainViewState()
    data class ShowError(val error: String): MainViewState()
}