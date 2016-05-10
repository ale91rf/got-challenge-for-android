package es.npatarino.android.gotchallenge.interartor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.BaseTestCase;
import es.npatarino.android.gotchallenge.interartor.callback.DownloadDataCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.networking.RestAPI;
import rx.Observable;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
public class DownloadDataInteractorTest extends BaseTestCase{

    @Mock
    private RestAPI mApi;

    private DownloadDataInteractor mInteractor;


    @Test
    public void itShouldReturnCharactersList() throws Exception {
        //as if two objects returned
        when(mApi.getCharacters()).thenReturn(Observable.just(getData()));
        mInteractor = new DownloadDataInteractor(mApi);

        mInteractor.downloadData(new DownloadDataCallback() {
            @Override
            public void dataDownloaded(List<GoTCharacter> aCharacters) {
                assertEquals(2, aCharacters.size());
            }

            @Override
            public void dataNotDownloaded(String aText) {

            }
        });

    }

    @Test
    public void itShouldReturnError() throws Exception {
        //as if an error returned
        when(mApi.getCharacters()).thenReturn(Observable.error(new Throwable("error")));
        mInteractor = new DownloadDataInteractor(mApi);

        mInteractor.downloadData(new DownloadDataCallback() {
            @Override
            public void dataDownloaded(List<GoTCharacter> aCharacters) {
            }

            @Override
            public void dataNotDownloaded(String aText) {
                assertEquals(aText, "error");
            }
        });

    }

    private List<GoTCharacter> getData(){
        List<GoTCharacter> lList = new ArrayList<>();
        GoTCharacter lCharacterOne = new GoTCharacter("w", "www,w,www", "no", "www.ww.www",
                "ww", "www");
        GoTCharacter lCharacterTwo = new GoTCharacter("w", "www,w,www", "no", "www.ww.www",
                "ww", "www");
        lList.add(lCharacterOne);
        lList.add(lCharacterTwo);

        return lList;
    }

}