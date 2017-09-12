package com.example.rutvik.handycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DateActivity extends AppCompatActivity implements View.OnClickListener {

    Button done, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        done = (Button)findViewById(R.id.calDone);
        done.setOnClickListener(this);
        cancel = (Button)findViewById(R.id.calCancel);
        cancel.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem activity3 = menu.findItem(R.id.activity3);
        activity3.setVisible(false);
        MenuItem activity2 = menu.findItem(R.id.activity2);
        activity2.setVisible(false);
        MenuItem activity1 = menu.findItem(R.id.activity1);
        activity1.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.reset:
            finish();
    }
        return(super.onOptionsItemSelected(item));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calDone:
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                String day = String.valueOf(datePicker.getDayOfMonth());
                String month = String.valueOf(datePicker.getMonth() + 1);
                String year = String.valueOf(datePicker.getYear());
                Intent date = getIntent();
                date.putExtra("day", day);
                date.putExtra("month", month);
                date.putExtra("year", year);
                setResult(RESULT_OK, date);
                finish();
                break;
            case R.id.calCancel:
                finish();
                break;
        }
    }
}
