package com.bebooo43.androidquizapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bebooo43.androidquizapp.R
import com.bebooo43.androidquizapp.databinding.FragmentHomeBinding
import com.bebooo43.androidquizapp.splash.SplashFragmentDirections

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.viewModel= viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToResults.observe(viewLifecycleOwner){
            it?.also {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToResultFragment(it.toTypedArray())
                )
            }
        }

        viewModel.questions.observe(viewLifecycleOwner) {
            it?.takeIf { it.isNotEmpty() }?.also {
                viewModel.startQuiz()
            }
        }

    }


}