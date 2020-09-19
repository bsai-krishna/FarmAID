package com.example.sih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CropPredictionActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG = "CropPredictionActivity";
    private LineChart lineChart;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList<Entry> values = new ArrayList<>();
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prediction);

        String state = getIntent().getStringExtra("state");
        String district = getIntent().getStringExtra("district");
        final String crop = getIntent().getStringExtra("crop");

        lineChart = findViewById(R.id.line_chart);

        lineChart.setOnChartGestureListener(CropPredictionActivity.this);
        lineChart.setOnChartValueSelectedListener(CropPredictionActivity.this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        final LineDataSet set = new LineDataSet(values, "Data set");
        set.setFillAlpha(110);

        databaseReference.child(state).child(crop).child(district).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                values = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    values.add(new Entry(dataSnapshot.child("Year").getValue(Float.class)
                            , dataSnapshot.child("Yield").getValue(Float.class)));
                }
                Log.d(TAG, "onDataChange: " + values);
                lineDataSet.setValues(values);
                lineDataSet.setLabel("Yield of " + crop);
                dataSets.clear();
                dataSets.add(lineDataSet);
                LineData lineData = new LineData(dataSets);
                lineChart.clear();
                lineChart.setData(lineData);
                lineChart.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}