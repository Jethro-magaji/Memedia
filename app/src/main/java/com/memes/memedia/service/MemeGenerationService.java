package com.memes.memedia.service;

import android.widget.ImageView;

import com.memes.memedia.AddMemesActivity;
import com.memes.memedia.models.MemeTemplate;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
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

//    public static void getAllTemplates(final ImageView view, final AddMemesActivity act) throws IOException, JSONException {
//
//        Request req = new Request.Builder()
//                .url("https://api.imgflip.com/get_memes")
//                .build();
//
//        client.newCall(req)
//                .enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                        String s = response.body().string();
//                        try {
//                            JSONArray memes =  new JSONObject(s).getJSONObject("data").getJSONArray("memes");
//
//                            MemeTemplate temp = new MemeTemplate(memes.getJSONObject(0).getString("url")
//                                    , memes.getJSONObject(0).getInt("id"));
//
//                            Picasso.get().load(temp.getImageUrl()).into(view);
//                            act.setTemplateId(temp.getId());
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
//
//
//
//
//
//    }
    /*

        Takes the id of a meme template, the top text to insert and the bottom text to insert.
        Returns a url which leads to the meme that was created.

     */

    public static String generateMeme(int templateId, String topText, String bottomText) throws IOException, JSONException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.imgflip.com/caption_image").newBuilder();
        urlBuilder.addQueryParameter("username", "MemediaProject")
            .addQueryParameter("password", "memesproject**")
            .addQueryParameter("template_id", templateId + "")
            .addQueryParameter("text0", topText)
            .addQueryParameter("text1", bottomText);
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
