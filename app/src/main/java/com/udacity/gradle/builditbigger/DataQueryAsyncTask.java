package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Amira on 1/8/2019.
 */

public class DataQueryAsyncTask extends AsyncTask<Context, Void , String> {

    private MyApi myApi = null;
    Context context;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApi == null){
            MyApi.Builder apiBuilder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null
            );
            // IP 10.0.3.2 for genymotion and PC IP for real devices
            apiBuilder.setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApi = apiBuilder.build();
        }
        context = params[0];
        try{
            return myApi.getJoke().execute().getData();
        }catch(IOException ex){
            Log.d("MainActivity" , ex.getMessage());
            Log.d("MainActivtiy" ,  ex.getStackTrace().toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String message;
        if(result == null || result.isEmpty()){
            message = "Sorry , Something went wrong";
        }else{
            message = result;
        }
        if(context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
