package com.marcinmejner.movievol.Data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marcinmejner.movievol.Activities.MovieDetailActivity;
import com.marcinmejner.movievol.Model.Movie;
import com.marcinmejner.movievol.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Marc on 14.02.2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>{
    private Context contex;
    private List<Movie> movieList;

    public MovieRecyclerViewAdapter(Context contex, List<Movie> movies) {

        this.contex = contex;
        movieList = movies;
    }

    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row, parent, false);

        return new ViewHolder(view, contex);
    }

    @Override
    public void onBindViewHolder(MovieRecyclerViewAdapter.ViewHolder holder, int position) {

        Movie movie = movieList.get(position);
        String posterLink = movie.getPoster();
        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getMovieType());

        Picasso.with(contex)
                .load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.poster);

        holder.year.setText(movie.getYear());





    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView poster;
        TextView year;
        TextView type;


        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            contex = ctx;

            title = itemView.findViewById(R.id.movieTitleId);
            poster = itemView.findViewById(R.id.movieImageId);
            year = itemView.findViewById(R.id.movieReleaseId);
            type = itemView.findViewById(R.id.movieCatId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Movie movie = movieList.get(getAdapterPosition());

                    Intent intent = new Intent(contex, MovieDetailActivity.class);

                    intent.putExtra("movie", movie);

                    ctx.startActivity(intent);


                }
            });
        }

        @Override
        public void onClick(View view) {
        }
    }
}
