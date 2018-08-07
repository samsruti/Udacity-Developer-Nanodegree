package samsruti.com.udacityprojectmovie2.application;


import android.app.Activity;
import android.app.Application;

import samsruti.com.udacityprojectmovie2.dagger.app.ContextModule;
import samsruti.com.udacityprojectmovie2.dagger.app.DaggerMovieApplicationComponent;
import samsruti.com.udacityprojectmovie2.dagger.app.MovieApplicationComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieApplication extends Application {

    private MovieApplicationComponent movieApplicationComponent;

    public static MovieApplication get(Activity activity) {
        return (MovieApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        movieApplicationComponent = DaggerMovieApplicationComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public MovieApplicationComponent getMovieApplicationComponent() {
        return movieApplicationComponent;
    }

}
