package com.codewithankit.mymoviedirectory.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithankit.mymoviedirectory.Activities.MovieDetailsActivity;
import com.codewithankit.mymoviedirectory.Model.Movie;
import com.codewithankit.mymoviedirectory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Movie>movieList;

    public MovieRecyclerViewAdapter(Context context , List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }

    @NonNull


    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row , parent , false);
        return new ViewHolder(v , context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie m = movieList.get(position);
        String posterLink =m.getPoster();

        holder.title.setText(m.getTitle());
        holder.type.setText(m.getMovieType());
        Picasso.with(context).load(posterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);
        holder.year.setText(m.getYear());
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

        public ViewHolder(@NonNull View itemView , final Context ctx) {
            super(itemView);
            context = ctx;

            title = (TextView)itemView.findViewById(R.id.movieTitleId);
            poster = (ImageView)itemView.findViewById(R.id.movieImageId);
            year = (TextView)itemView.findViewById(R.id.movieReleaseId);
            type = (TextView)itemView.findViewById(R.id.movieCatID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Movie movie = movieList.get(getAdapterPosition());
                    Intent intent = new Intent(context , MovieDetailsActivity.class);
                    intent.putExtra("movie" , movie);

                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
