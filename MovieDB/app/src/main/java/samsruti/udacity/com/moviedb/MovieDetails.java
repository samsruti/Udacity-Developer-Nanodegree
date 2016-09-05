package samsruti.udacity.com.moviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    //    JSON Node Names
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "original_title";
    private static final String TAG_POSTER_URL = "poster_path";
    private static final String TAG_OVERVIEW = "overview";
    private static final String TAG_VOTE_AVERAGE = "vote_average";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        TextView movieTitle = (TextView) findViewById(R.id.movieName);
        TextView overview = (TextView) findViewById(R.id.description);
        TextView rating = (TextView) findViewById(R.id.rating);
        ImageView poster = (ImageView) findViewById(R.id.poster);
        String titleString = intent.getStringExtra(TAG_TITLE);
        String URLString = intent.getStringExtra(TAG_POSTER_URL);
        String Rating = intent.getStringExtra(TAG_VOTE_AVERAGE);
        String Desc = intent.getStringExtra(TAG_OVERVIEW);

        movieTitle.setText(titleString);
        overview.setText(Desc);
        rating.setText(Rating);
        Picasso.with(getApplicationContext()).load(URLString).resize(120, 120).into(poster);
    }
}
