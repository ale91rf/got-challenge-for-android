package es.npatarino.android.gotchallenge.interartor;

import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.networking.NetworkRequest;
import es.npatarino.android.gotchallenge.networking.RestApi;
import rx.Subscription;

/**
 * Created by alejandro on 3/5/16.
 */
public class DownloadDataInteractor {

    private RestApi mApi;

    private Subscription mGetDataSubscription;

    public DownloadDataInteractor(RestApi aApi) {
        mApi = aApi;
    }

    public void downloadData(final DownloadDataCallback aCallback) {
        Subscription mGetCharactersSubscription = NetworkRequest.performAsyncRequest(mApi.getCharacters(), (data) -> {
            aCallback.dataDownloaded(data);
        }, (error) -> {
            aCallback.dataNotDownloaded(error.toString());
        });
    }
}
