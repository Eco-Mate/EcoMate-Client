package com.example.ecomate.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecomate.databinding.FragmentEcomapBinding
import com.example.ecomate.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapView

class EcomapFragment : Fragment() {
    lateinit var binding: FragmentEcomapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEcomapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    private fun setUi() {
        binding.mapView.addView(MapView(binding.root.context))
    }
}