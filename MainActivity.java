package com.jerry94.w3villaquiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    List<Question> quesList;
    int score = 0;
    int qid = 0;
    int flag = 0;

    Question currentQ = new Question();
    TextView txtQuestion, times, scored;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizHelper db = new QuizHelper(this);  // my question bank class
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        scored = (TextView) findViewById(R.id.score);
        times = (TextView) findViewById(R.id.timers);


        times.setText("00:00:00");
        setQuestionView();
        final CounterClass timer = new CounterClass(15000, 1000);
        timer.start();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("############################");
                getAnswer(button1.getText().toString());
                if(qid == 9)
                {
                    timer.onFinish();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("##################################");
                getAnswer(button2.getText().toString());
                if(qid == 9)
                {
                    timer.onFinish();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("############################");
                getAnswer(button3.getText().toString());
                if(qid == 9)
                {
                    timer.onFinish();
                }
            }
        });
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {
            score++;
            scored.setText("Score : " + score);
        }
//        else {

//            Intent intent = new Intent(MainActivity.this,
//                    ResultActivity.class);
//            Bundle b = new Bundle();
//            b.putInt("score", score);
//            intent.putExtras(b);
//            startActivity(intent);
//            finish();
//        }
        if (qid <= 8) {

            currentQ = quesList.get(qid);
            setQuestionView();
        } else {

            currentQ = quesList.get(qid);
            setQuestionView();
//            button1.setEnabled(false);
//            button1.setBackgroundColor(Color.GRAY);
//            button2.setEnabled(false);
//            button2.setBackgroundColor(Color.GRAY);
//            button3.setEnabled(false);
//            button3.setBackgroundColor(Color.GRAY);
//            Intent intent = new Intent(MainActivity.this,
//                    ResultActivity.class);
//            Bundle b = new Bundle();
//            b.putInt("score", score);
//            intent.putExtras(b);
//            startActivity(intent);
//            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

            times.setText("Oops...Time is up..!!");
            button1.setEnabled(false);
            button1.setBackgroundColor(Color.GRAY);
            button2.setEnabled(false);
            button2.setBackgroundColor(Color.GRAY);
            button3.setEnabled(false);
            button3.setBackgroundColor(Color.GRAY);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();

        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }

    }

    private void setQuestionView() {

        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());
        qid++;
    }
}