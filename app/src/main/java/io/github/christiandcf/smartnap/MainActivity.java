package io.github.christiandcf.smartnap;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {


    private TextView mUpdateText, mProblem;
    private Button mStarter, mGenerator, mConfirm, mButton5, mButton10, mButton15, mButton20, mButton25, mButton30;
    private EditText mAnswer;
    private CountDownTimer timer;
    private MediaPlayer mPlayer;
    private int time, op1, op2, operator, result;
    private static final String FORMAT = "%02d:%02d";

    private boolean running, pressed;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Sets the Buttons
        mStarter = (Button) findViewById(R.id.starter);
        mGenerator = (Button) findViewById(R.id.generator);
        mConfirm = (Button) findViewById(R.id.confirm);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton10 = (Button) findViewById(R.id.button10);
        mButton15 = (Button) findViewById(R.id.button15);
        mButton20 = (Button) findViewById(R.id.button20);
        mButton25 = (Button) findViewById(R.id.button25);
        mButton30 = (Button) findViewById(R.id.button30);

        // Answer EditTest
        mAnswer = (EditText) findViewById(R.id.answer);

        // Sets the Text
        mUpdateText = (TextView) findViewById(R.id.updateText);
        mProblem = (TextView) findViewById(R.id.problem);

        // Create Timer
        timer = null;

        // Give time to the Buttons
        //five minutes is 300000
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 10000;
                pressed = true;
            }
        });
        mButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 600000;
                pressed = true;
            }
        });
        mButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 900000;
                pressed = true;
            }
        });
        mButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1200000;
                pressed = true;
            }
        });
        mButton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1500000;
                pressed = true;
            }
        });
        mButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1800000;
                pressed = true;
            }
        });

        // creating player


        mStarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer = MediaPlayer.create(MainActivity.this, R.raw.rickroll);
                mPlayer.setLooping(true);
                if (pressed) {
                    timer = new CountDownTimer(time, 1000) { // adjust the milli seconds here
                        public void onTick(long millisUntilFinished) {
                            mUpdateText.setText("Napping for: "+String.format(FORMAT,
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                        }

                        public void onFinish() {
                            mUpdateText.setText("SOLVE IT!");
                            running = true;
                            //music
                            mPlayer.start();
                        }

                    }.start();
                    mStarter.setVisibility(View.INVISIBLE);
                }
            }
        });


        mGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    op1 = new Random().nextInt(100) + 1;
                    op2 = new Random().nextInt(100) + 1;

                    operator = new Random().nextInt(3);

                    if (operator == 0) {
                        mProblem.setText(op1 + " + " + op2);
                    } else if (operator == 1) {
                        mProblem.setText(op1 + " - " + op2);
                    } else if (operator == 2) {
                        mProblem.setText(op1 + " * " + op2);
                    }
                }
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result = 0;

                if (operator == 0) {
                    result = op1 + op2;
                } else if (operator == 1) {
                    result = op1 - op2;
                } else if (operator == 2) {
                    result = op1 * op2;
                }

                // Check if the answer is equal to the result
                if (running) {
                    if (mAnswer.getText().toString().equals("")) {
//                        Toast.makeText(getApplicationContext(), "Answer the question...", Toast.LENGTH_SHORT).show();
                    } else {
                        int answer = Integer.parseInt(mAnswer.getText().toString());
                        if (result == answer) {
                            pressed = false;
                            running = false;
                            timer.cancel();
                            mPlayer.stop();
                            mStarter.setVisibility(View.VISIBLE);
                            mProblem.setText("Problem");
                            Toast.makeText(getApplicationContext(), "Well Played", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Keep Trying", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

}