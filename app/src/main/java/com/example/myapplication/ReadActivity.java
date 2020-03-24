package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity
{
    private TextView tvname, tvsecname, tvpatr, tvschool, tvgroup;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }

    private void init()
    {
        tvname = findViewById(R.id.tvName);
        tvsecname = findViewById(R.id.tvSecName);
        tvpatr = findViewById(R.id.tvPatr);
        tvschool = findViewById(R.id.tvSchool);
        tvgroup = findViewById(R.id.tvGroup);
    }

    private void getIntentMain()
    {
        Intent i = getIntent();
        if (i != null)
        {
            tvname.setText(i.getStringExtra("user_name"));
            tvsecname.setText(i.getStringExtra("user_sec_name"));
            tvpatr.setText(i.getStringExtra("user_patr"));
            tvschool.setText(i.getStringExtra("user_school"));
            tvgroup.setText(i.getStringExtra("user_group"));
        }
    }
}
