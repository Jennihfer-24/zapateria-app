package cl.virginio.gomez.zapateria.ev3;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import cl.virginio.gomez.zapateria.ev3.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mfirestore = FirebaseFirestore.getInstance();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mfirestore.collection("tiendas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            LatLng tienda = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                double latitud = Double.parseDouble(Objects
                                        .requireNonNull(document.get("latitud")).toString());
                                double longitud = Double.parseDouble(Objects
                                        .requireNonNull(document.get("longitud")).toString());
                                tienda = new LatLng(latitud, longitud);
                                mMap.addMarker(new MarkerOptions().position(tienda)
                                        .title(Objects.requireNonNull(document.get("nombre")).toString()));
                            }
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda, 12));
                            mMap.setMinZoomPreference(4.0F);
                            mMap.setMaxZoomPreference(18.0f);
                        } else {
                            LatLng tienda1 = new LatLng(-36.603367, -72.091297);
                            LatLng tienda2 = new LatLng(-36.604587, -72.083914);
                            LatLng tienda3 = new LatLng(-36.6119628,-72.07268723);
                            LatLng tienda4 = new LatLng(-36.602517302817866,-72.10115649861793);



                            mMap.addMarker(new MarkerOptions().position(tienda1).title("Zapatería 1"));
                            mMap.addMarker(new MarkerOptions().position(tienda2).title("Zapatería 2"));
                            mMap.addMarker(new MarkerOptions().position(tienda3).title("Zapatería 3"));
                            mMap.addMarker(new MarkerOptions().position(tienda4).title("Zapatería 4"));

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tienda1, 15));
                            mMap.setMinZoomPreference(4.0F);
                            mMap.setMaxZoomPreference(18.0f);
                        }
                    }
                });




    }
}