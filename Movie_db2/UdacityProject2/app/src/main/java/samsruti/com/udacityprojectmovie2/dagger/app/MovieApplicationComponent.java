package samsruti.com.udacityprojectmovie2.dagger.app;

import com.squareup.picasso.Picasso;
import samsruti.com.udacityprojectmovie2.retrofit.MovieService;

import dagger.Component;
import io.realm.Realm;

@MovieApplicationScope
@Component(modules = {MovieServiceModule.class, PicassoModule.class})
public interface MovieApplicationComponent {

    Picasso picasso();

    MovieService movieService();

    Realm realm();
}
