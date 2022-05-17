package com.example.githubapp.feature_search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.network.Result
import com.example.githubapp.core.network.launchCatchError
import com.example.githubapp.feature_search.domain.model.User
import com.example.githubapp.feature_search.domain.usecases.GithubUserUsecase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//Inject it
class GithubUserViewModel @Inject constructor(
    private val githubUserUsecase: GithubUserUsecase
) : ViewModel(){

    private val _user = MutableLiveData<Result<User>>()
    val user : LiveData<Result<User>> = _user

     fun getUserDetail(name: String){

         viewModelScope.launchCatchError(
             block = {
                 val response =  githubUserUsecase.invoke(name)
                 _user.value = response
             },
             onError = {

             }

         )
    }

}