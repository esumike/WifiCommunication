package com.example.androidrecordtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;

public class VolumeButtonActivity extends Activity {

	TextView txtview;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_volume_button);
        
        txtview = new TextView(this);
        txtview.setText("Presiona botones de volumen");
        setContentView(txtview);    
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) 
    {    	
        int action = event.getAction();
        int keycode = event.getKeyCode();
            switch (keycode) 
            {
	            case KeyEvent.KEYCODE_VOLUME_UP:
	                if (action == KeyEvent.ACTION_DOWN) 
	                {                	
	                	txtview.setText("llamando...");
	                }
	                else if (action == KeyEvent.ACTION_UP)
	                {
	                	txtview.setText("enviando...");
	                }
	                return true;
	           
	            default:
	                return super.dispatchKeyEvent(event);
            }            
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_volume_button, menu);
        return true;
    }
}
