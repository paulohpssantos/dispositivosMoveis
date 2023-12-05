package com.example.arduinicontrole;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Context context = null;
    private Communication communication = null;

    private TextView mTextLog = null;
    private TextView mTextInfo = null;


    private ImageButton btFrente;
    private ImageButton btTras;
    private ImageButton btEsquerda;
    private ImageButton btDireita;
//    private ImageButton btSubir;
//    private ImageButton btDescer;
//    private ImageButton btAbrir;
//    private ImageButton btFechar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // System
        context = getApplicationContext();

        // Layouts
        setContentView(R.layout.activity_main);


        btFrente = (ImageButton)findViewById(R.id.btFrente);
        btTras = (ImageButton)findViewById(R.id.btTras);
        btEsquerda = (ImageButton)findViewById(R.id.btEsquerda);
        btDireita = (ImageButton)findViewById(R.id.btDireita);
//        btSubir = (ImageButton)findViewById(R.id.btSubir);
//        btDescer = (ImageButton)findViewById(R.id.btDescer);
//        btAbrir = (ImageButton)findViewById(R.id.btAbrir);
//        btFechar = (ImageButton)findViewById(R.id.btFechar);


        btFrente.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        communication.send("frente");
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        communication.send("parar");
                        break;
                }
                return false;
            }
        });

        btTras.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        communication.send("tras");
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        communication.send("parar");
                        break;
                }
                return false;
            }
        });

        btEsquerda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        communication.send("esquerda");
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        communication.send("parar");
                        break;
                }
                return false;
            }
        });
        btDireita.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        communication.send("direita");
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        communication.send("parar");
                        break;
                }
                return false;
            }
        });
//        btSubir.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        communication.send("cima");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        communication.send("parar");
//                        break;
//                }
//                return false;
//            }
//        });
//        btDescer.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        communication.send("baixo");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        communication.send("parar");
//                        break;
//                }
//                return false;
//            }
//        });
//
//        btAbrir.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        communication.send("abrir");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        communication.send("stop");
//                        break;
//                }
//                return false;
//            }
//        });
//        btFechar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        communication.send("fechar");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        communication.send("stop");
//                        break;
//                }
//                return false;
//            }
//        });

        // Initialize
        InputListener inputListener = new InputListener() {
            @Override
            public void onRead(String message) {
                mTextInfo.setText((String) message);
                mTextLog.append((String) message);
                mTextLog.append("\n");
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        };

        // Initialize Serial connector and starts Serial monitoring thread.
        communication = new BluetoothCommunication();
        communication.start(this, inputListener);
        if (!communication.isConnected()) {
            Toast.makeText(this, "Não foi possível conectar. :( Por favor feche a aplicação e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        communication.onActivityResult(requestCode, resultCode, data);
        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //iniciaVariaveis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        communication.stop();
    }
}