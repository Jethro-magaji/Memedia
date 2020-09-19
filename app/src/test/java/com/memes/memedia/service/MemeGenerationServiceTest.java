package com.memes.memedia.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
}