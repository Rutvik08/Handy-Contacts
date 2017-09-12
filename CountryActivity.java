package com.example.rutvik.handycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CountryActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button done, cancel;
    String cName;
    ListView listView;
    private static final int INTENT_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        listView = (ListView)findViewById(android.R.id.list);
        done = (Button)findViewById(R.id.cDone);
        done.setOnClickListener(this);
        cancel = (Button)findViewById(R.id.cCancel);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.reset:
            finish();
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        cName = (listView.getItemAtPosition(i)).toString();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cDone:
                if(cName == null)
                {
                    Toast.makeText(this,"Select a country to select a state.",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cCancel:
                finish();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        requestCode = INTENT_STATE;
        if (requestCode == INTENT_STATE) {
            switch (resultCode) {
                case RESULT_OK:
                    String stateCountry = data.getStringExtra("state");
                    Intent intent = getIntent();
                    intent.putExtra("stateCountry", stateCountry);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }
}
