package com.example.sih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String state,dist,season;
    Spinner spinner_crop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);
        Intent intent=getIntent();
        state=intent.getStringExtra("State");
        dist=intent.getStringExtra("District");
        season=intent.getStringExtra("Season");
        Log.d("DETAILS",state+dist+season);
        spinner_crop=findViewById(R.id.spinner_crop);
        setSpinnerData(state,season);


    }
    String []crop;


    private void setSpinnerData(String state, String season) {


        if(state.equals("Uttar Pradesh")){
            if(season.equals("Rabi")){
                crop=new String[]{"Select the crop","Rice","Wheat"};
            }
            else{
                crop=new String[]{"Select the crop","Maize","Rice"};
            }
            ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, crop);
            dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_crop.setAdapter(dist);
            spinner_crop.setOnItemSelectedListener(this);

        }

        if(state.equals("Bihar")){
            if(season.equals("Rabi")){
                crop=new String[]{"Select the crop","Butta","SugarCane"};
            }
            else{
                crop=new String[]{"Select the crop","Maize","Rice"};
            }
            ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, crop);
            dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_crop.setAdapter(dist);
            spinner_crop.setOnItemSelectedListener(this);

        }


        if(state.equals("Punjab")){
            if(season.equals("Rabi")){
                crop=new String[]{"Select the crop","Rice","Wheat"};
            }
            else{
                crop=new String[]{"Select the crop","Maize","Rice"};
            }
            ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, crop);
            dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_crop.setAdapter(dist);
            spinner_crop.setOnItemSelectedListener(this);

        }

        if(state.equals("Maharastra")){
            if(season.equals("Rabi")){
                crop=new String[]{"Select the crop","Rice","Wheat"};
            }
            else{
                crop=new String[]{"Select the crop","Maize","Rice"};
            }
            ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, crop);
            dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_crop.setAdapter(dist);
            spinner_crop.setOnItemSelectedListener(this);

        }


        if(state.equals("Haryana")){
            if(season.equals("Rabi")){
                crop=new String[]{"Select the crop","Rice","Wheat"};
            }
            else{
                crop=new String[]{"Select the crop","Maize","Rice"};
            }
            ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, crop);
            dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_crop.setAdapter(dist);
            spinner_crop.setOnItemSelectedListener(this);

        }
    }
    String crop_string;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        crop_string=spinner_crop.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void next_step(View view) {
        if(view.getId()==R.id.yield_pred){
            if(crop_string.isEmpty() || crop_string.equals("Select the crop")) {
                Toast.makeText(this, "Please Select the crop", Toast.LENGTH_LONG).show();
                return;
            }

            else
            {
                Intent intent = new Intent(OptionPage.this, CropPredictionActivity.class);
                intent.putExtra("state", state);
                intent.putExtra("district", dist);
                intent.putExtra("crop", crop_string);
                startActivity(intent);
            }
        }
        else if(view.getId()==R.id.yield_cal){
            if(crop_string.isEmpty() || crop_string.equals("Select the crop")) {
                Toast.makeText(this, "Please Select the crop", Toast.LENGTH_LONG).show();
                return;
            }

            else
            {
               // startActivity(new Intent(OptionPage.this,SmartYield.class));
                // Toast.makeText(OptionPage.this,crop_string.toString()+" calculator",Toast.LENGTH_LONG).show();
            }

        }
        else if(view.getId()==R.id.yield_com){
            if(crop_string.isEmpty() || crop_string.equals("Select the crop")) {
                Toast.makeText(this, "Please Select the crop", Toast.LENGTH_LONG).show();
                return;
            }

            else
            {


            }
        }else if(view.getId()==R.id.crop_guide_card){
            if(crop_string.isEmpty() || crop_string.equals("Select the crop")) {
                Toast.makeText(this, "Please Select the crop", Toast.LENGTH_LONG).show();
                return;
            }

            else
            {
                Intent intent=new Intent(OptionPage.this,Crop_Guide.class);
                intent.putExtra("Crop_Name",crop_string);
                startActivity(intent);


            }
        }
    }
}
