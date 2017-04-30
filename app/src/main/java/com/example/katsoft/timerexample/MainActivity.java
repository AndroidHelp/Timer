package com.example.katsoft.timerexample;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// created by Hemant Katariya

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_timer;
    private Button btn_start, btn_pause, btn_complete;
    private long startTime = 0L;
    private long startWith = 10000L;
    private Handler myHandler = new Handler();
    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_timer = (TextView) findViewById(R.id.textView_Timer);
        btn_start = (Button) findViewById(R.id.button_Start);
        btn_pause = (Button) findViewById(R.id.button_Pause);
        btn_complete = (Button) findViewById(R.id.button_Complete);

        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_complete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Start:

                startTimer();
                break;
            case R.id.button_Pause:
                pauseTimer();
                break;
            case R.id.button_Complete:
                completeTimer();
                break;
        }
    }

    private void startTimer() {
        startTime = SystemClock.uptimeMillis();
        myHandler.postDelayed(updateTimerMethod, 0);

    }

    private void pauseTimer() {

        timeSwap += timeInMillies;
        myHandler.removeCallbacks(updateTimerMethod);

    }

    private void completeTimer() {
        btn_start.setClickable(false);
        btn_pause.setClickable(false);
        myHandler.removeCallbacks(updateTimerMethod);
    }
    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;

            int seconds = (int) ((finalTime+startWith) / 1000);
            int minutes = seconds / 60;
            int hours   = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            int milliseconds = (int) (finalTime % 1000);
            txt_timer.setText("" + hours + ":"+"" + minutes + ":"
                    + String.format("%02d", seconds));
            myHandler.postDelayed(this, 500);
        }

    };
}
