package com.example.testcencosud.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcencosud.R
import com.example.testcencosud.databinding.FragmentListMovieBinding
import com.example.testcencosud.domain.model.Movie
import com.example.testcencosud.utils.Response
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListMovieFragment : Fragment() {

    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ListMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupView()
        observerGetAllMovies()
        return root
    }

    private fun setupView() {
        binding.recyclerView.visibility = View.GONE
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observerGetAllMovies(){
        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { result ->

            when(result) {
                is Response.Loading -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is Response.Failure -> {
                    Snackbar.make(requireView(), R.string.error_text, 5000)
                        .setBackgroundTint(Color.RED)
                        .setTextColor(resources.getColor(R.color.white))
                        .show()
                }
                is Response.Success -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressCircular.visibility = View.GONE
                    binding.recyclerView.adapter = MovieListAdapter(result.data){
                        goToDetailMovie(it)
                    }
                }
            }
        } )
    }

    fun goToDetailMovie(movie: Movie) {
        val bundle = Bundle()
        bundle.putSerializable(getString(R.string.bundle_detail), movie)
        findNavController().navigate(R.id.action_navigation_home_to_navigation_detail,bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}