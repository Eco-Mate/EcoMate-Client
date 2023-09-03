package com.example.ecomate.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecomate.databinding.FragmentEcomapBinding
import com.example.ecomate.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
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
        startTracking()
        setUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTracking()
    }

    private fun setUi() {
        binding.apply {
            currentLocationBtn.setOnClickListener {
                stopTracking()
                startTracking()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startTracking() {
        binding.mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

        val lm: LocationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val userCurrentLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        // 사용자의 현재 위치 (위도, 경도)
        val uLatitude = userCurrentLocation?.latitude
        val uLongitude = userCurrentLocation?.longitude
        val uCurrentPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)

        // 현재 사용자 위치에 마커 표시
        val marker = MapPOIItem()
        marker.itemName = "내 위치"
        marker.mapPoint = uCurrentPosition
        marker.markerType = MapPOIItem.MarkerType.RedPin
        marker.selectedMarkerType = MapPOIItem.MarkerType.BluePin
        binding.mapView.addPOIItem(marker)
    }
    private fun stopTracking() {
        binding.mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
    }
}