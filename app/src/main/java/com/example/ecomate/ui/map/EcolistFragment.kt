package com.example.ecomate.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
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
        ecolistViewModel.getUserProfile(sharedPreferencesUtil.getMemberId())
        setEcoStores()
        setUi()
        setAdapter(view)
    }

    override fun onResume() {
        super.onResume()
        ecolistViewModel.getEcoStores()
    }

    private fun setUi() {
        binding.apply {
            ecolistViewModel.profileInfo.observe(viewLifecycleOwner) {
                if (it.role == "ROLE_ADMIN") {
                    ecostoreAdd.visibility = View.VISIBLE
                }
            }
            searchBtn.setOnClickListener {

            }
            ecostoreAdd.setOnClickListener {
                startActivity(Intent(activity, EcostoreAddActivity::class.java))
            }
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

        ecolistViewModel.ecostores.observe(viewLifecycleOwner) {
            ecostoresAdapter.submitList(it)
        }
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