package io.github.christiandcf.smartnap;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    private TextView mUpdateText, mProblem;
    private Button mStarter, mGenerator, mConfirm, mButton5, mButton10, mButton15, mButton20, mButton25, mButton30;
    private EditText mAnswer;
    private CountDownTimer timer;
    private int time, checker;
    private double op1, op2;

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
                time = 30000;
            }
        });
        mButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 600000;
            }
        });
        mButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 900000;
            }
        });
        mButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1200000;
            }
        });
        mButton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1500000;
            }
        });
        mButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1800000;
            }
        });

        mStarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker++;

                if (checker == 1) {
                    timer = new CountDownTimer(time, 1000) { // adjust the milli seconds here
                        public void onTick(long millisUntilFinished) {
                            mUpdateText.setText("Napping for: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            mUpdateText.setText("SOLVE IT!");
                        }
                    }.start();
                } else {
                    Toast.makeText(getApplicationContext(), "Click on Generate to solve the problem", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op1 = 5;
                op2 = 5;
                mProblem.setText(op1 + " + " + op2);



            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                double result = op1 + op2;
                // Check if the answer is equal to the result
                if (mAnswer.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Answer the question...", Toast.LENGTH_SHORT).show();
                } else {
                    double answer = Double.parseDouble(mAnswer.getText().toString());
                    if (result == answer) {
                        checker = 0;
                        timer.cancel();
                        Toast.makeText(getApplicationContext(), "Well Played", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Keep Trying", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // TODO: Stop Timer, and reset the checker
    }

}