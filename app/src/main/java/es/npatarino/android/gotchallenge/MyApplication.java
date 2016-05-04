package es.npatarino.android.gotchallenge;

import android.app.Application;

import es.npatarino.android.gotchallenge.util.Constants;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by alejandro on 4/5/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration mRealmConfiguration = new RealmConfiguration.Builder(this.getApplicationContext())
                .name(Constants.DB_NAME)
                .build();
        Realm.setDefaultConfiguration(mRealmConfiguration);
    }
}
