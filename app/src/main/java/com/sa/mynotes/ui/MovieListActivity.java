package com.sa.mynotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sa.mynotes.R;
import com.sa.mynotes.models.Movie;
import com.sa.mynotes.repo.MovieRepoWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.movie_list)
    RecyclerView mNotesListView;

    @BindView(R.id.no_notes_avaiable_text)
    TextView mNoNotesAvailableMessage;

    @BindView(R.id.add_new_note_view)
    FloatingActionButton mAddNewNoteButton;

    List<Movie> movieList = new ArrayList<>();

    private MovieListAdapter movieListAdapter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        mUnbinder = ButterKnife.bind(this);

        initUI();
    }

    private void getMovieData () {

        String[] movieCode = getResources().getStringArray(R.array.movie_code);
        String[] movieName = getResources().getStringArray(R.array.movie_list);
        String[] movieYear = getResources().getStringArray(R.array.release_year);
        String[] movieRatings = getResources().getStringArray(R.array.ratings);

        for(int i=0; i<movieCode.length; i++) {
            Movie movie = new Movie();
            movie.setMovieCode(movieCode[i]);
            movie.setYear(movieName[i]);
            movie.setTitle(movieYear[i]);
            movie.setRatings(movieRatings[i]);
            movieList.add(movie);
        }

        if (movieList.size() > 0) {
            movieListAdapter.updateDataSet(movieList);
            mNoNotesAvailableMessage.setVisibility(View.GONE);
            mNotesListView.setVisibility(View.VISIBLE);
        } else {
            mNoNotesAvailableMessage.setVisibility(View.VISIBLE);
            mNotesListView.setVisibility(View.GONE);
        }
    }

    private void initUI() {
        movieListAdapter = new MovieListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNotesListView.setLayoutManager(linearLayoutManager);
        mNotesListView.setAdapter(movieListAdapter);
        fetchNotesList();
        if(movieList == null || movieList.isEmpty()){
            getMovieData();
            MovieRepoWrapper.getInstance().addAllMovies(movieList);
        }
    }

    /*
        fetches notes movie from repo if available
     */
    @Override
    protected void onResume() {
        super.onResume();
        //fetchNotesList();
    }

    private void fetchNotesList() {
        List<Movie> moviesList = MovieRepoWrapper.getInstance().getMoviesList();
        if (moviesList.size() > 0) {
            movieListAdapter.updateDataSet(moviesList);
            mNoNotesAvailableMessage.setVisibility(View.GONE);
            mNotesListView.setVisibility(View.VISIBLE);
        } else {
            mNoNotesAvailableMessage.setVisibility(View.VISIBLE);
            mNotesListView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MovieRepoWrapper.getInstance().closeRealm();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
