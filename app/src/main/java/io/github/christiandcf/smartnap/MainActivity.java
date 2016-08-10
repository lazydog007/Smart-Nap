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


    TextView mUpdateText, mProblem;
    Button mStarter, mGenerator, mButton5, mButton10, mButton15, mButton20, mButton25, mButton30;
    EditText mAnswer;
    private int time;
    private int checker;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Sets the Buttons
        mButton5 = (Button) findViewById(R.id.button5);
        mButton10 = (Button) findViewById(R.id.button10);
        mButton15 = (Button) findViewById(R.id.button15);
        mButton20 = (Button) findViewById(R.id.button20);
        mButton25 = (Button) findViewById(R.id.button25);
        mButton30 = (Button) findViewById(R.id.button30);
        mStarter = (Button) findViewById(R.id.starter);
        mGenerator = (Button) findViewById(R.id.generator);

        mAnswer = (EditText) findViewById(R.id.answer);

        // Sets the Text
        mUpdateText = (TextView) findViewById(R.id.updateText);

        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //five minutes is 300000
                time = 30000;
            }
        });

        mButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 6000000;
            }
        });

        mStarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker++;

                if (checker == 1) {
                    CountDownTimer timer = new CountDownTimer(time, 1000) { // adjust the milli seconds here
                        public void onTick(long millisUntilFinished) {

                            mUpdateText.setText("Napping for: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            mUpdateText.setText("SOLVE IT!");
                            checker = 0;
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
                Toast.makeText(getApplicationContext(), "GET REKT!!!!", Toast.LENGTH_SHORT).show();

//                int op1 = 5;
//                int op2 = 5;
//                int result = op1 + op2;
//                Double answer = Double.parseDouble(mAnswer.getText().toString());
//                mProblem.setText(op1 + " + " + op2);
//
//                if (result == answer){
//                    Toast.makeText(getApplicationContext(), "GG, WP", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(), "GET REKT!!!!", Toast.LENGTH_SHORT).show();
//                }

            }
        });


    }

}