package io.newsystemtest.filtersapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.newsystemtest.filtersapp.databinding.FragmentCategoryBinding
import io.newsystemtest.filtersapp.databinding.FragmentOthersBinding

class Home: Fragment() {
    private lateinit var binding: FragmentOthersBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOthersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}