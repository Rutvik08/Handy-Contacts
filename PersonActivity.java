package com.example.rutvik.handycontacts;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstName, lastName, age, email, phone;
    TextView birthdate, countryStateTv;
    Button bDate, countryState, done;
    String date, stateCountry;
    Intent intent;
    private static final int INTENT_DATE = 1;
    private static final int INTENT_CS = 0;
    public static final String DATA = "DataFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        firstName = (EditText) findViewById(R.id.etFn);
        lastName = (EditText) findViewById(R.id.etLn);
        age = (EditText) findViewById(R.id.etA);
        email = (EditText) findViewById(R.id.etEm);
        phone = (EditText) findViewById(R.id.etPh);

        birthdate = (TextView) findViewById(R.id.birthdateTv);
        countryStateTv = (TextView) findViewById(R.id.countryTv);

        bDate = (Button) findViewById(R.id.btnBd);
        bDate.setOnClickListener(this);
        countryState = (Button) findViewById(R.id.btnCs);
        countryState.setOnClickListener(this);
        done = (Button) findViewById(R.id.btnD);
        done.setOnClickListener(this);
        restoreData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem activity1 = menu.findItem(R.id.activity1);
        activity1.setVisible(false);
        MenuItem reset = menu.findItem(R.id.reset);
        reset.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.reset:
            return(true);
        case R.id.activity2:
            intent = new Intent(this, DateActivity.class);
            startActivityForResult(intent, INTENT_DATE);
            break;
        case R.id.activity3:
            intent = new Intent(this, CountryActivity.class);
            startActivityForResult(intent, INTENT_CS);
            break;
        }
        return(super.onOptionsItemSelected(item));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBd:
                intent = new Intent(this, DateActivity.class);
                startActivityForResult(intent, INTENT_DATE);
                break;
            case R.id.btnCs:
                intent = new Intent(this, CountryActivity.class);
                startActivityForResult(intent, INTENT_CS);
                break;
            case R.id.btnD:
                saveData();
                break;
            default:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_DATE) {
            switch (resultCode) {
                case RESULT_OK:
                    date = data.getStringExtra("month") + "/" + data.getStringExtra("day") + "/" + data.getStringExtra("year");
                    birthdate.setText(date);
                    birthdate.setVisibility(View.VISIBLE);
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
        else if (requestCode == INTENT_CS) {
            switch (resultCode) {
                case RESULT_OK:
                    stateCountry = data.getStringExtra("stateCountry");
                    countryStateTv.setText(stateCountry);
                    countryStateTv.setVisibility(View.VISIBLE);
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }

    protected void saveData() {
        SharedPreferences settings = getSharedPreferences(DATA, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("firstName", firstName.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.putString("age", age.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.putString("phone", phone.getText().toString());
        editor.putString("birthdate", birthdate.getText().toString());
        editor.putString("countryState", countryStateTv.getText().toString());
        editor.commit();
        Toast.makeText(this,"HURRAH! THE DATA HAS BEEN STORED SUCCESSFULLY.",Toast.LENGTH_SHORT).show();
    }

    protected void restoreData() {
        SharedPreferences settings = getSharedPreferences(DATA, 0);
        firstName.setText(settings.getString("firstName",null));
        lastName.setText(settings.getString("lastName",null));
        age.setText(settings.getString("age",null));
        email.setText(settings.getString("email",null));
        phone.setText(settings.getString("phone",null));
        String bd = settings.getString("birthdate",null);
        String cs = settings.getString("countryState",null);
        if(bd!=null){
            birthdate.setVisibility(View.VISIBLE);
            birthdate.setText(bd);
            if(cs!=null){
                countryStateTv.setVisibility(View.VISIBLE);
                countryStateTv.setText(cs);
            }
        }
    }
}


