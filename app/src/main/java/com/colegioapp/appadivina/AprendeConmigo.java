package com.colegioapp.appadivina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AprendeConmigo extends AppCompatActivity implements View.OnClickListener{

    CardView animales, numeros, calle;
    ImageButton im;
    int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprende_conmigo);

        callVolverAtras();

        animales = (CardView)findViewById(R.id.animales);
        animales.setOnClickListener(this);

        numeros = (CardView)findViewById(R.id.numeros);
        numeros.setOnClickListener(this);

        calle = (CardView)findViewById(R.id.la_calle);
        calle.setOnClickListener(this);

        im = (ImageButton)findViewById(R.id.boton_atras);
        im.setOnClickListener(this);

        Intent i = getIntent();
        c = i.getIntExtra("main",-1);//coge el valor pasado en la anterior activity//
        // si no recibe ningun valor se le ha dado uno por defecto que sera el -1
        // (aunque es improbable)
    }

    public void callVolverAtras(){

        // Localizamos el botón en la vista
        final ImageButton atras = (ImageButton) findViewById(R.id.boton_atras);

        // Creamos el Listener
        atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                finish();
            }
        });
    }

    @Override
    public void onClick(View v){

        Intent intent = new Intent(this, juguemos.class);

        switch (v.getId()){
            // Botón de los animales
            case R.id.animales:
                if(c == 0){

                    intent.putExtra("tipo", 0);
                    //si ha recibido un 0 en el intent que se ha comunicado con esta activity es
                    // por que en el main se ha pulsado la opcion de aprender por consiguiente
                    // se pasara un valor para que el siguiente activity sepa que funcionalidad
                    // tiene que dar en este caso seria de la funcionalidad aprender que muestre los
                    // animales

                }
                else{

                    //si no ha recibido un 0 en el intent que se ha comunicado con esta activity es
                    // por que en el main se ha pulsado la opcion de jugar por consiguiente
                    // se pasara un valor para que el siguiente activity sepa que funcionalidad
                    // tiene que dar en este caso seria de la funcionalidad jugar que muestre los
                    // animales y sus distintas respuestas para que elija una
                    intent.putExtra("tipo", 1);
                }

                intent.putExtra("Jugar", 0);//
                startActivityForResult(intent,0);
                break;

            // Botón de los números
            case R.id.numeros:
                if(c == 0){
                    //si ha recibido un 0 en el intent que se ha comunicado con esta activity es
                    // por que en el main se ha pulsado la opcion de aprender por consiguiente
                    // se pasara un valor para que el siguiente activity sepa que funcionalidad
                    // tiene que dar en este caso seria de la funcionalidad aprender que muestre los
                    // numeros
                    intent.putExtra("tipo", 0);

                }
                else {

                    //si no ha recibido un 0 en el intent que se ha comunicado con esta activity es
                    // por que en el main se ha pulsado la opcion de jugar por consiguiente
                    // se pasara un valor para que el siguiente activity sepa que funcionalidad
                    // tiene que dar en este caso seria de la funcionalidad jugar que muestre los
                    // numeros de forma aleatoria y sus distintas respuestas para que elija una
                    intent.putExtra("tipo", 1);
                }

                intent.putExtra("Jugar", 1);
                startActivityForResult(intent,0);
                break;

            // Botón de la calle
            case R.id.la_calle:
                if(c == 0){
                    //si ha recibido un 0 en el intent que se ha comunicado con esta activity es
                    // por que en el main se ha pulsado la opcion de aprender por consiguiente
                    // se pasara un valor para que el siguiente activity sepa que funcionalidad
                    // tiene que dar en este caso seria de la funcionalidad aprender que muestre los
                    // objetos de la calle

                    intent.putExtra("tipo", 0);
                }
                else{
                    //si no ha recibido un 0 en el intent que se ha comunicado con esta activity es
                    // por que en el main se ha pulsado la opcion de jugar por consiguiente
                    // se pasara un valor para que el siguiente activity sepa que funcionalidad
                    // tiene que dar en este caso seria de la funcionalidad jugar que muestre los
                    // objetos de la calle  de forma aleatoria y sus distintas respuestas para que elija una
                    intent.putExtra("tipo", 1);
                }
                intent.putExtra("Jugar", 2);
                startActivityForResult(intent,0);
                break;

            // Botón de atrás del app bar
            case R.id.boton_atras:
                finish();
                break;
        }
    }
}
