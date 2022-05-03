package com.personal.rickpedia.screen.home

import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.personal.rickpedia.R
import com.personal.rickpedia.base.BaseFragment
import com.personal.rickpedia.databinding.FragmentHomeBinding
import com.personal.rickpedia.domain.character.Character
import com.personal.rickpedia.util.OnLoading
import com.personal.rickpedia.util.TransitionAnimation
import com.personal.rickpedia.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override val viewModel: HomeViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    private val characterAdapter: CharactersAdapter by lazy { CharactersAdapter() }
    private var onLoading: OnLoading? = null

    override fun onInitView() {
        binding.mtbHomeToolbar.title = getString(R.string.home_title)
        binding.rvCharactersList.adapter = characterAdapter

        characterAdapter.onItemClick = {
            onNavigateToDetail(it)
        }
        onLoading = {
            binding.includeShimmerHome.shimmerhome.isVisible = it
            if (it){
                binding.rvCharactersList.isVisible = false
            } else {
                binding.shimmerLayout.stopShimmer()
                binding.rvCharactersList.isVisible = true
                binding.rvCharactersList.scrollTo(0, 0)
            }
        }
    }

    override fun onInitObserver() {
        viewModel.allCharacters.observe(this) { characters ->
            characterAdapter.submitData(lifecycle, characters)

            //TODO Search Feature with paging Data
//            binding.includeSearchBar.teSearch.doAfterTextChanged { text ->
//                if (text.isNullOrBlank()){
//                    characterAdapter.submitData(lifecycle, characters)
//                } else {
//                    val filteredResults = characters.filter { filter ->
//                        filter.name.contains(text.toString())
//                        filter.status.contains(text.toString())
//                        filter.gender.contains(text.toString())
//                        filter.origin?.name!!.contains(text.toString())
//                    }
//                    characterAdapter.submitData(lifecycle, filteredResults)
//                    binding.tvNoResults.isVisible = filteredResults != null
//                }
//            }
        }
    }

    override fun onFetchInitialData() {
        viewModel.fetchList(onLoading)
        binding.shimmerLayout.startShimmer()
    }

    override fun onError(message: String) {
        //TODO create exception Handler on BaseViewModel
    }

    override fun onLoading(loading: Boolean) {}
    //endregion Base Fragment

    //region Local Method
    //region Navigation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //TODO navigation to other lists
        }
        return true
    }

    private fun onNavigateToDetail(character: Character) {
        val directions = HomeFragmentDirections.actionFragmentHomeToFragmentDetail(character)
        navigate(directions, TransitionAnimation.TRANSLATE_FROM_RIGHT)
    }
    //endregion Navigation
    //endregion LocalMethod
}