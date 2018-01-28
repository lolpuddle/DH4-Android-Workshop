package com.example.oceancheung.deltahacksdemoworkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PriceActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        String cryptoRequested;

        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                cryptoRequested= null;
            }
            else {
                cryptoRequested= extras.getString("crypto");
            }
        }
        else {
            cryptoRequested= (String) savedInstanceState.getSerializable("crypto");
        }

        try {
            run("https://api.quadrigacx.com/v2/ticker?book=" + cryptoRequested);
        } catch (IOException e) {
            Log.e("OCEANFAIL", e.getMessage());
        }
    }

    private void run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("FAILL", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                Log.i("Data", jsonData);
                text = findViewById(R.id.resultString);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(jsonData);
                    }
                });
            }
        });
    }
}
