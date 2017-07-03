package com.colegioapp.appadivina;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class juguemos extends AppCompatActivity implements View.OnClickListener{

    // Declaración de las variables
    ArrayList<Integer> imagenesID;
    ArrayList<String> nombres;

    CardView c1, c2, c3 ;
    ImageView iv;
    Button bsig;
    Boolean acierta = true;
    TextView tx1, tx2, tx3, respuestaCorrecta;
    TextView titulo_app_bar;

    public static int rc = 0; // rc = respuesta correcta, es decir junta la imagen con su nombre
    public static int r1 = 1; // r1 es una de las posibles respuestas falsas
    public static int r2 = 2; // r2 es una de las posibles respuestas falsas
    public static int c;      // c es el que guarda si llamo de jugar o de aprender en el inicio
    public static int tipo;   // tipo == c pero sirve para saber qué cosas ocultar en los activity

    public static int ordenrc = 0;
        // Array para intercambiar el orden de las respuestas en jugar
    int[] pos = {0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juguemos);

        titulo_app_bar = (TextView) findViewById(R.id.titulo_app_bar);

        // El botón del app bar
        callVolverAtras();

        // Cada una de las respuestas del jugar
        c1 = (CardView) findViewById(R.id.c1);
        c2 = (CardView)findViewById(R.id.c2);
        c3 = (CardView)findViewById(R.id.c3);

        // Los string que contienen c1, c2 y c3 de arriba
        tx1 = (TextView) findViewById(R.id.resp1);
        tx2 = (TextView)findViewById(R.id.resp2);
        tx3 = (TextView)findViewById(R.id.resp3);

        // Texto "correcto" que sale al acertar en el juego
        respuestaCorrecta = (TextView) findViewById(R.id.textC);

        // Botón se "siguiente" que sale en juego/aprender
        bsig = (Button)findViewById(R.id.siguiente);

        // Imagen que muestra el dibujo en la parte superior
        iv = (ImageView) findViewById(R.id.imagen);

        // Obtengo el intent que se ha generado en el inicio
        Intent i = getIntent();

        // Cojo el valor que trae para saber si he pulsado jugar/aprender
        c = i.getIntExtra("Jugar",-1);
        tipo = i.getIntExtra("tipo",-1);

        // Depende de si quiero animales, números o calle, inicializo los elementos
        switch (c){
            case 0:
                inicializarAnimales();
                break;
            case 1:
                inicializaNumeros();
                break;
            case 2:
                inicializaCalle();
                break;
        }

        // Si t = 0 es porque he elegido "aprender" y entonces..
        if(tipo == 0){

            titulo_app_bar.setText("Aprende conmigo");

            // oculto el cardview con la respuesta 2
            c2.setVisibility(View.INVISIBLE);

            // oculto el cardview con la respuesta 3
            c3.setVisibility(View.INVISIBLE);

            // muestro el botón de siguiente
            bsig.setVisibility(View.VISIBLE);

            // Luego llamamos a aprende, que contiene animaciones
            rellenarAprende();

        }else{

            titulo_app_bar.setText("¡Vamos a jugar!");
            // Si t != 0 es porque he elegido "jugar" y entonces..
            // habilito los 3 botones con las respuestas
            c1.setOnClickListener(this);
            c2.setOnClickListener(this);
            c3.setOnClickListener(this);

            // Oculto el botón de siguiente
            bsig.setVisibility(View.INVISIBLE);

            // Luego llamamos a rellenarJuego

            rellenarJuego();
        }

        // Habilito el click para el botón siguiente
        bsig.setOnClickListener(this);

    }

    public void callVolverAtras(){

        // Localizamos el botón en la vista
        final ImageButton atras = (ImageButton) findViewById(R.id.boton_atras);

        // Creamos el Listener
        atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                rc = 0;
                finish();
            }
        });
    }

    // Cargo la imagen inicial y la respuesta
    public void rellenarAprende(){

        iv.setBackgroundResource(imagenesID.get(rc));
        tx1.setText(nombres.get(rc));

        // Animamos el activity: al botón y la imagen
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.aparecer);
        anim1.reset();
        iv.startAnimation(anim1);
        anim1.reset();
        c1.startAnimation(anim1);
    }

    // Cargo las imágenes y los nombres de los animales
    public void inicializarAnimales(){

        imagenesID = new ArrayList();
        imagenesID.add(R.drawable.cerdo);
        imagenesID.add(R.drawable.elefante);
        imagenesID.add(R.drawable.erizo);
        imagenesID.add(R.drawable.gato);
        imagenesID.add(R.drawable.leon);
        imagenesID.add(R.drawable.loro);
        imagenesID.add(R.drawable.perro);
        imagenesID.add(R.drawable.pez);
        imagenesID.add(R.drawable.tortuga);
        imagenesID.add(R.drawable.vaca);

        nombres = new ArrayList();
        nombres.add("Cerdo");
        nombres.add("Elefante");
        nombres.add("Erizo");
        nombres.add("Gato");
        nombres.add("León");
        nombres.add("Loro");
        nombres.add("Perro");
        nombres.add("Pez");
        nombres.add("Tortuga");
        nombres.add("Vaca");

    }

    // Cargo las imágenes y los nombres de los números
    public void inicializaNumeros(){

        imagenesID = new ArrayList();
        imagenesID.add(R.drawable.uno);
        imagenesID.add(R.drawable.dos);
        imagenesID.add(R.drawable.tres);
        imagenesID.add(R.drawable.cuatro);
        imagenesID.add(R.drawable.cinco);
        imagenesID.add(R.drawable.seis);
        imagenesID.add(R.drawable.siete);
        imagenesID.add(R.drawable.ocho);
        imagenesID.add(R.drawable.nueve);
        imagenesID.add(R.drawable.diez);

        nombres = new ArrayList();
        nombres.add("Uno");
        nombres.add("Dos");
        nombres.add("Tres");
        nombres.add("Cuatro");
        nombres.add("Cinco");
        nombres.add("Seis");
        nombres.add("Siete");
        nombres.add("Ocho");
        nombres.add("Nueve");
        nombres.add("Diez");
    }

    // Cargo las imágenes y los nombres de los elementos de la calle
    public void inicializaCalle(){

        imagenesID = new ArrayList();
        imagenesID.add(R.drawable.coche);
        imagenesID.add(R.drawable.camion);
        imagenesID.add(R.drawable.moto);
        imagenesID.add(R.drawable.bicicleta);
        imagenesID.add(R.drawable.autobus);
        imagenesID.add(R.drawable.casa);
        imagenesID.add(R.drawable.parque);
        imagenesID.add(R.drawable.arbol);
        imagenesID.add(R.drawable.peaton);
        imagenesID.add(R.drawable.calle);

        nombres = new ArrayList();
        nombres.add("Coche");
        nombres.add("Camión");
        nombres.add("Moto");
        nombres.add("Bicicleta");
        nombres.add("Autobús");
        nombres.add("Casa");
        nombres.add("Parque");
        nombres.add("Árbol");
        nombres.add("Peatón");
        nombres.add("Calle");
    }

    // Devuelve un número random entre 0 y 9
    public  int generarRandom(){
        return (int) (Math.random() * 10) ;
    }



    // Cargo la imagen inicial y las tres respuestas
    public void rellenarJuego() {
        respuestaCorrecta.setVisibility(View.GONE);
        ordenrc=(int) (Math.random() * 3);
        switch (ordenrc){
            case 0:
                tx1.setText(nombres.get(rc));
                tx2.setText(nombres.get(r1));
                tx3.setText(nombres.get(r2));
                break;
            case 1:
                tx2.setText(nombres.get(rc));
                tx1.setText(nombres.get(r1));
                tx3.setText(nombres.get(r2));
                break;
            case 2:
                tx3.setText(nombres.get(rc));
                tx2.setText(nombres.get(r1));
                tx1.setText(nombres.get(r2));
                break;
        }


        iv.setBackgroundResource(imagenesID.get(rc));
    }

    // Genero las tres respuestas hasta que sean distintas
    public void generaIndiceRespuesta(){
        // Uso este valor para también escoger la img correspondiente y su nombre
        rc = generarRandom();

        // Genero dos random para las respuestas falsas
        r1 = generarRandom();
        r2 = generarRandom();

        while (r1 == rc) {
            r1 = generarRandom();
        }

        while (r2 == r1 || r2 == rc) {
            r2 = generarRandom();
        }
    }

    public void acierto(){
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.aparecer);

        bsig.setText("Siguiente");
        bsig.setVisibility(View.VISIBLE);
        respuestaCorrecta.setText("¡CORRECTO!");
        respuestaCorrecta.setTextColor(Color.GREEN);
        respuestaCorrecta.setVisibility(View.VISIBLE);
        respuestaCorrecta.startAnimation(anim);
    }

    public void fallo(){
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.desvanecer);
        anim.setFillAfter(true);
        respuestaCorrecta.startAnimation(anim);
        respuestaCorrecta.setText("Prueba otra vez");
        respuestaCorrecta.setTextColor(Color.RED);
        respuestaCorrecta.setVisibility(View.VISIBLE);
        respuestaCorrecta.startAnimation(anim);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // Si pulso el botón "siguiente"
            case R.id.siguiente:
                // y selecciono aprender
                if (tipo == 0){
                    // reseteo la respuesta correcta
                    rc++;

                    // hago modulo para que de la vuelta siempre
                    rc = rc % 10;

                    // inicializo el aprender

                    rellenarAprende();

                }else{
                    // si selecciono juego entonces
                    generaIndiceRespuesta();

                    c1.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);

                    // Ocultamos el botón de siguiente y el
                    // de la respuesta correcta
                    bsig.setVisibility(View.GONE);
                    respuestaCorrecta.setVisibility(View.GONE);

                    // Y rellenamos el juego nuevamente

                    rellenarJuego();
                }
                 break;

            case R.id.c1:
                // Si es la respuesta correcta, oculto las demás, habilito el botón
                // de siguiente y muestro el mensaje de correcto
                if(tx1.getText() == nombres.get(rc)){
                    acierta = true;

                    c2.setVisibility(View.INVISIBLE);
                    c3.setVisibility(View.INVISIBLE);


                    acierto();

                }else{
                    //Sino, hacemos lo contrario
                    acierta = false;
                    fallo();
                }
                break;

            // idem para la respuesta 2
            case R.id.c2:
                if(tx2.getText() == nombres.get(rc)){
                    acierta = true;
                    bsig.setText("Siguiente");
                    c1.setVisibility(View.INVISIBLE);
                    c3.setVisibility(View.INVISIBLE);

                    acierto();

                }else{
                    acierta = false;
                    fallo();
                }
                break;
            // idem para la respuesta 3
            case R.id.c3:
                if(tx3.getText() == nombres.get(rc)){
                    acierta = true;
                    bsig.setText("Siguiente");
                    c1.setVisibility(View.INVISIBLE);
                    c2.setVisibility(View.INVISIBLE);

                    acierto();

                }else{
                    acierta = false;
                    fallo();
                }
                break;
        }

    }
}
