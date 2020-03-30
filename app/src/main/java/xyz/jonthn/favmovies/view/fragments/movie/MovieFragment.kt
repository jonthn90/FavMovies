package xyz.jonthn.favmovies.view.fragments.movie

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.databinding.FragmentMovieBinding
import xyz.jonthn.favmovies.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val mainViewModel: MainViewModel by sharedViewModel()

    private val args: MovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false).apply {
            viewModel = mainViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.movie.let {
            mainViewModel.movieDetail.value = it
            setImagePoster(it.poster_path)
        }

        setupToolbar()
    }


    private fun setImagePoster(posterPath: String) {
        val uri = Uri.parse("https://image.tmdb.org/t/p/w500/${posterPath}")
        binding.imagePoster.setImageURI(uri, null)
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}
