package com.memes.memedia.service;

import com.memes.memedia.models.MemeTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

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


        JSONArray memes = new JSONObject(resBody).getJSONObject("data").getJSONArray("memes");

        MemeTemplate [] allMemeTemplates = new MemeTemplate[memes.length()];

        for(int i = 0; i < memes.length(); i++){

            JSONObject jobj = memes.getJSONObject(0);
            allMemeTemplates[i] = new MemeTemplate(jobj.getString("url"), jobj.getInt("id"));

        }

        return allMemeTemplates;


    }
    /*

        Takes the id of a meme template, the top text to insert and the bottom text to insert.
        Returns a url which leads to the meme that was created.

     */

    public static String generateMeme(int templateId, String topText, String bottomText) throws IOException, JSONException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.imgflip.com/caption_image").newBuilder();
        urlBuilder.addQueryParameter("username", "MemediaProject");
        urlBuilder.addQueryParameter("password", "memesproject**");
        urlBuilder.addQueryParameter("template_id", templateId + "");
        urlBuilder.addQueryParameter("text0", topText);
        urlBuilder.addQueryParameter("text1", bottomText);
        String url = urlBuilder.build().toString();

        RequestBody body = RequestBody.create(JSON, "");

        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        Response res = client.newCall(req).execute();

        JSONObject resJson = new JSONObject(res.body().string());

        return resJson.getJSONObject("data").getString("url");


    }


}
