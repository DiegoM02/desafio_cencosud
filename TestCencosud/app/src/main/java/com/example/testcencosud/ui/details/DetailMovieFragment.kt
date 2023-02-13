package com.example.testcencosud.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.testcencosud.R
import com.example.testcencosud.databinding.FragmentDetailBinding
import com.example.testcencosud.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import com.example.testcencosud.utils.Response

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailMovieViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val movie: Movie = arguments?.
                getSerializable(getString(R.string.bundle_detail)) as Movie
        setupView(movie)
        val root: View = binding.root

        return root
    }

    private fun setupView(movie: Movie){
        observeGetLocalMovie(movie)
    }

    private fun observeGetLocalMovie(movie: Movie){
        viewModel.getMovie(movie.id).observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Response.Success -> {
                    binding.titleDetail.setText(result.data.title)
                    Glide.with(this).load(result.data.image).into(binding.imageView)
                    binding.genderDetail.setText(result.data.genres)
                    binding.durationTimeDetail.setText("${result.data.runtimeMins} Min")
                    binding.ratingDetail.setText(result.data.imDbRating)
                    binding.starsDetail.setText(result.data.stars)
                    binding.directorsDetail.setText(result.data.directors)
                    binding.releaseDetail.setText(result.data.releaseState)
                }
                is Response.Failure -> {

                }
                is Response.Loading -> {

                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}