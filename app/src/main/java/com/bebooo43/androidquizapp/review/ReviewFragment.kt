package com.bebooo43.androidquizapp.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import com.bebooo43.androidquizapp.databinding.FragmentReviewBinding
import com.bebooo43.androidquizapp.home.QuestionModel
import com.bebooo43.androidquizapp.result.ResultFragmentArgs


class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private val nArgs: ResultFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listView.adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_list_item_1,
            getResultsList(nArgs.answers.asList())
        )
    }

    private fun getResultsList(list: List<QuestionModel>): MutableList<String> =
        mutableListOf<String>().also {
            list.forEach { question ->
                val listItem = String.format(
                    "%s\nYour answer: %s\nCorrect answer: %s",
                    question.questionText,
                    question.answer,
                    question.explanation
                )
                it.add(listItem)
            }
        }

}