package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;


import com.example.finalproject.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import app.com.example.joke_components.JokeActivity;

/**
 * Created by Shimona on 3/17/2016.
 */
public class FetchJokeTask extends AsyncTask<Context, Void, String> {
private static MyApi myApiService = null;
private Context context;
    private ProgressBar spinner;

    public FetchJokeTask() {
    }

    public FetchJokeTask(ProgressBar spinner) {
        this.spinner = spinner;
    }

    @Override
protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://joketellerbackend.appspot.com/_ah/api/");

//        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//        new AndroidJsonFactory(), null)
//        // options for running against local devappserver
//        // - 10.0.2.2 is localhost's IP address in Android emulator
//        // - turn off compression when running against local devappserver
//        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//@Override
//public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//        abstractGoogleClientRequest.setDisableGZipContent(true);
//        }
//        });
        // end options for devappserver

        myApiService = builder.build();
        }

        context = params[0];


        try {
        return myApiService.getJokes().execute().getData();
        } catch (IOException e) {
        return e.getMessage();
        }
        }

@Override
protected void onPostExecute(String result) {
       // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
 //   JokesProvider jokesProvider = new JokesProvider();
    Intent intent = new Intent(context,JokeActivity.class);
    Bundle mBundle = new Bundle();
    mBundle.putString(Intent.EXTRA_TEXT, result);
    intent.putExtras(mBundle);

    context.startActivity(intent);
    if(spinner!=null) {
        spinner.setVisibility(View.INVISIBLE);
    }
        }
        }

