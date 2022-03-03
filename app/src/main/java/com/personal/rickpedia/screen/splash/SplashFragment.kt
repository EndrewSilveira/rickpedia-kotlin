package com.personal.rickpedia.screen.splash

import android.os.Handler
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.personal.rickpedia.base.BaseFragment
import com.personal.rickpedia.base.BaseViewModel
import com.personal.rickpedia.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import org.koin.core.module.Module
import java.util.*
import kotlin.concurrent.timerTask

@AndroidEntryPoint
class SplashFragment: BaseFragment<FragmentSplashBinding>() {

    override val viewModel: BaseViewModel? = null
    override val bindingInflater: (LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun onInitView() {
        Handler().postDelayed({
            navigateToHome()
        }, 3000)
    }

    override fun onInitObserver() {}

    override fun onFetchInitialData() {}

    override fun onError(message: String) {}

    override fun onLoading(loading: Boolean) {}

    private fun navigateToHome(){
        val directions = SplashFragmentDirections.actionFragmentSplashToFragmentHome()
        this.findNavController().navigate(directions)
    }
}