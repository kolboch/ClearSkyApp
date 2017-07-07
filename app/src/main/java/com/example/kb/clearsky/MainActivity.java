package com.example.kb.clearsky;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kb.clearsky.model.api_specific.ResponseMainBody;
import com.example.kb.clearsky.model.connection_utils.ApiService;
import com.example.kb.clearsky.model.connection_utils.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cityName)
    TextView cityTextView;
    @BindView(R.id.linesInResponse)
    TextView linesTextView;
    @BindView(R.id.responseCode)
    TextView responseCodeTextView;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseMainBody> call = apiService.getForecastFromCityName(
                "Wroclaw", "737490f14eb57485aa7928ce8e2c8a41"
        );
        final ProgressDialog dialog;
        /**
         * Progress Dialog for User Interaction
         */
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Getting json");
        dialog.setMessage("Message");
        dialog.show();
        call.enqueue(new Callback<ResponseMainBody>() {
            @Override
            public void onResponse(Call<ResponseMainBody> call, Response<ResponseMainBody> response) {
                dialog.dismiss();
                Log.e(LOG_TAG, "OnResponse");
                if(response.isSuccessful()){
                    ResponseMainBody body = response.body();
                    String responseCode = body.getResponseCode();
                    String cityName = body.getCity().getName();
                    Integer lines = body.getLinesInResponse();
                    responseCodeTextView.setText(responseCode);
                    linesTextView.setText(lines.toString());
                    cityTextView.setText(cityName);
                    Log.e(LOG_TAG, "RESPONSE");
                }
                else{
                    Toast.makeText(MainActivity.this, "Failure code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMainBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Response failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
