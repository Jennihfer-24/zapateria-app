package cl.virginio.gomez.zapateria.ev3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import cl.virginio.gomez.zapateria.ev3.adapter.ProductoAdapter;
import cl.virginio.gomez.zapateria.ev3.dto.ProductoDto;


public class ProductosActivity extends AppCompatActivity {

    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    ProductoAdapter pAdapter;
    RecyclerView prodRecycler;
    FirebaseFirestore mFirestore;
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productos);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        prodRecycler = findViewById(R.id.recyclerViewProducto);
        prodRecycler.setLayoutManager(new LinearLayoutManager(this));
        query = mFirestore.collection("productos");

        FirestoreRecyclerOptions<ProductoDto> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<ProductoDto>().setQuery(query, ProductoDto.class).build();

        pAdapter = new ProductoAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        pAdapter.notifyDataSetChanged();
        prodRecycler.setAdapter(pAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        pAdapter.stopListening();
    }


}