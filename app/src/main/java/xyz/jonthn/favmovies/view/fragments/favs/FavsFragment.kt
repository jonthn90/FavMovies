package xyz.jonthn.favmovies.view.fragments.favs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favs, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
