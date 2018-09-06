package com.sa.mynotes.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sa.mynotes.R;
import com.sa.mynotes.Utils;
import com.sa.mynotes.models.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deepak on 24/01/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder> {

    private List<Movie> mNotesList;

    public void updateDataSet(List<Movie> mNotesList) {
        if(mNotesList != null && !mNotesList.isEmpty()){
            mNotesList.clear();
        }
        this.mNotesList = mNotesList;
        notifyDataSetChanged();
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        Movie movie = mNotesList.get(position);
        holder.movieName.setText(movie.getYear());
        holder.movieRatings.setText("Ratings- " + movie.getRatings());
        holder.movieYear.setText("Year- " + movie.getTitle());

        switch (position) {
            case 0:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie1)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 1:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie2)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 2:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie3)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 3:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie4)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 4:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie5)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 5:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie6)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 6:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie7)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 7:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie8)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 8:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie9)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

            case 9:
                Picasso.with(holder.context)
                        .load(R.mipmap.movie10)
                        .resize(80, 108)
                        .centerInside()
                        .into(holder.poster);
                break;

        }

        holder.cardItemView.setTag(movie);
    }

    @Override
    public int getItemCount() {
        return mNotesList.size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_name)
        TextView movieName;

        @BindView(R.id.movie_year)
        TextView movieYear;

        @BindView(R.id.movie_ratings)
        TextView movieRatings;

        @BindView(R.id.info_link_tv)
        TextView movieLink;

        @BindView(R.id.movie_poster)
        ImageView poster;

        @BindView(R.id.movie_card_view)
        CardView cardItemView;


        private Context context;

        @OnClick(R.id.movie_card_view)
        public void onMovieItemClicked(View view) {
            Movie note = (Movie) view.getTag();
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(WebViewActivity.MOVIE_DATA,
                    Utils.getMovieImdbLinkUrl(note.getMovieCode()));
            context.startActivity(intent);
        }


        public MovieItemViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }
    }
}
