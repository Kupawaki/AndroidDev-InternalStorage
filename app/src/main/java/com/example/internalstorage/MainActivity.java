package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity
{
    //Hooks
    EditText enterET;
    Button storeBTN, readBTN;
    TextView displayTV;

    //Instance variables
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    StringBuffer stringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        enterET = findViewById(R.id.enterET);
        storeBTN = findViewById(R.id.storeBTN);
        readBTN = findViewById(R.id.readBTN);
        displayTV = findViewById(R.id.displayTV);

        storeBTN.setOnClickListener(view ->
        {
            String enteredText = enterET.getText().toString();
            try
            {
                fileOutputStream = openFileOutput("offlineLocations.txt",  MODE_APPEND);
                fileOutputStream.write(enteredText.getBytes());
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