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


public class StateFragment extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener {

    String fileContents;
    ArrayAdapter<String> adapter;
    Intent intent;
    String cName;
    public StateFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_state, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        int COUNTRY = b.getInt("country");
        cName = b.getString("countryName");
        fileContents = readFile(COUNTRY);
        String textStr[] = fileContents.split("\\r\\n|\\n|\\r");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, textStr);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        String csName = (getListView().getItemAtPosition(i)).toString()+", "+ cName;
        Intent countryState = getActivity().getIntent();
        countryState.putExtra("state", csName);
        getActivity().setResult(getActivity().RESULT_OK, countryState);
        getActivity().finish();
    }

    private String readFile(int dataFile) {
        String fileContents;
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
