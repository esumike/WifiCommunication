package com.example.androidrecordtest;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecordTestActivity extends Activity {

	private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private boolean isRecording = false;
    private boolean isPlaying = false;

    //private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    //private PlayButton   mPlayButton = null;
    private MediaPlayer   mPlayer = null;

    /*private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }*/

    private void startPlaying() {
    	if(mPlayer != null) {
    		stopPlaying();
    	}
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
    	if(isRecording)
    		return;
    	
    	if(mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    	}

        try {
        	Log.v("", "before praparing");
            mRecorder.prepare();
            Log.v("", "before starting");
            mRecorder.start();
            Log.v("", "Started");
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopRecording() {
    	try {
	        mRecorder.stop();
	        mRecorder.release();
	        mRecorder = null;
    	}
    	catch (Exception e) {
    		Log.e("Record Stop", "Shit, can't stop recording.");
    		e.printStackTrace();
    	}
    }

    /*class RecordButton extends Button {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    setText("Stop recording");
                } else {
                    setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        }
    }*/

    /*class PlayButton extends Button {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };

        public PlayButton(Context ctx) {
            super(ctx);
            setText("Start playing");
            setOnClickListener(clicker);
        }
    }*/

    public RecordTestActivity() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        TextView a = new TextView(this);
        a.setText("mamepato?");
        setContentView(a);
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {    	
        int action = event.getAction();
        int keycode = event.getKeyCode();
            switch (keycode) 
            {
	            case KeyEvent.KEYCODE_VOLUME_UP:
	                if (action == KeyEvent.ACTION_DOWN) {                	
	                	//onRecord(mStartRecording);
	                	if(isPlaying) {
	                		//stop all players
	                		stopPlaying();
	                		isPlaying = false;
	                		//isPlaying = false;
	                	}
	                    startRecording();

	                	isRecording = true;
	                    
	                }
	                else if (action == KeyEvent.ACTION_UP) {
	                	stopRecording();
	                	isRecording = false;
	                	startPlaying();
	                	isPlaying = true;
	                }
	                return true;
	           
	            default:
	                return super.dispatchKeyEvent(event);
            }            
        }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
