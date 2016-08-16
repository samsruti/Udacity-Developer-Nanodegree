package samsruti.udacity.com.portfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String string = "This button will launch : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClickPopularMovies(View view){
        Toast.makeText(view.getContext(),string+"Popular Movies",Toast.LENGTH_SHORT).show();
    }
    public void onClickStockHawk(View view){
        Toast.makeText(view.getContext(),string+"Stock Hawk",Toast.LENGTH_SHORT).show();
    }
    public void onClickBuildItBigger(View view){
        Toast.makeText(view.getContext(),string+"Build It Bigger",Toast.LENGTH_SHORT).show();
    }
    public void onClickMakeYourAppMaterial(View view){
        Toast.makeText(view.getContext(),string+"Make your app material",Toast.LENGTH_SHORT).show();
    }
    public void onClickGoUbiquitous(View view){
        Toast.makeText(view.getContext(),string+"Go Ubiquitous",Toast.LENGTH_SHORT).show();
    }
    public void onClickCapstone(View view){
        Toast.makeText(view.getContext(),string+"Capstone",Toast.LENGTH_SHORT).show();
    }

}
