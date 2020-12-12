package com.example.fmbthane;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    EditText editTexts;
    Button button;
    DatabaseReference reff;
    List<String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        editTexts = (EditText) findViewById(R.id.et);
        button = (Button) findViewById(R.id.bt);
        //Intent i=new Intent(MainActivity2.this,StartStop.class);
        //startActivity(i);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String et=editTexts.getText().toString();
                reff=FirebaseDatabase.getInstance().getReference().child("FmbUsers");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            user = new ArrayList<>();
                            if (!user.contains(dataSnapshot1.child("Its").getValue().toString())) {
                                user.add(dataSnapshot1.child("Its").getValue().toString());
                            }
                        /*String uname=dataSnapshot1.child("Its").getValue().toString();
                        if (!et.equals(uname)){
                            Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent i=new Intent(MainActivity2.this,StartStop.class);
                            startActivity(i);
                        }*/
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if (!user.contains(et)){
                    Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i=new Intent(MainActivity2.this,StartStop.class);
                    startActivity(i);
                }

        }
    });
}
}