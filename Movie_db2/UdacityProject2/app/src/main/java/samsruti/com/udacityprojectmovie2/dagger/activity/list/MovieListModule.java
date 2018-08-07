package samsruti.com.udacityprojectmovie2.dagger.activity.list;

import android.content.Context;

import samsruti.com.udacityprojectmovie2.MovieListPresenter;
import samsruti.com.udacityprojectmovie2.MovieListView;
import samsruti.com.udacityprojectmovie2.retrofit.MovieService;

import dagger.Module;
import dagger.Provides;

@MovieListScope
@Module
public class MovieListModule {

    private final MovieListView movieListView;

    public MovieListModule(MovieListView movieListView) {
        this.movieListView = movieListView;
    }

    @Provides
    @MovieListScope
    public MovieListView movieListView() {
        return movieListView;
    }

    @Provides
    @MovieListScope
    public MovieListPresenter movieListPresenter(MovieListView movieListView, MovieService movieService, Context context) {
        return new MovieListPresenter(movieListView, movieService, context);
    }


}
