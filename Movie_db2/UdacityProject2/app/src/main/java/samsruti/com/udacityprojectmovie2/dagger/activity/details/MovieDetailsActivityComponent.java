package samsruti.com.udacityprojectmovie2.dagger.activity.details;

import samsruti.com.udacityprojectmovie2.MovieDetailActivity;
import samsruti.com.udacityprojectmovie2.MovieDetailFragment;
import samsruti.com.udacityprojectmovie2.dagger.app.MovieApplicationComponent;

import dagger.Component;

@MovieDetailsScope
@Component(dependencies = MovieApplicationComponent.class)
public interface MovieDetailsActivityComponent {

    void injectMovieDetailsActivity(MovieDetailActivity movieDetailActivity);

    void injectMovieDetailsFragment(MovieDetailFragment movieDetailFragment);
}
