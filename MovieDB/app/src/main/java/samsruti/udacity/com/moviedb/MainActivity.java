package samsruti.udacity.com.moviedb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://api.themoviedb.org/3/discover/movie?";
    private String API_KEY ="api_key=8d966ddf22f9aa22d6b0554c163f347c";
    private String sortMostPopularity = "&sort_by=popularity.desc";
    private String sortHighRated = "&sort_by=vote_average.desc";

//    JSON Node Names
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "original_title";
    private static final String TAG_POSTER_URL = "poster_path";
    private static final String TAG_OVERVIEW = "overview";
    private static final String TAG_VOTE_AVERAGE = "vote_average";

    ArrayList<String> movieDesc = new ArrayList<String>();
    ArrayList<String> movieVotes = new ArrayList<String>();
    ArrayList<String> movieTitle = new ArrayList<String>();
    ArrayList<String> imageURLs = new ArrayList<String>();
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String urlFinal = URL + API_KEY;
        new ProcessJSON().execute(urlFinal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.sortMostPopularity){
            String urlFinal = URL + API_KEY + sortMostPopularity;
            new ProcessJSON().execute(urlFinal);
            Toast.makeText(getApplicationContext(),"Sorted with Most Popular Movies",Toast.LENGTH_SHORT).show();
        }
        if(itemId == R.id.sortHighRated){
            String urlFinal = URL + API_KEY+ sortHighRated;
            new ProcessJSON().execute(urlFinal);
            Toast.makeText(getApplicationContext(),"Sorted with High Rated Movies",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private class ProcessJSON extends AsyncTask<String, Void,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
//            Dispaly a message to wait..
        }

        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler httpDataHandler = new HTTPDataHandler();
            stream = httpDataHandler.GetHTTPData(urlString);
//            Return the data from the specified URL
            return stream;
        }
        protected void onPostExecute(String stream){
            final ArrayList<HashMap<String,String>> movieLists = new ArrayList<HashMap<String, String>>();
            if(stream!=null){
                try {

                    JSONObject reader = new JSONObject(stream);
                    JSONArray moviesResults = reader.getJSONArray("results");
                    Log.v("Results: ", String.valueOf(moviesResults));

                    for (int i = 0;i<moviesResults.length();i++){
                        JSONObject movieObject = moviesResults.getJSONObject(i);
                        final String movieName, posterURL, id, overview, vote_average;
                        movieName = movieObject.getString(TAG_TITLE);
                        id = String.valueOf(movieObject.getString(TAG_ID));
                        posterURL = "https://image.tmdb.org/t/p/w342"+ movieObject.getString(TAG_POSTER_URL);
                        overview = movieObject.getString(TAG_OVERVIEW);
                        vote_average = "\nRating: "+String.valueOf(movieObject.getString(TAG_VOTE_AVERAGE));
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put(TAG_ID,id);
                        map.put(TAG_TITLE,movieName);
                        map.put(TAG_OVERVIEW,overview);
                        map.put(TAG_POSTER_URL,posterURL);
                        map.put(TAG_VOTE_AVERAGE,vote_average);
                        movieTitle.add(i,movieName);
                        movieVotes.add(i,vote_average);
                        movieDesc.add(i,overview);
                        imageURLs.add(i,posterURL);
                        Log.v("Val: ",imageURLs.get(i));

                    }
                    ImageAdapter adapterView = new ImageAdapter(MainActivity.this, imageURLs, movieTitle);
                    gridView =  (GridView) findViewById(R.id.gridview);
                    gridView.setAdapter(adapterView);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int i, long id) {
                            Intent intent = new Intent(MainActivity.this, MovieDetails.class);
//                            intent.putExtra(TAG_ID, id );
                            intent.putExtra(TAG_VOTE_AVERAGE,movieVotes.get(i) );
                            intent.putExtra(TAG_POSTER_URL, imageURLs.get(i));
                            intent.putExtra(TAG_OVERVIEW, movieDesc.get(i));
                            intent.putExtra(TAG_TITLE, movieTitle.get(i));
                            startActivity(intent);
                        }
                    });


                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
