package samsruti.com.udacityprojectmovie2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import samsruti.com.udacityprojectmovie2.application.MovieApplication;
import samsruti.com.udacityprojectmovie2.dagger.activity.details.DaggerMovieDetailsActivityComponent;
import samsruti.com.udacityprojectmovie2.dagger.activity.details.MovieDetailsActivityComponent;
import samsruti.com.udacityprojectmovie2.models.MovieModel;
import samsruti.com.udacityprojectmovie2.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolImage)
    ImageView toolImage;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        MovieModel movieModel =  getIntent().getParcelableExtra("movie");

        MovieDetailsActivityComponent movieDetailsActivityComponent = DaggerMovieDetailsActivityComponent.builder()
                .movieApplicationComponent(MovieApplication.get(this).getMovieApplicationComponent())
                .build();

        movieDetailsActivityComponent.injectMovieDetailsActivity(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            picasso.load(Constants.IMAGE_URL + "/w500" + movieModel.getBackdrop_path() + "?api_key?=" + BuildConfig.API_KEY).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(toolImage);
        }


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable("movie", movieModel);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                supportFinishAfterTransition();
                super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
