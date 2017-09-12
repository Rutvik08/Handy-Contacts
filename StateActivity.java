package com.example.rutvik.handycontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StateActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        cancel = (Button)findViewById(R.id.sCancel);
        cancel.setOnClickListener(this);
    }
    public void onClick(View view) {
        finish();
    }
}
