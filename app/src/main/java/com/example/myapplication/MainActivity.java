package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText edName, edSecName, edPatr, edSchool, edGroup;
    private DatabaseReference myDataBase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init()
    {
        edName = findViewById(R.id.edName);
        edSecName = findViewById(R.id.edSecName);
        edPatr = findViewById(R.id.edPatr);
        edSchool = findViewById(R.id.edSchool);
        edGroup = findViewById(R.id.edGroup);
        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClickSave(View view)
    {
        String id = myDataBase.getKey();
        String name = edName.getText().toString();
        String secName = edSecName.getText().toString();
        String patr = edPatr.getText().toString();
        String school = edSchool.getText().toString();
        String group = edGroup.getText().toString();
        User newUser = new User(id, name, secName, patr, school, group);
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(secName)
                && !TextUtils.isEmpty(school) &&
                    !TextUtils.isEmpty(group)){
            myDataBase.push().setValue(newUser);
            Toast.makeText(this, "Пользователь " + name + " успешно добавлен",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Введи данные", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickRead(View view)
    {

    }
}
