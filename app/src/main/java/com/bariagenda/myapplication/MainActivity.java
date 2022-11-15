package com.bariagenda.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private EditText usuario,password,ed_titulo,ed_descripcion;
    private Button ingresar,registrarse,b_agregar,b_ver;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.etUsuario1);
        password = findViewById(R.id.etPassword1);
        ingresar = findViewById(R.id.btnIngresar);
        ed_titulo = findViewById(R.id.et_titulo);
        ed_descripcion = findViewById(R.id.et_descripcion);

        b_agregar = findViewById(R.id.btn_agregar);
        b_ver = findViewById(R.id.btn_ver);
        DB = new DBHelper(this);


        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "Completa todo los campos",Toast.LENGTH_SHORT).show();
                else{
                    Boolean verificarUser = DB.verificarPassword(user,pass);
                    if (verificarUser==true) {
                        Toast.makeText(MainActivity.this, "Ingreso aceptado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, NotasActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,"Usuario no existe",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Inicialización de los elementos del layout registro biometrico
        TextView msgtex = findViewById(R.id.msgtext);
        final Button loginbutton = findViewById(R.id.login);

        /**
         *  Creación del BiometricManager y comprobación de si el usuario puede o no usar biometría
         */
        androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

            // El dispositivo este habilitado para usar el lector biométrico
            case BiometricManager.BIOMETRIC_SUCCESS:
                msgtex.setText("Dispositivo habilitado para utilizar datos biométricos.");
                break;

            // El dispositivo no contiene lector biométrico
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                msgtex.setText("Este dispositivo no contiene lector de datos biométricos.");
                loginbutton.setVisibility(View.GONE);
                break;

            // El dispositivo cuenta con lector biométrico pero este no se encuentra disponible
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                msgtex.setText("El lector de datos biométricos no se encuentra disponible.");
                loginbutton.setVisibility(View.GONE);
                break;

            // El dispositivo no cuenta con un dato biométrico cargado para realizar la autentificación
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                msgtex.setText("El dispositivo no cuenta con datos biométricos cargados, por favor corrobore sus opciones de seguridad.s");
                loginbutton.setVisibility(View.GONE);
                break;
        }

        /**
         * Creación del Executor
         */
        Executor executor = ContextCompat.getMainExecutor(this);

        /**
         * Otorga el resultado de la validación
         */
        final androidx.biometric.BiometricPrompt biometricPrompt = new androidx.biometric.BiometricPrompt(MainActivity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

            }

            /**
             * Método que se ejecuta al ser correcta la validación
             * @param result
             */
            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                //Toast que muestra un mensaje confirmando la lectura
                Toast.makeText(getApplicationContext(), "Acceso correcto", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,NotasActivity.class);
                startActivity(intent);

            }

            /**
             * Método que se ejecuta al ser incorrecta la validación
             */
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        /**
         * Creación del PrompInfo y cuadro de diálogo que solicita la autenticación del usuario
         */
        final androidx.biometric.BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Verifica tu identidad")
                .setDescription("Coloca el dedo sobre el lector de huella").setNegativeButtonText("Cancelar").build();

        //Acción del botón creado e inicializado previamente que se encuentra en el main
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);

            }
        });

    }

    public void onRegister(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);

    }
}