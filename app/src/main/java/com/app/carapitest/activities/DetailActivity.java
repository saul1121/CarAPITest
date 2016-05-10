package com.app.carapitest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.carapitest.R;
import com.app.carapitest.models.CarMediaResponse;
import com.app.carapitest.models.CarModelResponse;
import com.app.carapitest.network.CarRestAPI;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = (ImageView) findViewById(R.id.image);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");


        new CarRestAPI(new CarRestAPI.CarAPIListener() {
            @Override
            public void OnResponse(Object response) {
                CarModelResponse data = (CarModelResponse) response;
                getImageData(data.getModels().get(0).getYears().get(0).getStyles().get(0).getId());
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                findViewById(R.id.progressBar).setVisibility(View.GONE);

            }
        }).getCarModels(name);
    }

    private void getImageData(Integer id) {
        new CarRestAPI(new CarRestAPI.CarAPIListener() {
            @Override
            public void OnResponse(Object response) {
                CarMediaResponse data = (CarMediaResponse) response;

                findViewById(R.id.progressBar).setVisibility(View.GONE);
                Picasso.with(DetailActivity.this)
                        .load(CarRestAPI.MEDIA_URL+data.getPhotoSrcs().get(0))
                        .into(image);
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                findViewById(R.id.progressBar).setVisibility(View.GONE);

            }
        }).getCarMedia(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
