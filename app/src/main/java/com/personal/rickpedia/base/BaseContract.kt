package com.personal.rickpedia.base

import androidx.lifecycle.LiveData
interface BaseContract {

    interface View {

        fun onInitView()
        fun onInitObserver()
        fun onFetchInitialData()
        fun onError(message: String)
        fun onLoading(loading: Boolean)
    }

    interface ViewModel {
        val message: LiveData<String>
        val loading: LiveData<Boolean>
    }
}