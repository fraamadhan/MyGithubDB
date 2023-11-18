package com.example.mygithubdb.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.example.mygithubdb.data.repository.UsersRepository

class DetailViewModel(private val usersRepository: UsersRepository): ViewModel() {
    val githubUser = usersRepository.user

    fun getUser(username: String){
        usersRepository.getDetailUser(username)
    }
}