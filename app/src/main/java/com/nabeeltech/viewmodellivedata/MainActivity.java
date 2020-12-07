package com.nabeeltech.viewmodellivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTextView = findViewById(R.id.tvNumber);
        //MainActivityDataGenerator model = new MainActivityDataGenerator();
        final MainActivityDataGenerator model = ViewModelProviders.of(this).get(MainActivityDataGenerator.class);


        setSupportActionBar(toolbar);
        toolbar.setTitle("Live Data View Model");
        setSupportActionBar(toolbar);

        final LiveData<String> myRandomNumber = model.getNumber();

        myRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextView.setText(s);
                Log.i(TAG, "Data Updated in UI");
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.createNumber();
            }
        });

    }
}