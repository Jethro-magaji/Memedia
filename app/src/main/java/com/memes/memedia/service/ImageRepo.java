package com.memes.memedia.service;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class ImageRepo {

    private StorageReference imageRepo;

    public ImageRepo(){

        imageRepo = FirebaseStorage.getInstance().getReference();

    }

    /*
       Upload a file given an image url and a url path to place it into.
    */
    public void upload(String imgPath, String storageLocation) throws IOException {


        Uri file = Uri.fromFile(new File(imgPath));

        StorageReference memeRef = imageRepo.child(storageLocation);

        memeRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Log.d("Upload", "Image Uploaded");

                    }
                });

    }

    /*
        Downloads jpg image from storage into a temporary file, given the path and the name for the file
        If unable to it returns null

     */

    public File download(String path, String name) throws IOException {

        StorageReference imgRef = imageRepo.child(path);

        File f = File.createTempFile(name, "jpg");
        final boolean[] successful = {false};

        imgRef.getFile(f).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                successful[0] = true;

            }
        });


        if(successful[0]){

            return f;

        }

        return null;

    }


}
