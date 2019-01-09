package com.example.joketelleral.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.joketelleral.R;

public class JokeActivity extends AppCompatActivity {

    private static final String sErrorMessage = "Oops , Something went wrong!";
    private TextView mJokeTextView;
    private String mJokeText = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent callingIntent = getIntent();

        if(callingIntent.hasExtra(Intent.EXTRA_TEXT)){
            mJokeText = callingIntent.getStringExtra(Intent.EXTRA_TEXT);
        }

        mJokeTextView = (TextView) findViewById(R.id.tv_joke);

        if(mJokeText == null || mJokeText.isEmpty()){
            mJokeTextView.setText(sErrorMessage);
        }else{
            mJokeTextView.setText(mJokeText);
        }

    }
}
