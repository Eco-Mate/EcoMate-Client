package com.example.ecomate.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ecomate.R
import com.example.ecomate.databinding.FragmentChatBinding
import com.example.ecomate.databinding.FragmentCommunityBinding
import com.example.ecomate.databinding.FragmentHomeBinding
import com.example.ecomate.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {
    lateinit var binding: FragmentMapBinding
    var isMap: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    private fun setUi() {
        binding.apply {
            childFragmentManager.beginTransaction().add(R.id.map_list_content, EcomapFragment()).commit()
            ecoshopSwitchBtn.setOnClickListener {
                when (isMap) {
                    true -> {
                        isMap = false
                        ecoshopSwitchBtn.setImageResource(R.drawable.map_menu)
                        childFragmentManager.beginTransaction().replace(R.id.map_list_content, EcolistFragment()).commit()
                    }
                    false -> {
                        isMap = true
                        ecoshopSwitchBtn.setImageResource(R.drawable.list)
                        childFragmentManager.beginTransaction().replace(R.id.map_list_content, EcomapFragment()).commit()
                    }
                }
            }
        }
    }
}