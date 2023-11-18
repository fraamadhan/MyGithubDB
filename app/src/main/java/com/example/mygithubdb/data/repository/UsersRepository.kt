package com.example.mygithubdb.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygithubdb.data.retrofit.ApiConfig
import com.example.mygithubdb.data.retrofit.ApiService
import com.example.mygithubdb.model.GithubResponse
import com.example.mygithubdb.model.GithubUserDetailResponse
import com.example.mygithubdb.model.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepository(
    private val apiService: ApiService,
) {
    private val _github = MutableLiveData<GithubResponse>()

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser : LiveData<List<ItemsItem>> = _listUser

    private val _user = MutableLiveData<GithubUserDetailResponse>()
    val user : LiveData<GithubUserDetailResponse> = _user

    fun getUser(usn : String){
        val client = ApiConfig.getApiService().getUser(usn)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if(response.isSuccessful){
                    _github.value = response.body()
                    _listUser.value = response.body()?.items
                }
                else{
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }

        })
    }

    fun getDetailUser(usn : String = ""){
        val client = ApiConfig.getApiService().getDetailUser(usn)
        client.enqueue(object : Callback<GithubUserDetailResponse> {
            override fun onResponse(
                call: Call<GithubUserDetailResponse>,
                response: Response<GithubUserDetailResponse>
            ) {
                if(response.isSuccessful){
                    _user.value = response.body()
                }
                else{
                    Log.e(DETAILTAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserDetailResponse>, t: Throwable) {
                Log.e(DETAILTAG, "onFailure : ${t.message.toString()}")
            }

        })
    }


    companion object {
        private const val TAG = "MainViewModel"
        private const val DETAILTAG = "DetailViewModel"
        @Volatile
        private var instance: UsersRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): UsersRepository =
            instance ?: synchronized(this) {
                instance ?: UsersRepository(apiService)
            }.also { instance = it }
    }


}

