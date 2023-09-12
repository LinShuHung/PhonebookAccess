package com.suhun.phonebookaccess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListView();
        checkContactsUserPermissionFun();
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

    private void checkContactsUserPermissionFun(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            initContentResolver();
        }else{
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                initContentResolver();
            }
        }else{
            finish();
        }
    }

    private void initContentResolver(){
        contentResolver = getContentResolver();
    }

    public void updateFun(View view){

    }
    public void getPhoneBookFun(View view){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String outData = String.format("%s:%s", name, number);
            HashMap<String, String> content = new HashMap<>();
            content.put(from[0], outData);
            data.add(content);
            simpleAdapter.notifyDataSetChanged();
        }
        cursor.close();
    }
}