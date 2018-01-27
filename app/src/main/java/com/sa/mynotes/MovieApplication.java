package com.sa.mynotes;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by deepak on 24/01/18.
 */

public class MovieApplication extends Application {

    public static final String DB_NAME = "Movie.Realm";
    public static final int REALM_SCHEMA = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    /*
        Initializing realm
     */
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().
                name(DB_NAME).schemaVersion(REALM_SCHEMA).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
