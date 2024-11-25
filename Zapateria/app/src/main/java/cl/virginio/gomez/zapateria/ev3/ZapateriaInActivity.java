package cl.virginio.gomez.zapateria.ev3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ZapateriaInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zapateria_in);


        Button buttonMap = findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "Boton presionado para mapa");
                Intent intent = new Intent(ZapateriaInActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });


        Button buttonProductos = findViewById(R.id.buttonProductos);
        buttonProductos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "Boton presionado para registro");
                Intent intent = new Intent(ZapateriaInActivity.this, ProductosActivity.class);
                startActivity(intent);

            }
        });
    }
}