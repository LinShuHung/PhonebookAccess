package com.suhun.phonebookaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private EditText updateInput;
    private ListView phoneContent;
    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private String[] from = {"contentKey"};
    private int[] to = {R.id.item_content};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListView();
    }

    private void initView(){
        updateInput = findViewById(R.id.lid_updateInput);
    }

    private void initListView(){
        phoneContent = findViewById(R.id.lid_phonebookContent);
        simpleAdapter = new SimpleAdapter(this,data, R.layout.item, from, to );
        phoneContent.setAdapter(simpleAdapter);
        //test list view show data
        /*HashMap<String, String> testData = new HashMap<>();
        testData.put(from[0], "Suhun is happy");
        data.add(testData);
        simpleAdapter.notifyDataSetChanged();*/
    }

    public void updateFun(View view){

    }
    public void getPhoneBookFun(View view){

    }
}