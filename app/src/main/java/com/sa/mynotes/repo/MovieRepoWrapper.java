package com.sa.mynotes.repo;

import com.sa.mynotes.models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by deepak on 24/01/18.
 */

public class MovieRepoWrapper {

    private static MovieRepoWrapper instance;
    private final Realm mRealm;

    private MovieRepoWrapper() {
        mRealm = Realm.getDefaultInstance();
    }

    public static MovieRepoWrapper getInstance() {
        instance = new MovieRepoWrapper();
        return instance;
    }


    /*
        Fetch all saved movies
     */
    public List<Movie> getMoviesList() {
        List<Movie> list = new ArrayList<>();
        mRealm.beginTransaction();
        try {
            RealmResults<Movie> results = mRealm
                    .where(Movie.class)
                    .findAll();
            list.addAll(mRealm.copyFromRealm(results));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRealm.commitTransaction();
        return list;
    }


    /*
        Add a new Movie
     */
    public void addMovies(Movie movie) {
/*        if (movie.getMovieCode() == 0) {
            int randomNo = new Random().nextInt(50) + 1;
            movie.setMovieCode(randomNo);
        }*/
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(movie);
        mRealm.commitTransaction();
    }

    public void closeRealm() {
        mRealm.close();
    }

}
