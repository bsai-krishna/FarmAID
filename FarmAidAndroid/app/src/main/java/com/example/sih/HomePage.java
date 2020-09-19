package com.example.sih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] country = { "State", "Uttar Pradesh", "Bihar", "Punjab", "Maharashtra","Haryana"};
    Spinner sp_dis,spin,sp_season;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        spin = (Spinner) findViewById(R.id.spinner);
        sp_dis=findViewById(R.id.spinner_dist);
        sp_season=findViewById(R.id.spinner_season);
        //spin.setOnItemSelectedListener(getBaseContext());

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(this);

    }

    String[]up_dist;
    String state,dist,season_string;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        Spinner spin3=(Spinner)parent;
        String []season={"Rabi","Karif"};
        ArrayAdapter seas = new ArrayAdapter(this,android.R.layout.simple_spinner_item,season);
        seas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sp_season.setAdapter(seas);
        sp_season.setOnItemSelectedListener(this);
        if(spin3.getId()==R.id.spinner_season){
            season_string=spin3.getSelectedItem().toString();
        }
        if(spin.getId()==R.id.spinner) {
            state = spin.getSelectedItem().toString();
            if (spin.getSelectedItem().equals("State")) {
                Toast.makeText(this, "Please Select a State", Toast.LENGTH_SHORT).show();
            }
            if (spin.getSelectedItem().equals("Uttar Pradesh")) {
                up_dist = new String[]{"Agra", "Aligarh", "Allahabad", "Ambedkar nagar", "Amethi", "Amroha", "Auraiya", "Azamgarh", "Baghpat", "Bahraich", "Ballia", "Balrampur", "Banda", "Barabanki"
                        , "Bareilly", "Basti", "Bhadohi", "Bijnor", "Budaun", "Bulandshahr", "Chandauli", "Chitrakoot", "Deoria", "Etah", "Etawah", "Faizabad", "Farrukhabad", "Fatehpur", "Firozabad", "Gautam Buddha Nagar"
                        , "Ghaziabad", "Gazipur", "Gonda","Gorakhpur","Hamirpur","Hapur","Hardor","Hathras","Jaunpur","Jalaun","Jhansi","Kannauj","Kanpur Dehat","Kanpur Nagar","Kanshiram Nagar","Kaushambi","Kushinagar"
                        ,"Lakhimpur Kheri","Khusinagar","Lalitpur","Lucknow","Maharajaganj","Mahoba","Mainpiri","Mathura","Mau","Meerut","Mirzapur","Moradabad","Muzaffarnagr","Hapur","Pilibhit","Pratapgarh","Raebareli","Rampur","Saharanpur",
                        "Sant Kabir Nagar","Shahjahanpur","Shamli","Shravasti","Siddharthnagar","Sitapur","Sonbhadra","Sultanpur","Unnao","Varanasi"};
                sp_dis.setVisibility(View.VISIBLE);
                sp_season.setVisibility(View.INVISIBLE);
                ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, up_dist);
                dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                sp_dis.setAdapter(dist);
                sp_dis.setOnItemSelectedListener(this);


            }

            if (spin.getSelectedItem().equals("Bihar")) {
                up_dist = new String[]{"Araria","Arwal","Aurangabad","Banka","Begusarai","Bhagalpur","Bhojpur","Buxar","Darbhanga","East Champaran","Gaya","Gopalganj","Jamui","Jehanabad","Kaimur","katihar","Khagaria","Kisanganj","Lakhisarai"
                        ,"Medhepura","Madhubani","Munger","Muzzaffarpur","Nalanda","Nawada","Patna","Purnia","Rohtas","Saharsa","Samastipur","Saran","Sheikhpura","Sheohar","Sitamarhi","Siwan","Supaul","Vaishali","West Champaran"};
                sp_dis.setVisibility(View.VISIBLE);
                sp_season.setVisibility(View.INVISIBLE);
                ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, up_dist);
                dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                sp_dis.setAdapter(dist);
                sp_dis.setOnItemSelectedListener(this);



            }

            if (spin.getSelectedItem().equals("Punjab")) {

                up_dist = new String[]{"Amritsar","Barnala","Bathinda","Fazilka","Faridkot","Fatehgarh Sahib","Firozpur","Gurdaspur","Hoshiarpur","Jalandhar","Kapurthala","Ludhiana","Mansa","Moga","Mohali","Muktsar","Pathankot","Patiala",
                        "Rupnagar","Sangrur","Shahid Bhagat Singh Nagar ","Tarn Taran"};
                sp_dis.setVisibility(View.VISIBLE);
                sp_season.setVisibility(View.INVISIBLE);
                ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, up_dist);
                dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                sp_dis.setAdapter(dist);
                sp_dis.setOnItemSelectedListener(this);

            }

            if ((spin.getSelectedItem().equals("Maharashtra"))) {
                up_dist = new String[]{"Ahmednagar","Akola","Amravati","Aurangabad","Beed","Bhandara","Chandrapur","Dhule","Gadchiroli","Gondia","Hingoli","Jalgaon","Jalna","Kolhapur","Latur","Mumbai City","Mumbai Suburban","Nagpur",
                        "Nanded","Nandurbar","Nashik","Osmanabad","Parbhani","Pune","Raigad","Ratnagiri","Sangli","Satara","Sindhudurg","Solapur","Thane","Wardha","Washim","Yavatmal","Palghar"};
                sp_dis.setVisibility(View.VISIBLE);
                sp_season.setVisibility(View.INVISIBLE);
                ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, up_dist);
                dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                sp_dis.setAdapter(dist);
                sp_dis.setOnItemSelectedListener(this);

            }

            if (spin.getSelectedItem().equals("Haryana")) {
                up_dist = new String[]{"Ambala","Bhiwani","Charkhi Dadri","Faridabad","Gurgaon","Hisar","Jhajjar","Jind","Katihal","Karnal","Kurukshetra","Mahenderagarh","Nuh","Palwal","Panchkula","Panipat","Rewari","Rohtak","Sirsa","Sonipat","Yamunanagar"};
                sp_dis.setVisibility(View.VISIBLE);
                sp_season.setVisibility(View.INVISIBLE);
                ArrayAdapter dist = new ArrayAdapter(this, android.R.layout.simple_spinner_item, up_dist);
                dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                sp_dis.setAdapter(dist);
                sp_dis.setOnItemSelectedListener(this);

            }
        }
        if(spin2.getId()==R.id.spinner_dist){
            dist=spin2.getSelectedItem().toString();
            sp_season.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void submit_detail(View view) {
        Intent intent=new Intent(HomePage.this,OptionPage.class);
        intent.putExtra("State",state);
        intent.putExtra("District",dist);
        intent.putExtra("Season",season_string);
        startActivity(intent);
    }
}

