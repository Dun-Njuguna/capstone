package com.example.capstone.services;

import android.util.Log;

import com.example.capstone.Constants;
import com.example.capstone.models.Popular;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Service {
    public static final String baseUrl = Constants.BASE_URL;
    public static final String TAG = Service.class.getSimpleName();

    public static void findPopularMovies(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();

        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Popular> processResults(Response response) {
        ArrayList<Popular> newPopularMovies = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject popularJSON = new JSONObject(jsonData);
            JSONArray resultsJSON = popularJSON
                    .getJSONArray("tv_shows");


            Log.v(TAG, "Response " + resultsJSON.toString());
            if (response.isSuccessful()) {
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject resultJSON = resultsJSON.getJSONObject(i);
                    String name = resultJSON.getString("name");
                    String image_thumbnail_path = resultJSON.getString("image_thumbnail_path");

                    Popular popular =  new Popular(name,image_thumbnail_path);
                    newPopularMovies.add(popular);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return newPopularMovies;
    }
}
