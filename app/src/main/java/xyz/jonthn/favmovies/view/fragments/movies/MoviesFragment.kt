package xyz.jonthn.favmovies.view.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.databinding.FragmentFavsBinding
import xyz.jonthn.favmovies.databinding.FragmentMoviesBinding
import xyz.jonthn.favmovies.viewmodel.MainViewModel
import xyz.jonthn.favmovies.viewmodel.MoviesViewModel
import xyz.jonthn.favmovies.viewmodel.ViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private val factory: ViewModelFactory by inject()

    private val moviesViewModel: MoviesViewModel by navGraphViewModels(R.id.nav_movies) {
        factory
    }

    private val mainViewModel: MainViewModel by sharedViewModel()

    private val moviesAdapter by lazy {
        MoviesAdapter ({ movie, position ->
            Timber.d("+++ Movie Click: ${movie.title}")

            if (movie.isFav) {
                mainViewModel.deleteFavMovie(movie.id)
            } else {
                mainViewModel.insertMovie(movie)
            }
        }, { movie, position ->

            findNavController().navigate(
                R.id.action_moviesFragment_to_movieFragment,
                bundleOf("movie" to movie)
            )
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        mainViewModel.getMovies().observe(requireActivity(), Observer {
            moviesAdapter.submitList(it)
        })
    }

    fun updateAdapter(positon: Int){
        moviesAdapter.notifyItemChanged(positon)
    }

    private fun initializeList() {
        binding.recyclerMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }

    private fun invalidateAdapter() {
        binding.recyclerMovies.apply {
            adapter = null
            layoutManager = null
        }

        moviesAdapter.notifyDataSetChanged()
    }
}
