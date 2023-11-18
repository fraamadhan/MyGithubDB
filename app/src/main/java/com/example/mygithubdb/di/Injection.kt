package com.example.mygithubdb.di

import com.example.mygithubdb.data.repository.UsersRepository
import com.example.mygithubdb.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(): UsersRepository {
        val apiService = ApiConfig .getApiService()
        return UsersRepository.getInstance(apiService)
    }
}