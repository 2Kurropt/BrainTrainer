package com.example.gideon.braintrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answer = new ArrayList<Integer>();
    private Button startButton, button, button1, button2, button3;
    private TextView questionTextView;
    private int locationOfCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        button = (Button) findViewById(R.id.ans1);
        button1 = (Button) findViewById(R.id.ans2);
        button2 = (Button) findViewById(R.id.ans3);
        button3 = (Button) findViewById(R.id.ans4);

        Random rand = new Random();
        int a = rand.nextInt(101);
        int b = rand.nextInt(101);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAns;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                incorrectAns = rand.nextInt(202);

                while (incorrectAns == a + b) {
                    incorrectAns = rand.nextInt(202);
                }
                answer.add(incorrectAns);
            }
        }

        button.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view) {

    }
}
