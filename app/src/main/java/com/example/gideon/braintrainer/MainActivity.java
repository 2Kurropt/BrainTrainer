package com.example.gideon.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    private Button startButton, button, button1, button2, button3, playAgain;
    private TextView questionTextView, resultTextView, pointsTextView, timerTextView;
    private int locationOfCorrectAnswer, score = 0, numOfQuestions = 0;
    private RelativeLayout gameLayout;

    private void initialize() {
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        startButton = (Button) findViewById(R.id.startButton);
        playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.ans1);
        button1 = (Button) findViewById(R.id.ans2);
        button2 = (Button) findViewById(R.id.ans3);
        button3 = (Button) findViewById(R.id.ans4);
        gameLayout = (RelativeLayout) findViewById(R.id.game);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void disableButtons(boolean val) {
        button.setEnabled(val);
        button1.setEnabled(val);
        button2.setEnabled(val);
        button3.setEnabled(val);
    }

    public void playAgain(View view) {
        score = 0;
        numOfQuestions = 0;
        timerTextView.setText("60s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        disableButtons(true);

        playAgain.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(60100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                disableButtons(false);
                timerTextView.setText("0s");
                resultTextView.setText("Game Over!\n You scored " + pointsTextView.getText());
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(playAgain);
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toHexString(locationOfCorrectAnswer))) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong!");
        }
        numOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));

        generateQuestion();
    }

    private void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(101);
        int b = rand.nextInt(101);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAns;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAns = rand.nextInt(202);

                while (incorrectAns == a + b) {
                    incorrectAns = rand.nextInt(202);
                }
                answers.add(incorrectAns);
            }
        }

        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
}
