package com.example.sign_up_activity.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sign_up_activity.R;

public class CoinViewHolder extends RecyclerView.ViewHolder {
    public ImageView coinicon;
    TextView coin_symbol,coin_name,coin_price,one_hour,twenty_hour,seven_day;
    public CoinViewHolder(@NonNull View itemView) {
        super(itemView);
        coinicon = (ImageView)itemView.findViewById(R.id.imageView);
//        imageView = itemView.findViewById(R.id.imageView);
        coin_symbol = itemView.findViewById(R.id.coinnsymbol);
//        txt_divider = itemView.findViewById(R.id.coindivider);
        coin_name = itemView.findViewById(R.id.coinname);
        coin_price = itemView.findViewById(R.id.priceusdtext);
 //       txt_dollor = itemView.findViewById(R.id.doller);
        one_hour = itemView.findViewById(R.id.onehour);
        twenty_hour = itemView.findViewById(R.id.twentyFourHour);
        seven_day = itemView.findViewById(R.id.sevenDayChange);



    }
}
