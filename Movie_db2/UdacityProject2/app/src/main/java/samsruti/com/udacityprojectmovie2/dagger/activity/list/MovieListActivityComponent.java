package samsruti.com.udacityprojectmovie2.dagger.activity.list;

import samsruti.com.udacityprojectmovie2.MovieListActivity;
import samsruti.com.udacityprojectmovie2.dagger.app.ContextModule;
import samsruti.com.udacityprojectmovie2.dagger.app.MovieApplicationComponent;

import dagger.Component;

@MovieListScope
@Component(modules = {MovieListModule.class, ContextModule.class}, dependencies = MovieApplicationComponent.class)
public interface MovieListActivityComponent {

    void injectMovieListActivity(MovieListActivity movieListActivity);

}
