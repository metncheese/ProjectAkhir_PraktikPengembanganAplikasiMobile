package com.dicoding.picodiploma.loginwithanimation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.loginwithanimation.data.Result
import com.dicoding.picodiploma.loginwithanimation.data.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    fun getPagingStories(token: String): LiveData<PagingData<ListStoryItem>> =
        repository.getPagingStories(token).cachedIn(viewModelScope)

    private val _storyList = MediatorLiveData<Result<List<ListStoryItem>>>()
    val storyList: LiveData<Result<List<ListStoryItem>>> = _storyList

    fun getStories(token: String) {
        val liveData = repository.getStories(token)
        _storyList.addSource(liveData) { result ->
            _storyList.value = result
        }
    }
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}