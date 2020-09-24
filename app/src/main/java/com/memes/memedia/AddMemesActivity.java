package com.memes.memedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.memes.memedia.models.MemeTemplate;
import com.memes.memedia.service.MemeGenerationService;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddMemesActivity extends AppCompatActivity {

    private volatile int templateId = 0;
    private volatile String url = "";
    private static OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memes);
        ImageView template = findViewById(R.id.image_post);

        ImageView close = findViewById(R.id.close_add_post_btn);
        loadTemplate();

        final Intent intent = new Intent(this, MainActivity.class);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);

            }
        });

    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public void loadTemplate(){

        Request req = new Request.Builder()
                .url("https://api.imgflip.com/get_memes")
                .build();

        client.newCall(req)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String s = response.body().string();

                        try {
                            JSONArray memes =  new JSONObject(s).getJSONObject("data").getJSONArray("memes");

                            MemeTemplate temp = new MemeTemplate(memes.getJSONObject(0).getString("url")
                                    , memes.getJSONObject(0).getInt("id"));

                            url = temp.getImageUrl();
                            templateId = temp.getId();

                            ImageView view = findViewById(R.id.image_post);

                            Bitmap m = null;
                            try {
                                URL aURL = new URL(url);
                                URLConnection conn = aURL.openConnection();
                                conn.connect();
                                InputStream is = conn.getInputStream();
                                BufferedInputStream bis = new BufferedInputStream(is);
                                m = BitmapFactory.decodeStream(bis);

                                view.setImageBitmap(m);
                                bis.close();
                                is.close();
                            } catch (IOException e) {
                                Log.e("error", "Error getting bitmap", e);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }

}