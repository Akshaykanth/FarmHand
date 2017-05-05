package com.example.prince.farmhand;

/**
 * Created by prince on 26/2/17.
 */
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class getData extends Thread {

    private String str;
    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType TEXT
            = MediaType.parse("application/text; charset=utf-8");

    public getData(String s) {
        this.str = s;
    }

    public void run() {
        RequestBody body = RequestBody.create(TEXT,str);

        Request request = new Request.Builder()
                .url("https://farmhand.herokuapp.com/input")
                //.method("post",body)
                .get()
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            Intent goToNextActivity = new Intent(getApplicationContext(), WeatherReport.class);
            startActivity(goToNextActivity);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) try {
            throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



