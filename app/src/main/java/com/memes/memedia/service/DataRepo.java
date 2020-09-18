package com.memes.memedia.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRepo {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private HashMap<String, Object> refMap;

    /*
     Whenever this object is instantiated, pass the names of the references to keep track of.
     Every time that reference's value changes it's value will be put into a HashMap for easy access
     */

    public DataRepo(String ... references){

        refMap = new HashMap<String, Object>();

        for(final String reference : references){

            final DatabaseReference ref = database.getReference(reference);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    refMap.put(reference, snapshot);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    refMap.put(reference, snapshot.getValue());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.d("ERROR", error.toString());

                }
            });

        }

    }

    public void putToDatabase(String reference, Object value){

        DatabaseReference ref = database.getReference(reference);
        ref.setValue(value);

    }

    public Object getFromDatabase(String reference){

        return refMap.get(reference);

    }


}
