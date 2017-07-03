package com.colegioapp.appadivina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

// Clase que se encarga de cargar el activity correspondiente a
// cada botón del menú principal


public class menu_main extends AppCompatActivity {
    CardView aprende, jugar, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        llamarAprender();
        llamarJugar();
        llamarSalir();
    }

    public void llamarJugar(){
        jugar = (CardView)findViewById(R.id.jugar);
        jugar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                llamarJuguemos();
            }
        });
    }

    public void llamarSalir(){

        salir = (CardView)findViewById(R.id.salir);
        salir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    public void llamarAprender(){

        aprende = (CardView)findViewById(R.id.aprender);
        aprende.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                llamarAprendeCONMIGO();
            }
        });
    }

    public void llamarAprendeCONMIGO(){

        Intent intent = new Intent(this, AprendeConmigo.class);
        // Le aviso a la nueva activity que el botón que la incova
        // es el de "aprender conmigo"
        intent.putExtra("main", 0);
        startActivityForResult(intent,0);
    }

    public void llamarJuguemos(){

        Intent intent = new Intent(this, AprendeConmigo.class);
        // Le aviso a la nueva activity que el botón que la incova
        // es el de "juguemos"
        intent.putExtra("main", 1);
        startActivityForResult(intent,0);
    }
}
