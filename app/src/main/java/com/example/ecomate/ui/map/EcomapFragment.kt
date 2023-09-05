package com.example.ecomate.ui.map

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecomate.ApplicationClass
import com.example.ecomate.R
import com.example.ecomate.databinding.FragmentEcomapBinding
import com.example.ecomate.databinding.ItemBalloonBinding
import com.example.ecomate.model.Board
import com.example.ecomate.model.StoreInfo
import com.example.ecomate.viewmodel.EcomapViewModel
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class EcomapFragment : Fragment() {
    lateinit var binding: FragmentEcomapBinding
    lateinit var itemBalloonBinding: ItemBalloonBinding
    private val ecomapViewModel: EcomapViewModel by viewModels()
    var isMyLocation = false
  
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEcomapBinding.inflate(inflater, container, false)
        itemBalloonBinding = ItemBalloonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMarkers()
        setUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTracking()
    }

    private fun setUi() {
        binding.apply {
            currentLocationBtn.setOnClickListener {
                if (isMyLocation) {
                    stopTracking()
                    currentLocationBtn.setImageResource(R.drawable.location_icon)
                    isMyLocation = false
                } else {
                    startTracking()
                    currentLocationBtn.setImageResource(R.drawable.location_selected_icon)
                    isMyLocation = true
                }
            }
            mapView.setCalloutBalloonAdapter(CustomBallonAdapter())
        }
    }


    @SuppressLint("MissingPermission")
    private fun setMarkers() {
        // 사용자의 현재 위치 (위도, 경도)
        val lm: LocationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val userCurrentLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val uLatitude = userCurrentLocation?.latitude
        val uLongitude = userCurrentLocation?.longitude

//        ecomapViewModel.getSurroundEcoStores(uLatitude!!,uLongitude!!)
        ecomapViewModel.ecostores.observe(viewLifecycleOwner) {
            it.forEach {
                val marker = MapPOIItem()
                marker.apply {
                    itemName = it.storeName
                    mapPoint = MapPoint.mapPointWithGeoCoord(it.latitude, it.longitude)
                    markerType = MapPOIItem.MarkerType.CustomImage
                    customImageResourceId = R.drawable.eco_marker
                    isCustomImageAutoscale = false
                    userObject = it
                }
                binding.mapView.addPOIItem(marker)
            }
        }
    }
    private fun startTracking() {
        binding.mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }
    private fun stopTracking() {
        binding.mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
    }
    
    // 커스텀 말풍선 클래스
    inner class CustomBallonAdapter: CalloutBalloonAdapter {
        lateinit var storeInfo: StoreInfo
        override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
            // 마커 클릭 시 나오는 말풍선
            storeInfo = poiItem?.userObject as StoreInfo
            itemBalloonBinding.apply {
                ecostoreName.text = storeInfo.storeName
                ecostoreDesc.text = storeInfo.description
                if (storeInfo.liked) {
                    likeIcon.setImageResource(R.drawable.green_hart)
                } else {
                    likeIcon.setImageResource(R.drawable.green_border_hart)
                }
                likeCnt.text = storeInfo.likeCnt.toString()
            }
            return itemBalloonBinding.root
        }

        override fun getPressedCalloutBalloon(poiItem: MapPOIItem?): View {
            return itemBalloonBinding.root
        }
    }
}