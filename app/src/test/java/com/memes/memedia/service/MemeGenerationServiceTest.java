package com.memes.memedia.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.jupiter.api.Assertions.*;

public class MemeGenerationServiceTest {

    private OkHttpClient client = new OkHttpClient();

    @Test
    void getAllTemplates() throws IOException, JSONException {

        Request req = new Request.Builder()
                .url("https://api.imgflip.com/get_memes")
                .build();

        Response res = client.newCall(req).execute();

        String resBody = res.body().string();

        assertTrue(new JSONObject(resBody).getBoolean("success"));

    }

    @Test
    void generateMeme(int templateId, String topText, String bottomText) throws IOException, JSONException {

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

        assertTrue(resJson.getBoolean("success"));


    }


}