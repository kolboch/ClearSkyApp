package com.example.kb.clearsky;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kb.clearsky.model.api_specific.ResponseMainBody;
import com.example.kb.clearsky.connection_utils.ForecastCallGenerator;

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
    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        makeTestApiCall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings_item:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void makeTestApiCall() {
        Call<ResponseMainBody> call = ForecastCallGenerator.getCall("Wroclaw", "metric", "737490f14eb57485aa7928ce8e2c8a41");
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
                if (response.isSuccessful()) {
                    ResponseMainBody body = response.body();
                    Double tempMax = body.getForecastList().get(1).getWeatherInfo().getTempMax();
                    String responseCode = body.getResponseCode();
                    String cityName = body.getCity().getName();
                    Integer lines = body.getLinesInResponse();
                    responseCodeTextView.setText(responseCode);
                    linesTextView.setText(tempMax.toString());
                    cityTextView.setText(cityName);
                    Log.e(LOG_TAG, "RESPONSE");
                } else {
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
