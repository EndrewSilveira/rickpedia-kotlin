package com.personal.rickpedia.screen.detail

import android.os.Handler
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.personal.rickpedia.R
import com.personal.rickpedia.base.BaseFragment
import com.personal.rickpedia.databinding.FragmentDetailBinding
import com.personal.rickpedia.domain.character.Character
import com.personal.rickpedia.screen.home.CharactersAdapter
import com.personal.rickpedia.util.popBackStack
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import java.util.*


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override val viewModel: DetailViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> FragmentDetailBinding =
        FragmentDetailBinding::inflate

    private val args: DetailFragmentArgs by navArgs()

    override fun onInitView() {

        binding.ivBackIcon.setOnClickListener {
            popBackStack()
        }
        changeVisibility(false)
    }

    override fun onInitObserver() {
        viewModel.characterData.observe(this) {
            setDataInView(it)
        }
    }

    override fun onFetchInitialData() {
        viewModel.setData(args.character?.id)
    }

    override fun onError(message: String) {}

    override fun onLoading(loading: Boolean) {}

    // Region Local Method
    private fun changeVisibility(loading: Boolean) {
        binding.pbLoading.isVisible = !loading
        binding.ivBackgroundImage.isVisible = loading
        binding.mcvBack.isVisible = loading
        binding.mcvDetailImage.isVisible = loading
        binding.clDetailContainer.isVisible = loading
    }

    private fun setDataInView(query: Character?) {
        binding.tvDetailName.text = query?.name
        binding.includeInfo.tvDetailSpeciesValue.text = query?.species
        binding.includeInfo.tvGenderSpeciesValue.text = query?.gender
        binding.includeInfo.tvLocationSpeciesValue.text = query?.location?.name
        binding.includeInfo.tvOriginSpeciesValue.text = query?.origin?.name

        setImages(query)
        setStatus(query)

        Handler().postDelayed({
            changeVisibility(true)
        }, 2000)
    }

    private fun setStatus(query: Character?) {
        binding.includeInfo.tvStatus.text = query?.status
        when (query?.status?.toLowerCase(Locale.getDefault())) {
            CharactersAdapter.STATUS_ALIVE -> {
                context?.resources?.getColor(R.color.green_variant)
                    ?.let {
                        binding.includeInfo.tvStatus.setTextColor(it)
                        binding.mcvDetailImage.setCardBackgroundColor(it)
                    }
            }
            CharactersAdapter.STATUS_DEAD -> {
                context?.resources?.getColor(R.color.red)
                    ?.let {
                        binding.includeInfo.tvStatus.setTextColor(it)
                        binding.mcvDetailImage.setCardBackgroundColor(it)
                    }
            }
            else -> {
                context?.resources?.getColor(R.color.gray)
                    ?.let {
                        binding.includeInfo.tvStatus.setTextColor(it)
                        binding.mcvDetailImage.setCardBackgroundColor(it)
                    }
            }
        }
    }

    private fun setImages(query: Character?) {
        context?.let {
            Glide.with(it)
                .load(query?.image)
                .into(binding.civDetailImage)

            // Background
            Glide.with(it)
                .load(args.character?.image)
                .apply(bitmapTransform(BlurTransformation(22)))
                .into(binding.ivBackgroundImage)
        }
    }
}