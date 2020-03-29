package xyz.jonthn.favmovies.view.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
