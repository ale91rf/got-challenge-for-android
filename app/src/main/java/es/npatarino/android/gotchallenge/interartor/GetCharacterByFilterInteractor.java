package es.npatarino.android.gotchallenge.interartor;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.interartor.callback.GetDataCharacterCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.db.Character;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * Created by alejandro on 5/5/16.
 */
public class GetCharacterByFilterInteractor {

    private Realm mRealm;

    public GetCharacterByFilterInteractor(Realm aRealm){
        mRealm = aRealm;
    }

    public void getCharactersByFilter(GetDataCharacterCallback aCallback, String aId) {
        mRealm.where(Character.class)
                .equalTo("mHouseId", aId).findAllAsync().asObservable().subscribe(new Action1<RealmResults<Character>>() {
            @Override
            public void call(RealmResults<Character> characters) {
                List<GoTCharacter> lCharacterGoTList = new ArrayList<GoTCharacter>();

                for (Character lCharacter: characters){
                    lCharacterGoTList.add(createGoTCharacter(lCharacter));
                }

                aCallback.charactersFromDB(lCharacterGoTList);
            }
        });

    }

    private GoTCharacter createGoTCharacter(Character aCharacter){
        return new GoTCharacter(aCharacter.getmName(), aCharacter.getmImageUrl(), aCharacter.getmDescription(),
                aCharacter.getmHouseImageUrl(), aCharacter.getmHouseName(), aCharacter.getmHouseId());
    }
}
