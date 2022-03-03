package com.personal.rickpedia.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.rickpedia.util.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel(): ViewModel(), BaseContract.ViewModel {

    override val loading: LiveData<Boolean>
        get() = mLoadingChanged

    override val message: LiveData<String>
        get() = mMessage

    private val mLoadingChanged = MutableLiveData<Boolean>()
    private val mMessage = MutableLiveData<String>()

    protected fun <T> LiveData<T>.post(data: T){
        if (this is MutableLiveData<T>){
            postValue(data)
        }
    }
    protected fun <T> LiveData<T>.call(){
        if (this is SingleLiveData<T>){
            call()
        }
    }

    protected fun defaultLaunch(
        loadingLiveData: LiveData<Boolean> = mLoadingChanged,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            try {
                loadingLiveData.post(true)

                block.invoke(this)
                loadingLiveData.post(false)
            } catch (exception: Exception) {
                loadingLiveData.post(false)

            }
        }
    }
}