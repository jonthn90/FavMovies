package xyz.jonthn.favmovies.view.fragments.favs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import org.koin.android.ext.android.inject
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.databinding.FragmentFavsBinding
import xyz.jonthn.favmovies.viewmodel.FavsViewModel
import xyz.jonthn.favmovies.viewmodel.ViewModelFactory

class FavsFragment : Fragment() {

    private lateinit var binding: FragmentFavsBinding

    private val factory: ViewModelFactory by inject()

    private val favsViewModel: FavsViewModel by navGraphViewModels(R.id.nav_favs) {
        factory
    }

    private val favsAdapter by lazy {
        FavsAdapter {
            favsViewModel.deleteFavMovie(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        favsViewModel.getFavMovies().observe(requireActivity(), Observer {
            favsAdapter.submitList(it)

            if (it.isNullOrEmpty())
                binding.layoutEmpty.visibility = View.VISIBLE
            else
                binding.layoutEmpty.visibility = View.GONE
        })
    }

    private fun initializeList() {
        binding.recyclerMovies.apply {
            adapter = favsAdapter
        }
    }
}