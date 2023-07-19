package com.example.ecomate.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecomate.databinding.FragmentChatBinding
import com.example.ecomate.databinding.FragmentCommunityBinding
import com.example.ecomate.databinding.FragmentHomeBinding
import com.example.ecomate.databinding.FragmentMapBinding

class MapFragment : Fragment() {
    lateinit var binding: FragmentMapBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }
}