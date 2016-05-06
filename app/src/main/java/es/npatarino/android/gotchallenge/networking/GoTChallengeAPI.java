package es.npatarino.android.gotchallenge.networking;

import android.content.Context;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by alejandro on 2/5/16.
 */
public class GoTChallengeAPI {

    private static RestAPI mRestApi;

    //private constructor, so it can not be instantiated
    private GoTChallengeAPI(){};

    public static RestAPI getApiInterface(Context aContext) {

        if (mRestApi == null) {
            createInstance(aContext);
        }

        return mRestApi;
    }

    private static void createInstance(Context aContext) {
        synchronized (GoTChallengeAPI.class) {
            if (mRestApi == null) {
                mRestApi = buildApliClient(aContext);
            }
        }
    }

    private static RestAPI buildApliClient(Context aContext) {

        OkHttpClient.Builder lBuilder = new OkHttpClient.Builder();
        lBuilder.cache(createCache(aContext));

        OkHttpClient lClient = lBuilder.build();

        Retrofit lRetrofit = new Retrofit.Builder()
                .baseUrl(RestAPI.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(lClient)
                .build();

        return lRetrofit.create(RestAPI.class);

    }


    private static Cache createCache(Context aContext) {
        File lHttpCacheDirectory = new File(aContext.getApplicationContext()
                .getCacheDir().getAbsolutePath(), "HttpCache");
        return new Cache(lHttpCacheDirectory, 10 * 1024);
    }
}
