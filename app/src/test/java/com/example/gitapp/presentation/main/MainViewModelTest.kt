package com.example.gitapp.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gitapp.data.usecase.GetRepositoriesUseCase
import com.example.gitapp.domain.entity.Owner
import com.example.gitapp.domain.entity.Repositories
import com.example.gitapp.domain.entity.Repository
import com.example.gitapp.domain.repository.MainRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val repository = mockk<MainRepository>()
    private val useCase = mockk<GetRepositoriesUseCase>()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MainViewModel(useCase)

        viewModel.coroutineContext = Dispatchers.Unconfined + SupervisorJob()
    }

    @Test
    fun `getRepositories - Verifica sucesso da requisicao`() = runBlocking {
        coEvery { useCase.execute(PAGE_ONE) } returns getRepositories()
        coEvery { repository.getRepositories(PAGE_ONE) } returns getRepositories()

        viewModel.getRepositories(PAGE_ONE)

        Assert.assertEquals(
            viewModel.states.value, MainViewState.ShowRepositories(getRepositories().repositories.toCollection(ArrayList()))
        )
    }

    @Test
    fun `getRepositories - Verifica falha da requisicao`() = runBlocking {
        val exception: Exception = mockk()

        coEvery { repository.getRepositories(PAGE_ONE) } throws exception

        viewModel.getRepositories(PAGE_ONE)

        Assert.assertEquals(viewModel.states.value, MainViewState.ShowError(ERROR))
    }

    private fun getRepositories() : Repositories =
        Repositories(arrayListOf(getItems()))

    private fun getItems() : Repository =
        Repository(1, "itemName", 1, 1, getOwner())

    private fun getOwner() : Owner =
        Owner(1, "loginOwnser", "avatarUrlOwner")

    companion object {
        const val PAGE_ONE = 1
        const val ERROR = "Tente novamente."
    }
}