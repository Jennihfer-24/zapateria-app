package cl.virginio.gomez.zapateria.ev3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import cl.virginio.gomez.zapateria.ev3.dto.UsuarioDto;


public class RegistroUsuarios extends AppCompatActivity {

    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;

    private EditText nombres;
    private EditText apellidoPat;
    private EditText apellidoMat;
    private EditText rut;
    private EditText contrasenia;
    private EditText telefono;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuarios);
        nombres = findViewById(R.id.editNombres);
        apellidoPat = findViewById(R.id.editApPaterno);
        apellidoMat = findViewById(R.id.editApMaterno);
        email = findViewById(R.id.editMail);
        rut = findViewById(R.id.editRut);
        contrasenia = findViewById(R.id.editContrasenia);
        telefono = findViewById(R.id.editTelefono);
        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Button buttonRegistrar = findViewById(R.id.buttonRegistrarCli);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "Boton presionado para Registrar Usuario");
                String nombresStr = nombres.getText().toString().trim();
                String apellidoPatStr = apellidoPat.getText().toString().trim();
                String apellidoMatStr = apellidoMat.getText().toString().trim();
                String rutStr = rut.getText().toString().trim();
                String contraseniaStr = contrasenia.getText().toString().trim();
                String telefonoStr = telefono.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                UsuarioDto cliente = new UsuarioDto(nombresStr, apellidoPatStr, apellidoMatStr,
                         telefonoStr, emailStr, rutStr, contraseniaStr);

                if (clienteValido(cliente)) {
                    registerUser(cliente);
                }

            }
        });
    }

    private boolean clienteValido(UsuarioDto cliente) {
        if (cliente.getNombres().isEmpty() || cliente.getApellidoPat().isEmpty()
                || cliente.getApellidoMat().isEmpty() || cliente.getEmail().isEmpty()
                || cliente.getTelefono().isEmpty() || cliente.getRut().isEmpty()
        || cliente.getPassword().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Ingrese los datos faltantes",Toast.LENGTH_SHORT).show();
            return false;
        } else if (cliente.getPassword().length() < 6) {
            Toast.makeText(getApplicationContext(),
                    "Contraseña debe ser igual o mayor a 6 digitos",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void registerUser(UsuarioDto usuarioDto) {
        mAuth.createUserWithEmailAndPassword(usuarioDto.getEmail(), usuarioDto.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("nombres", usuarioDto.getNombres());
                map.put("apellido_paterno", usuarioDto.getApellidoPat());
                map.put("apellido_materno", usuarioDto.getApellidoMat());
                map.put("rut", usuarioDto.getRut());
                map.put("password", usuarioDto.getPassword());
                map.put("telefono", usuarioDto.getTelefono());
                map.put("email", usuarioDto.getEmail());

                mfirestore.collection("usuarios").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        Toast.makeText(getApplicationContext(), "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }


}