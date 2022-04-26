package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    //Hooks
    EditText keyET, valueET;
    Button storeBTN, readBTN;
    TextView displayTV;

    //Instance variables----------------------------------------------------------------------------
    //File read and write
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    StringBuffer stringBuffer;

    //Data structures
    Map<String, String> map;

    //The path of internal storage is data/data/com.example.internalstorage/files

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        keyET = findViewById(R.id.keyET);
        valueET = findViewById(R.id.valueET);
        storeBTN = findViewById(R.id.storeBTN);
        readBTN = findViewById(R.id.readBTN);
        displayTV = findViewById(R.id.displayTV);

        map = new HashMap<>();

        storeBTN.setOnClickListener(view ->
        {
            String enteredText = keyET.getText().toString();
            try
            {
                fileOutputStream = openFileOutput("offlineLocations.txt",  MODE_APPEND);
                fileOutputStream.write((enteredText + "\n").getBytes());
                fileOutputStream.close();
            }
            catch (Exception e)
            {
                Log.e("TESTING / MISC", e.getMessage());
            }
        });

        readBTN.setOnClickListener(view ->
        {
            try
            {
                fileInputStream = openFileInput("offlineLocations.txt");
                inputStreamReader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                stringBuffer = new StringBuffer();

                String lines;
                while((lines = bufferedReader.readLine()) != null)
                {
                    stringBuffer.append(lines + "\n");
                }
                displayTV.setText(stringBuffer.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
    }
}