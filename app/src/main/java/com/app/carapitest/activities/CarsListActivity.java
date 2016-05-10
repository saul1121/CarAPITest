package com.app.carapitest.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.carapitest.R;
import com.app.carapitest.adapters.CarAdapter;
import com.app.carapitest.common.CarItemFactory;
import com.app.carapitest.models.CarItem;
import com.app.carapitest.models.CarMakesResponse;
import com.app.carapitest.models.Make;
import com.app.carapitest.models.Model;
import com.app.carapitest.network.CarRestAPI;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CarsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CarAdapter mAdapter;
    private SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CarAdapter(this, new ArrayList<CarItem>());
        setLinearLayout();
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
                mAdapter.addmore();
                mAdapter.notifyDataSetChanged();
            }
        });
        final Realm realm = Realm.getInstance(this);

        RealmResults<CarItem> result = realm.where(CarItem.class).findAll();
        if(result.size()>0){
            List<CarItem> carItem = new ArrayList<>();
            for(CarItem car : result){
                carItem.add(car);
            }
            setAdapter(carItem);
        } else {
            new CarRestAPI(new CarRestAPI.CarAPIListener() {
                @Override
                public void OnResponse(Object response) {
                    CarMakesResponse data = (CarMakesResponse) response;
                    findViewById(R.id.warning).setVisibility(View.VISIBLE);
                    //List<Model> models = new ArrayList<>();
                    List<CarItem> carItem = new ArrayList<>();
                    for (Make make : data.getMakes()) {
                        for (Model model : make.getModels()) {
                            carItem.add(CarItemFactory.getItem(realm, make, model));
                        }
                    }

                    setAdapter(carItem);
                }

                @Override
                public void OnError(String msg) {

                }
            }).getCarMakes();
        }

    }


    public synchronized void setLinearLayout() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

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

    public void setAdapter(List<CarItem> carItem) {
        findViewById(R.id.warning).setVisibility(View.GONE);
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        mAdapter.addAll(carItem);
        mAdapter.notifyDataSetChanged();
    }
}
