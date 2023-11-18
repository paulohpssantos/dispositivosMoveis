package com.hardcopy.arduinocontroller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ArduinoControllerActivity extends Activity {

	private Context context = null;
	private Communication communication = null;
	
	private TextView mTextLog = null;
	private TextView mTextInfo = null;


	private ImageButton btFrente;
	private ImageButton btTras;
	private ImageButton btEsq;
	private ImageButton btDir;
    private ImageButton btCima;
    private ImageButton btBaixo;
	private ImageButton btCima1;
	private ImageButton btBaixo1;
	private Button btAbrir;
	private Button btFechar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// System
		context = getApplicationContext();
		
		// Layouts
		setContentView(R.layout.activity_arduino_controller);
		

		btFrente = (ImageButton)findViewById(R.id.btFrente);
		btTras = (ImageButton)findViewById(R.id.btTras);
		btEsq = (ImageButton)findViewById(R.id.btEsquerda);
		btDir = (ImageButton)findViewById(R.id.btDireita);
        btCima = (ImageButton)findViewById(R.id.btCima);
        btBaixo = (ImageButton)findViewById(R.id.btBaixo);
		//btCima1 = (ImageButton)findViewById(R.id.btCima1);
		//btBaixo1 = (ImageButton)findViewById(R.id.btBaixo1);
		btAbrir = (Button)findViewById(R.id.btAbrir);
		btFechar = (Button)findViewById(R.id.btFechar);

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

		btEsq.setOnTouchListener(new View.OnTouchListener() {
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
		btDir.setOnTouchListener(new View.OnTouchListener() {
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
        btCima.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        communication.send("cima");
                        break;

                    case MotionEvent.ACTION_UP:
                        communication.send("parar");
                        break;
                }
                return false;
            }
        });
        btBaixo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        communication.send("baixo");
                        break;

                    case MotionEvent.ACTION_UP:
                        communication.send("parar");
                        break;
                }
                return false;
            }
        });

//		btCima1.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//					case MotionEvent.ACTION_DOWN:
//						communication.send("baixo1");
//						break;
//
//					case MotionEvent.ACTION_UP:
//						communication.send("stop");
//						break;
//				}
//				return false;
//			}
//		});
//		btBaixo1.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//					case MotionEvent.ACTION_DOWN:
//						communication.send("cima1");
//						break;
//
//					case MotionEvent.ACTION_UP:
//						communication.send("stop");
//						break;
//				}
//				return false;
//			}
//		});
		btAbrir.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						communication.send("abrir");
						break;

					case MotionEvent.ACTION_UP:
						communication.send("stop");
						break;
				}
				return false;
			}
		});
		btFechar.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						communication.send("fechar");
						break;

					case MotionEvent.ACTION_UP:
						communication.send("stop");
						break;
				}
				return false;
			}
		});

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
				Toast.makeText(ArduinoControllerActivity.this, error, Toast.LENGTH_SHORT).show();
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

	//communication.send("led1on");


}
