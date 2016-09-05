package samsruti.udacity.com.moviedb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by samsrutidash on 9/5/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    String[] movieTitle = new String[100];
    String[] posterUrl = new String[100];



//    private final String[] movieCast;


    public ImageAdapter(Context c ,ArrayList<String> posterURLImage ,ArrayList<String> OriginalTitle){
        mContext = c;
        String[] urlImages = Arrays.copyOf(posterURLImage.toArray(), posterURLImage.toArray().length, String[].class);
        String[] titleMovies = Arrays.copyOf(OriginalTitle.toArray(), OriginalTitle.toArray().length, String[].class);
        this.posterUrl = urlImages;
        this.movieTitle = titleMovies;
//          this.movieCast = movieCast;
    }
    public int getCount() {
        return movieTitle.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.gridview_layout,null);
            TextView movieTitleView = (TextView) gridView.findViewById(R.id.movieName);
            ImageView poster = (ImageView) gridView.findViewById(R.id.poster);
            Log.v("Error:","URL "+posterUrl[position]);
            movieTitleView.setText(movieTitle[position]);
            Picasso.with(mContext).load(posterUrl[position]).resize(120, 120).into(poster);
        }
        else {
        gridView = (View) convertView;
        }
        return gridView;
    }

}
