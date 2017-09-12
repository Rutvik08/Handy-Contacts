package com.example.rutvik.handycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class CountryFragment extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener {

    String fileContents;
    ArrayAdapter<String> adapter;
    Intent intent;
    String cName;

    private static int COUNTRY = R.raw.countries;
    private static int USA = R.raw.usa;
    private static int INDIA = R.raw.india;
    private static int CHINA = R.raw.china;
    private static int MEXICO = R.raw.mexico;
    private static final int INTENT_STATE = 1;
    public CountryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_country, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fileContents = readFile(COUNTRY);
        String textStr[] = fileContents.split("\\r\\n|\\n|\\r");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, textStr);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        cName = (getListView().getItemAtPosition(i)).toString();
        switch (cName) {
            case "China":
                intent = new Intent(getActivity(), StateActivity.class);
                intent.putExtra("country", CHINA);
                intent.putExtra("countryName", "China");
                startActivityForResult(intent, INTENT_STATE);
                break;
            case "India":
                intent = new Intent(getActivity(), StateActivity.class);
                intent.putExtra("country", INDIA);
                intent.putExtra("countryName", "India");
                startActivityForResult(intent, INTENT_STATE);
                break;
            case "Mexico":
                intent = new Intent(getActivity(), StateActivity.class);
                intent.putExtra("country", MEXICO);
                intent.putExtra("countryName", "Mexico");
                startActivityForResult(intent, INTENT_STATE);
                break;
            case "United States":
                intent = new Intent(getActivity(), StateActivity.class);
                intent.putExtra("country", USA);
                intent.putExtra("countryName", "United States");
                startActivityForResult(intent, INTENT_STATE);
                break;
        }
    }

    private String readFile(int dataFile) {
        try {
            InputStream file = new BufferedInputStream(getResources().openRawResource(dataFile));
            byte[] data = new byte[file.available()];
            file.read(data, 0, file.available());
            fileContents = new String(data);
            file.close();
        } catch (Exception noFile) {
            fileContents = "empty";
        }
        return fileContents;
    }
}
