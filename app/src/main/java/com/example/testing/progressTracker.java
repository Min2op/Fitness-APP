package com.example.testing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class progressTracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progress_tracker);
        BarChart chart = findViewById(R.id.firstChart);

        chart.animateXY(2000, 2000);
        Description description = chart.getDescription();
        description.setText("Weight loss over 7 days (KG)");
        description.setPosition(710, 100);
        description.setTextSize(15f);
        description.setTextColor(R.color.teal_200);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setFitBars(true);
        BarDataSet dataSet = new BarDataSet(dataValues(), "Data set 1");
        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.invalidate(); // refresh
    }

    private ArrayList<BarEntry> dataValues(){
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(1, 80.5f));
        dataVals.add(new BarEntry(2, 80.3f ));
        dataVals.add(new BarEntry(3, 80.1f));
        dataVals.add(new BarEntry(4, 79.8f));
        dataVals.add(new BarEntry(5,79.6f));
        dataVals.add(new BarEntry(6,79.4f));
        dataVals.add(new BarEntry(7,79));

        return dataVals;

    }
}