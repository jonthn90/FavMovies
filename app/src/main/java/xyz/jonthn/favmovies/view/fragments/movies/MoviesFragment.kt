package xyz.jonthn.favmovies.view.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.databinding.FragmentMoviesBinding
import xyz.jonthn.favmovies.viewmodel.MoviesViewModel
import xyz.jonthn.favmovies.viewmodel.ViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private val factory: ViewModelFactory by inject()

    private val moviesViewModel: MoviesViewModel by navGraphViewModels(R.id.nav_movies) {
        factory
    }

    private val moviesAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        moviesViewModel.getMovies().observe(requireActivity(), Observer {
            moviesAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        binding.recyclerMovies.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = moviesAdapter
        }
    }
}
