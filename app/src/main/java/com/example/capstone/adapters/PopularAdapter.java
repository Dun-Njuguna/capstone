package com.example.capstone.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstone.R;
import com.example.capstone.models.Popular;
import com.example.capstone.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.AllComicViewHolder>{
    private ArrayList<Popular> mPopular;
    private Context mContext;

    public PopularAdapter(Context context, ArrayList<Popular> movies) {
        mContext = context;
        mPopular = movies;
    }

    @Override
    public PopularAdapter.AllComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movies_list_item, parent, false);
        AllComicViewHolder viewHolder = new AllComicViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(PopularAdapter.AllComicViewHolder holder, int position) {
        holder.bindPopular(mPopular.get(position));
    }

    @Override
    public int getItemCount() {
        return mPopular.size();
    }

    public class AllComicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movieImageView) ImageView mMovieImageView;
        @BindView(R.id.title) TextView mNameTitleView;
        private Context mContext;

        public AllComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("comics", Parcels.wrap(mPopular));
            mContext.startActivity(intent);
        }

        public void bindPopular(Popular popular) {
            Picasso.get().load(popular.getImage_thumbnail_path()).fit().into(mMovieImageView);
            mNameTitleView.setText(popular.getName());
        }
    }
}
