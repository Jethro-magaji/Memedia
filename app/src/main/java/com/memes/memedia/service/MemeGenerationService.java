package com.memes.memedia.service;

import com.memes.memedia.models.MemeTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MemeGenerationService {

    private static OkHttpClient client = new OkHttpClient();

    /*
        Calls the meme generator api and returns urls images of all the available meme formats
     */

    public static MemeTemplate[] getAllTemplates() throws IOException, JSONException {

        Request req = new Request.Builder()
                .url("https://api.imgflip.com/get_memes")
                .build();

        Response res = client.newCall(req).execute();

        String resBody = res.body().string();


        JSONArray memes = new JSONObject(resBody).getJSONArray("memes");

        MemeTemplate [] allMemeTemplates = new MemeTemplate[memes.length()];

        for(int i = 0; i < memes.length(); i++){

            JSONObject jobj = memes.getJSONObject(0);
            allMemeTemplates[i] = new MemeTemplate(jobj.getString("url"), jobj.getInt("id"));

        }

        return allMemeTemplates;


    }


}
