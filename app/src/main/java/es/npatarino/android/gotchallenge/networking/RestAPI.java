package es.npatarino.android.gotchallenge.networking;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by alejandro on 2/5/16.
 */
public interface RestApi {

    String API_ENDPOINT = "http://52.18.228.107:3000/";

    @GET("characters")
    Observable<List<GoTCharacter>> getCharacters();
}
