package com.example.sign_up_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sign_up_activity.Adapter.CoinAdapter;
import com.example.sign_up_activity.Interface.ILoadMore;
import com.example.sign_up_activity.Model.CoinModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Coin_Display extends AppCompatActivity {
    List<CoinModel> items = new ArrayList<>();
    CoinAdapter adapter;
    RecyclerView recyclerView;

    OkHttpClient client;
    Request request;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin__display);
        swipeRefreshLayout = findViewById(R.id.rootlayout);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadFirst10Coin(0);

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadFirst10Coin(1);
                setupAdapter();
            }
        });
        recyclerView = findViewById(R.id.coinlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter() {

    adapter = new CoinAdapter(recyclerView,Coin_Display.this,items);
    recyclerView.setAdapter(adapter);
    adapter.setiLoadMore(new ILoadMore() {
        @Override
        public void onLoadMore() {
            if(items.size() <= 1000){
                loadNext10Coin(items.size());
            }else {

                Toast.makeText(Coin_Display.this,"max limit reached",Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    private void loadNext10Coin(int index) {

        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10",index)).build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(Coin_Display.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body=response.body().string();
                Gson gson=new Gson();
               final List<CoinModel> newItems=gson.fromJson(body,new TypeToken<List<CoinModel>>(){}.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.addAll(newItems);
                        adapter.setLoaded();
                        adapter.updatedata(items);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });

    }

    private void loadFirst10Coin(int index) {

        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10",index)).build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(Coin_Display.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) {
               String body=response.body().toString();

//                Toast.makeText(Coin_Display.this, body, Toast.LENGTH_SHORT).show();
            //    Log.d("MESSAGE", body);
                Gson gson=new Gson();
                final List<CoinModel> newItems;

                newItems=gson.fromJson(body,new TypeToken<List<CoinModel>>(){}.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updatedata(newItems);

                    }
                });


            }
        });

        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }

    }
}
