package com.example.ecomate.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomate.databinding.FragmentEcolistBinding
import com.example.ecomate.databinding.FragmentMapBinding
import com.example.ecomate.model.StoreInfo
import com.example.ecomate.ui.adapter.EcostoresAdapter
import com.example.ecomate.viewmodel.CommunityViewModel
import com.example.ecomate.viewmodel.EcolistViewModel
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class EcolistFragment : Fragment() {
    lateinit var binding: FragmentEcolistBinding
    private val ecolistViewModel: EcolistViewModel by viewModels()
    val stores = mutableListOf(
        StoreInfo(
            0,
            "매장 1",
            "매장 상세정보",
            "",
            35.856859,
            128.521188,
            "매장 주소",
            0,
            false
        ),
        StoreInfo(
            1,
            "매장 2",
            "매장 상세정보",
            "",
            35.856859,
            128.521188,
            "매장 주소",
            0,
            false
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEcolistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEcoStores()
        setUi()
        setAdapter(view)
    }

    private fun setUi() {
        binding.apply {

        }
    }

    private fun setAdapter(view: View) {
        val ecostoresAdapter = EcostoresAdapter()
        ecostoresAdapter.detailStoreListener =
            object : EcostoresAdapter.DetailStoreListener {
                override fun onClick(storeInfo: StoreInfo) {

                }
            }
        binding.ecostoreRv.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ecostoresAdapter
            addItemDecoration(
                DividerItemDecoration(
                    view.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        ecostoresAdapter.submitList(stores)

//        ecolistViewModel.ecostores.observe(viewLifecycleOwner) {
//            ecostoresAdapter.submitList(stores)
//        }
    }

    @SuppressLint("MissingPermission")
    private fun setEcoStores() {
        val lm: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val userCurrentLocation: Location? =
            lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        // 사용자의 현재 위치 (위도, 경도)
        val uLatitude = userCurrentLocation?.latitude
        val uLongitude = userCurrentLocation?.longitude
        Log.d("MyLocation", "Latitude: ${uLatitude}, Longitude: ${uLongitude}")
//        ecolistViewModel.getEcoStores(uLatitude!!, uLongitude!!)
    }
}