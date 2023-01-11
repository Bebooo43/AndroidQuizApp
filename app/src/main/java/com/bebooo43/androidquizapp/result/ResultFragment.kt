package com.bebooo43.androidquizapp.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bebooo43.androidquizapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val nArgs: ResultFragmentArgs by navArgs()
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        binding.viewModel= viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTryAgain.setOnClickListener {
            findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToHomeFragment()
            )
        }
        binding.btnReview.setOnClickListener {
            findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToReviewFragment(viewModel.answersList.toTypedArray())
            )
        }
        viewModel.setResults(nArgs.answers.toList())
    }

}