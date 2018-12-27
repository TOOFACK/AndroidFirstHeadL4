package com.example.paulr.androidfirstheadl4;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopWatch extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRun;
    private boolean wasStop;
    private boolean wasReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }

    public void Start(View view) {
        running = true;

    }

    public void Reset(View view) {
        running = false;
        seconds = 0;
        wasReset = true;
    }

    public void Stop(View view) {
        running = false;
        wasStop = true;


    }

    private void runTimer(){
        final TextView timer = findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int min = seconds/60;
                int sec = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, min,sec);

                timer.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }
    
    @Override
    protected void onPause() {
        super.onPause();
        if((wasStop)||(wasReset)){
            wasReset = true;
            wasStop = true;
            wasRun = false;
            running =false;
        } else {
            wasReset = false;
            wasStop = false;
            running = true;
            wasRun = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRun) {
            running = true;
        }else  {
            running = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
