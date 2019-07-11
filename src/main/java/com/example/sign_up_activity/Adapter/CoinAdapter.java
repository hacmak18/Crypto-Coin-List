package com.example.sign_up_activity.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sign_up_activity.Interface.ILoadMore;
import com.example.sign_up_activity.Model.CoinModel;
import com.example.sign_up_activity.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ILoadMore iLoadMore;
    boolean isloading;
    Activity activity;
    List <CoinModel> items;

    int visibleThreshold = 5,lastVisibleItem,totalItemCount;

    public CoinAdapter(RecyclerView recyclerView, Activity activity, List<CoinModel> items) {

        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isloading && totalItemCount <= (lastVisibleItem + visibleThreshold)){

                    if (iLoadMore != null)
                            iLoadMore.onLoadMore();
                    isloading = true;

                }
            }
        });
    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_item,parent,false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CoinModel item = items.get(position);
        CoinViewHolder holderitem = (CoinViewHolder)holder;

        holderitem.coin_name.setText(item.getName());
        holderitem.coin_symbol.setText(item.getSymbol());
        holderitem.coin_price.setText(item.getPrice_usd());
        holderitem.one_hour.setText(item.getPercent_change_1h());
        holderitem.twenty_hour.setText(item.getPercent_change_24h());
        holderitem.seven_day.setText(item.getPercent_change_7d());


        Picasso.with(activity).load(new StringBuilder("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjdlJ-Njv7iAhXBNo8KHf8mDXkQjRx6BAgBEAU&url=https%3A%2F%2Fwww.adweek.com%2Fbrand-marketing%2F3-reasons-cryptocurrency-isnt-worth-your-time%2F&psig=AOvVaw20G3tZxya098HZgeFab8oE&ust=1561327973512407")
                .append(item.getSymbol().toLowerCase()).append(".png").toString()).into(holderitem.coinicon);

        holderitem.one_hour.setTextColor(item.getPercent_change_1h().contains("-")?
                Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));

        holderitem.twenty_hour.setTextColor(item.getPercent_change_1h().contains("-")?
                Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));

        holderitem.seven_day.setTextColor(item.getPercent_change_1h().contains("-")?
                Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

     public void setLoaded(){
        isloading = true;
     }
     public void updatedata(List<CoinModel> coinModels){
        this.items = coinModels;
        notifyDataSetChanged();
     }
}
