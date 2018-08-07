package samsruti.com.udacityprojectmovie2;


import java.util.ArrayList;

import samsruti.com.udacityprojectmovie2.models.MovieModel;

public interface MovieListView {

    void setProgressBarVisibility(int visibility);

    void startMovieDetailFragment(MovieDetailFragment movieDetailFragment);

    void startMovieDetailActivity(MovieModel movieModel);

    void setMenuItemChecked(int id);

    void showMovies(ArrayList<MovieModel> movieModels);

}
