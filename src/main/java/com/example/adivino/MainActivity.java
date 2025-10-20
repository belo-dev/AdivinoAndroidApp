package com.example.adivino;

import android.os.Handler;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int maximo = 101, minimo = 0, numero = 50, intentos = 0;
    protected TextView adivino, numeroA;
    protected ImageButton mas, menos, acierto, nuevoJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adivino = findViewById(R.id.adivino);
        numeroA = findViewById(R.id.numero);
        mas = findViewById(R.id.mayor);
        menos = findViewById(R.id.menor);
        acierto = findViewById(R.id.acierto);
        nuevoJuego = findViewById(R.id.nuevo);

        mas.setOnClickListener(v -> {
            animacion(mas);
            if (maximo == minimo) {
                adivino.setText("¡No puedes engañarme! Tu número es " + numero);
                return;
            }
            minimo = numero;
            numero = (minimo + maximo) / 2;
            intentos = intentos + 1;
            actualizarTexto();
        });

        menos.setOnClickListener(v -> {
            animacion(menos);
            if (maximo == minimo) {
                adivino.setText("Tu número es " + numero + " Cabezón");
                return;
            }
            maximo = numero;
            numero = (minimo + maximo) / 2;
            intentos = intentos + 1;
            actualizarTexto();
        });

        acierto.setOnClickListener(v -> {
            adivino.setText("¡He acertado, facilito!");
            mas.setVisibility(View.GONE);
            menos.setVisibility(View.GONE);
            acierto.setVisibility(View.GONE);
            nuevoJuego.setVisibility(View.VISIBLE);
        });

        nuevoJuego.setOnClickListener(v -> {
            maximo = 100;
            minimo = 0;
            numero = 50;
            intentos = 0;
            actualizarTexto();
            mas.setVisibility(View.VISIBLE);
            menos.setVisibility(View.VISIBLE);
            acierto.setVisibility(View.VISIBLE);
            nuevoJuego.setVisibility(View.GONE);
        });
    }
    private void actualizarTexto() {
        String miNumero = String.valueOf(numero);
        numeroA.setText(miNumero);
        switch (intentos) {
            case 0:
                adivino.setText("¿Este es tu numero?");
            case 1:
                adivino.setText("Esto va a ser facil");
                break;
            case 2:
                adivino.setText("No te emociones");
                break;
            case 3:
                adivino.setText("Casi lo tengo");
                break;
            case 4:
                adivino.setText("¿Y tu que miras cara almendra?");
                break;
        }
    }

    private void animacion(ImageButton a){

        ViewGroup.LayoutParams params = a.getLayoutParams();
        int dpw = 200;
        int dph = 75;
        float scale = getResources().getDisplayMetrics().density;
        int pxw = (int) (dpw * scale + 0.5f);
        int pxh = (int) (dph * scale + 0.5f);
        params.width = pxw;
        params.height = pxh;
        a.setLayoutParams(params);
        new Handler().postDelayed(() -> {
            ViewGroup.LayoutParams params2 = a.getLayoutParams();
            int dpw2 = 252;
            int dph2 = 95;
            float scale2 = getResources().getDisplayMetrics().density;
            int pxw2 = (int) (dpw2 * scale2 + 0.5f);
            int pxh2 = (int) (dph2 * scale2 + 0.5f);
            params2.width = pxw2;
            params2.height = pxh2;
            a.setLayoutParams(params2);
        }, 200);

    }
}