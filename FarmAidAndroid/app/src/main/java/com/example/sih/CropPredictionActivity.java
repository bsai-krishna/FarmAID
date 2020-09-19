package com.example.sih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class CropPredictionActivity extends AppCompatActivity {

    private static final String TAG = "CropPredictionActivity";
    private LineChart lineChart;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList<Entry> values = new ArrayList<>();
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private int district_encode = 0;

    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prediction);

        final String state = getIntent().getStringExtra("state");
        final String district = getIntent().getStringExtra("district");
        final String crop = getIntent().getStringExtra("crop");

        Log.d(TAG, "onCreate: " + state + " " + district + " " + crop);

        lineChart = findViewById(R.id.line_chart);

//        lineChart.setOnChartGestureListener(CropPredictionActivity.this);
//        lineChart.setOnChartValueSelectedListener(CropPredictionActivity.this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        final LineDataSet set = new LineDataSet(values, "Data set");
        set.setFillAlpha(110);

        databaseReference.child(state).child(crop).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<String> districts = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        districts.add(dataSnapshot.getKey());
                    }
                    district_encode = districts.indexOf(district);
                    Log.d(TAG, "District: " + districts.indexOf(district));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                databaseReference.child(state).child(crop).child(district).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        values = new ArrayList<>();
                        Log.d(TAG, "onDataChange: " + district);
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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
            }}, 1500, 10000);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (state.equals("Uttar Pradesh")) {
            if (crop.equals("Rice")) {
                Call<String> call = service.getUpRice(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Wheat")) {
                Call<String> call = service.getUpWheat(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Sugarcane")) {
                Call<String> call = service.getUpSugarcane(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else if (state.equals("Maharashtra")) {
            if (crop.equals("Cotton")) {
                Call<String> call = service.getMhCotton(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Arhar")) {
                Call<String> call = service.getMhArhar(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Rice")) {
                Call<String> call = service.getMhRice(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Soyabean")) {
                Call<String> call = service.getMhSoyabean(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else if (state.equals("Haryana")) {
            if (crop.equals("Rice")) {
                Call<String> call = service.getHrRice(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Wheat")) {
                Call<String> call = service.getHrWheat(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: " + state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (crop.equals("Sugarcane")) {
            //TODO: Add API Call
            }
        }
        else if(state.equals("Bihar")){
            if(crop.equals("Rice")){
                //TODO: Add API Call
            }
            else if(crop.equals("Maize")){
                Call<String> call = service.getBrMaize(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+ state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(crop.equals("Wheat")){
                //TODO: Add API Call
            }
        }
        else if(state.equals("Punjab")){
            if(crop.equals("Rice")){
                Call<String> call = service.getPbRice(new APIObject(2020, district_encode));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: " + state + response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CropPredictionActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(crop.equals("Maize")){
                //TODO: Add API Call
            }
            else if(crop.equals("Wheat")){
                //TODO: Add API Call
            }
        }
    }
}