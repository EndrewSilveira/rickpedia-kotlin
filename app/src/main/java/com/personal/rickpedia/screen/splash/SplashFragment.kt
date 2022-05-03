package com.personal.rickpedia.screen.splash

import android.os.Handler
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.personal.rickpedia.R
import com.personal.rickpedia.base.BaseFragment
import com.personal.rickpedia.base.BaseViewModel
import com.personal.rickpedia.databinding.FragmentSplashBinding
import com.personal.rickpedia.util.TransitionAnimation
import com.personal.rickpedia.util.navigate
import com.personal.rickpedia.util.startAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val viewModel: BaseViewModel? = null
    override val bindingInflater: (LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun onInitView() {
        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.circle_explosion_animation)
                .apply {
                    duration = 2000
                    interpolator = AccelerateDecelerateInterpolator()

                }

        Handler().postDelayed({
            binding.icRick.animate()
                .alpha(0f)
                .duration = 100
            binding.viewCircle.startAnimation(animation) {
                // onEnd
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.rick_cian
                    )
                )
                binding.viewCircle.isVisible = false
                binding.icRick.isVisible = false
                navigateToHome()
            }
        }, 1000)
    }

    override fun onInitObserver() {}

    override fun onFetchInitialData() {}

    override fun onError(message: String) {}

    override fun onLoading(loading: Boolean) {}

    private fun navigateToHome() {
        val directions = SplashFragmentDirections.actionFragmentSplashToFragmentHome()
        navigate(directions, TransitionAnimation.FADE)
    }
}