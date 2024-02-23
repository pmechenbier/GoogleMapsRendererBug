package xyz.mechenbier.GoogleMapRendererBug

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*

class GoogleMapRendererBug : AppCompatActivity(), OnMapsSdkInitializedCallback  {

    private lateinit var mGoogleMap: GoogleMap
    private lateinit var mMapViewFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapsInitializer.initialize(applicationContext, MapsInitializer.Renderer.LATEST, this)

        setContentView(R.layout.googlemaprendererbug)

        mMapViewFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment

        mMapViewFragment.getMapAsync { googleMap ->

            mGoogleMap = googleMap
        }
    }

    override fun onMapsSdkInitialized(renderer: MapsInitializer.Renderer) {
        when (renderer) {
            MapsInitializer.Renderer.LATEST -> Log.d("MapsDemo", "The latest version of the renderer is used.")
            MapsInitializer.Renderer.LEGACY -> Log.d("MapsDemo", "The legacy version of the renderer is used.")
        }
    }

    override fun onResume() {
        super.onResume()

        mMapViewFragment.getMapAsync { googleMap ->

            mGoogleMap = googleMap
        }
    }

}