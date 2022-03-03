package com.personal.rickpedia.screen.home

import android.view.LayoutInflater
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.personal.rickpedia.R
import com.personal.rickpedia.base.BaseFragment
import com.personal.rickpedia.databinding.FragmentHomeBinding
import com.personal.rickpedia.domain.character.Character
import com.personal.rickpedia.util.TransitionAnimation
import com.personal.rickpedia.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override val viewModel: HomeViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    private val characterAdapter: CharactersAdapter by lazy { CharactersAdapter() }

    override fun onInitView() {
        setupDrawer()

        binding.tvScreenTitle.text = getString(R.string.home_title)
        binding.rvCharactersList.adapter = characterAdapter
        binding.ivMenuIcon.setOnClickListener {
            binding.drawerLayout.openDrawer(binding.navView)
        }
        binding.ivSearchIcon.setOnClickListener {
//            Toast.makeText(requireContext(), "Not available at the moment, Sorry.", Toast.LENGTH_SHORT).show()
            binding.includeSearchBar.root.isVisible = !binding.includeSearchBar.root.isVisible
        }

        characterAdapter.onItemClick = {
            onNavigateToDetail(it)
        }
    }

    override fun onInitObserver() {
        viewModel.allCharacters.observe(this, { characters ->
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
        })
    }

    override fun onFetchInitialData() {}

    override fun onError(message: String) {
        //TODO create exception Handler on BaseViewModel
    }

    override fun onLoading(loading: Boolean) {
        binding.pbLoading.isVisible = loading
    }
    //endregion Base Fragment

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

    //region Local Method
    private fun setupDrawer() {
        binding.navView.setNavigationItemSelectedListener(this)
        binding.drawerLayout.closeDrawer(binding.navView)
    }
    //endregion LocalMethod
}