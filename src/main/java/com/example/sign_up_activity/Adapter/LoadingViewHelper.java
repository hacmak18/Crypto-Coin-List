package com.example.sign_up_activity.Adapter;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sign_up_activity.R;

public class LoadingViewHelper extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;
    public LoadingViewHelper(@NonNull View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progress_Bar);


    }
}
