package com.example.mygithubdb.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.mygithubdb.data.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val usersRepository: UsersRepository): ViewModel() {

    init {
        getUsers("Fakhri")
    }

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    val githubListUser = usersRepository.listUser

    fun getUsers(username: String){
        usersRepository.getUser(username)
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        usersRepository.getUser(_query.value)
    }
}