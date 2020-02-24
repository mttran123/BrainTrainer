package com.example.gohasu.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView randomMath;
    int score;
    int numberOfQuestions;
    TextView points;
    GridLayout gridLayout;

    TextView result;

    int sum;

    int randomTag;

    boolean gameIsActive = true;

    CountDownTimer countdown;
    Button playAgainButton;

    public void goButton(View view) {
        Button goButton = findViewById(R.id.goButton);

        playAgainButton = findViewById(R.id.playAgainButton);

        goButton.setVisibility(View.INVISIBLE);

        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);
        randomMath = findViewById(R.id.randomMath);

        final TextView countdownTimer = findViewById(R.id.countdownTextView);

        points = findViewById(R.id.pointsTextView);


        randomMath.setVisibility(View.VISIBLE);
        countdownTimer.setVisibility(View.VISIBLE);
        points.setVisibility(View.VISIBLE);

        generateMath();

         countdown = new CountDownTimer(31000,1000) {
            public void onTick(long millisecondsUntilDone) {
                gameIsActive = true;
                countdownTimer.setText(Integer.toString((int)millisecondsUntilDone/1000) +"s");

            }

            public void onFinish() {
                gameIsActive = false;
                playAgainButton.setVisibility(View.VISIBLE);
                result.setText("Done!");

            }
        }.start();
    }

    public void resetGame(View view ) {
        gameIsActive = true;
        generateMath();
        countdown.start();
        score = 0;
        numberOfQuestions =0;
        playAgainButton.setVisibility(View.INVISIBLE);

        result.setVisibility(View.INVISIBLE);
    }


    public void answerButton(View view) {
        if(gameIsActive) {

            result = findViewById(R.id.resultTextView);


            Button buttonClicked = (Button) view;
            String clickedButtonText = buttonClicked.getText().toString();


            if (sum == Integer.parseInt(clickedButtonText)) {
                score++;
                points.setText(score + "");
                result.setText("CORRECT");
                result.setVisibility(View.VISIBLE);
            } else {
                result.setText("Wrong :/");
                result.setVisibility(View.VISIBLE);
            }
            numberOfQuestions++;
            points.setText(score + "/" + numberOfQuestions);

            generateMath();
        }

    }

    public void generateMath() {
        if(gameIsActive) {

            Random r = new Random();

            int int1 = r.nextInt(20) + 1;
            int int2 = r.nextInt(20) + 1;

            String number1 = Integer.toString(int1);
            String number2 = Integer.toString(int2);

            randomMath.setText(number1 + " + " + number2);

            sum = int1 + int2;

            randomTag = r.nextInt(3) + 0;

            Button b0 = (Button) gridLayout.findViewWithTag("0");
            Button b1 = (Button) gridLayout.findViewWithTag("1");
            Button b2 = (Button) gridLayout.findViewWithTag("2");
            Button b3 = (Button) gridLayout.findViewWithTag("3");

            Button[] buttonArray = {b0,b1,b2,b3};

            for(int i=0; i<4; i++) {
                if(randomTag == i) {
                    buttonArray[i].setText(sum+"");

                } else {
                    int wrongAnswer = (r.nextInt(40) + 1);

                    while(wrongAnswer == sum) {
                        wrongAnswer = r.nextInt(40) + 1;
                    }
                    buttonArray[i].setText(wrongAnswer+"");
                }
            }

//            if (randomTag == 0) {
//                b0.setText(sum + "");
//                b1.setText((r.nextInt(40) + 1) + "");
//                b3.setText((r.nextInt(40) + 1) + "");
//                b2.setText((r.nextInt(40) + 1) + "");
//
//            }
//            if (randomTag == 1) {
//                b1.setText(sum + "");
//                b2.setText((r.nextInt(40) + 1) + "");
//                b3.setText((r.nextInt(40) + 1) + "");
//                b0.setText((r.nextInt(40) + 1) + "");
//
//            }
//            if (randomTag == 2) {
//                b2.setText(sum + "");
//                b1.setText((r.nextInt(40) + 1) + "");
//                b3.setText((r.nextInt(40) + 1) + "");
//                b0.setText((r.nextInt(40) + 1) + "");
//            }
//            if (randomTag == 3) {
//                b3.setText(sum + "");
//                b1.setText((r.nextInt(40) + 1) + "");
//                b2.setText((r.nextInt(40) + 1) + "");
//                b0.setText((r.nextInt(40) + 1) + "");
//            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}