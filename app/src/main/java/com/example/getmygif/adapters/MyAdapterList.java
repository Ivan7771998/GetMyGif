package com.example.getmygif.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.getmygif.R;
import com.example.getmygif.models.GigDTO;

import java.util.List;

public class MyAdapterList extends RecyclerView.Adapter<MyAdapterList.MyViewHolder> {

    private List<GigDTO> data;
    private Context context;

    public MyAdapterList(Context context, List<GigDTO> date) {
        this.data = date;
        this.context = context;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
        }
    }

    @Override
    public MyAdapterList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(context)
                .load(data.get(position).getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e,
                                                Object model, Target<Drawable> target,
                                                boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource,
                                                   Object model, Target<Drawable> target,
                                                   DataSource dataSource,
                                                   boolean isFirstResource) {
                        return false;
                    }
                })
                .into( holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}