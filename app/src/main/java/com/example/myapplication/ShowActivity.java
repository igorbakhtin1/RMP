package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<User> userData;

    private DatabaseReference myDataBase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        init();
        getDataFromDB();
        setOnClickItem();
    }
    private void init(){
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        userData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    private void getDataFromDB()
    {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(listData.size() > 0)listData.clear();
                if(userData.size() > 0)userData.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    listData.add(user.name);
                    userData.add(user);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = userData.get(position);
                Intent i = new Intent(ShowActivity.this, ReadActivity.class);
                i.putExtra("user_name",user.name);
                i.putExtra("user_sec_name",user.secName);
                i.putExtra("user_patr",user.patr);
                i.putExtra("user_school",user.school);
                i.putExtra("user_group",user.group);
                startActivity(i);
            }
        });
    }
}
