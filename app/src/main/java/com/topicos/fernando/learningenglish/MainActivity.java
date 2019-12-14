package com.topicos.fernando.learningenglish;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btn1, btn2, btn3, btn4, btn5, btn6;
    TextView textViewTime, textViewScore, textViewStatus, textViewProgress;
    ImageView imageView;
    GifImageView gif;
    SeekBar seekTime;
    RelativeLayout layoutGame;
    LinearLayout layoutStart;
    CountDownTimer timer;
    RadioGroup radioGroup;

    MyImage[] listImages;
    int currentPos;
    int attemps, acerts;
    int time = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindComponents();
        initData();
        initComponents();
    }

    private void bindComponents() {
        btnStart = findViewById(R.id.buttonStart);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewProgress = findViewById(R.id.textViewProgress);
        imageView = findViewById(R.id.imageView);
        gif = findViewById(R.id.gifImageView);
        layoutGame = findViewById(R.id.layoutGame);
        layoutStart = findViewById(R.id.layoutStart);
        radioGroup = findViewById(R.id.radioGroup);
        seekTime = findViewById(R.id.seekTime);
    }

    private void initData() {
        currentPos = -1;
        attemps = 0;
        acerts = 0;
    }

    private void initComponents() {
        layoutGame.setVisibility(View.INVISIBLE);
        textViewStatus.setText("");
        textViewScore.setText("Score: " + acerts + "/" + attemps);
        textViewProgress.setText("Max Time: " + time);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTimer();
                setListImages();
                changeEnableStatus(true);
                initData();
                startGame();
            }
        });

        seekTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                time = progress+1;
                textViewProgress.setText("Max Time " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    private void initTimer(){
        timer = new CountDownTimer(time*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                textViewTime.setText(seconds + "");
                textViewStatus.setText("");
            }

            @Override
            public void onFinish() {
                imageView.setImageResource(R.drawable.finish);
                changeEnableStatus(false);
                textViewTime.setText("0");
                textViewStatus.setText("");
                layoutStart.setVisibility(View.VISIBLE);
                gif.setVisibility(View.INVISIBLE);
                btnStart.setText("Try Again");
            }
        };
    }

    private void changeEnableStatus(boolean status) {
        btn1.setEnabled(status);
        btn2.setEnabled(status);
        btn3.setEnabled(status);
        btn4.setEnabled(status);
        btn5.setEnabled(status);
        btn6.setEnabled(status);
    }

    private void startGame() {
        layoutGame.setVisibility(View.VISIBLE);
        layoutStart.setVisibility(View.GONE);
        changeImage();
        timer.start();
    }

    private void setListImages() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioBody:
                listImages = ListImages.getBodyImages();
                break;
            case R.id.radioAnimal:
                listImages = ListImages.getBodyImages();
                break;
            case R.id.radioFruits:
                listImages = ListImages.getFruitImages();
                break;
            case R.id.radioSport:
                listImages = ListImages.getBodyImages();
        }
    }

    private void changeImage() {
        currentPos = getRandomPosition();
        MyImage image = listImages[currentPos];
        imageView.setImageResource(image.getResource());
        int buttonRand = (int) (Math.random() * 6) + 1;
        setButtonText(buttonRand, image.getName());
        for (int i = 1; i <= 6; i++)
            if (i != buttonRand)
                setButtonText(i, listImages[getRandomPosition()].getName());
    }

    public int getRandomPosition() {
        int pos;
        do {
            pos = (int) (Math.random() * listImages.length);
        } while (pos == currentPos);

        return pos;
    }

    public void setButtonText(int button, String text) {
        switch (button) {
            case 1:
                btn1.setText(text);
                break;
            case 2:
                btn2.setText(text);
                break;
            case 3:
                btn3.setText(text);
                break;
            case 4:
                btn4.setText(text);
                break;
            case 5:
                btn5.setText(text);
                break;
            case 6:
                btn6.setText(text);
        }
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        if (btn.getText().toString().equalsIgnoreCase(listImages[currentPos].getName())) {
            textViewStatus.setText("Correct!!");
            textViewStatus.setTextColor(Color.GREEN);
            acerts++;
            attemps++;
        } else {
            textViewStatus.setText("Incorrect!!");
            textViewStatus.setTextColor(Color.RED);
            attemps++;
        }

        textViewScore.setText("Score: " + acerts + "/" + attemps);
        changeImage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuExit:
                AlertDialog.Builder builder = createAlertDialog("Confirmation", "Are you sure you want EXIT?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.cancel();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog.Builder createAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);
        return builder;
    }
}

