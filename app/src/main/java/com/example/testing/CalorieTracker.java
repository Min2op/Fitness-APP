package com.example.testing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalorieTracker extends AppCompatActivity {
    private Integer prog = 0;
    Integer x = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);
        EditText amountToIncrease = findViewById(R.id.calorieChanger);
        Button increase = findViewById(R.id.increasTracker);
        Button reset = findViewById(R.id.resetCalorieTracker);
        ProgressBar Wheel = findViewById(R.id.progress_Bar);
        updateProgressCircle();

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prog >= 100){
                    Wheel.setProgress(100);
                }
                else{
                    prog += 10;
                    x -= 200;
                    updateProgressCircle();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetProgress();
            }
        });
    }

    public void resetProgress() {
        ProgressBar Wheel = findViewById(R.id.progress_Bar);
        TextView textView = findViewById(R.id.progressTextView);
        TextView textView2 = findViewById(R.id.caloriesRemainingValue);
        textView2.setText("2000");
        Wheel.setProgress(0);
        textView.setText("0");
        x = 2000;
        prog = 0;
    }
    public void updateProgressCircle(){

        ProgressBar Wheel = findViewById(R.id.progress_Bar);
        TextView textView = findViewById(R.id.progressTextView);
        TextView textView2 = findViewById(R.id.caloriesRemainingValue);
        textView2.setText(x.toString());
        Wheel.setProgress(prog);
        textView.setText(prog.toString() + "%");

    }
}
