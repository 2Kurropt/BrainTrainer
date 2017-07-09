package com.example.gideon.braintrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    private Button startButton, button, button1, button2, button3;
    private TextView questionTextView, resultTextView, pointsTextView;
    private int locationOfCorrectAnswer, score = 0, numOfQuestions = 0;

    private void initialize() {
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        startButton = (Button) findViewById(R.id.startButton);
        button = (Button) findViewById(R.id.ans1);
        button1 = (Button) findViewById(R.id.ans2);
        button2 = (Button) findViewById(R.id.ans3);
        button3 = (Button) findViewById(R.id.ans4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        generateQuestion();


    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
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
