package com.memes.memedia.service;

import android.media.Image;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRepo {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private HashMap<String, Object> refMap;

    /*
     Whenever this object is instantiated, pass the names of the references to keep track of.
     Every time that reference's value changes it's value will be put into a HashMap for easy access
     Because of the way this works, the page would have to be refreshed every time and new change is
     to be made
     */

    public DataRepo(String ... references){

        refMap = new HashMap<String, Object>();

        for(final String reference : references){

            final DatabaseReference ref = database.getReference(reference);

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

    /*

        Override the above constructor to accept Database references instead of their keys

     */

    public DataRepo(DatabaseReference ... references){

        refMap = new HashMap<String, Object>();

        for(final DatabaseReference reference : references){

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    refMap.put(reference.getKey(), snapshot.getValue());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.d("ERROR", error.toString());

                }
            });


        }


    }

    /*

      Add reference to database, if it exists

     */

    public void putToDatabase(String reference, Object value){

        DatabaseReference ref = database.getReference(reference);
        ref.setValue(value);

    }

    public Object getFromDatabase(String reference){

        return refMap.get(reference);

    }



}
