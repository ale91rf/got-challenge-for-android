package es.npatarino.android.gotchallenge.interartor;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.interartor.callback.GetDataCharacterCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.db.Character;
import es.npatarino.android.gotchallenge.model.db.Contract;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * Created by alejandro on 5/5/16.
 */
public class GetDataCharacterByQueryInteractor {

    private Realm mRealm;

    public GetDataCharacterByQueryInteractor(Realm aRealm){
        mRealm = aRealm;
    }

    public void getCharacterByQuery(GetDataCharacterCallback aCallback, String aQuery){

        mRealm.where(Character.class)
                .contains("mName", aQuery, Case.INSENSITIVE).findAllAsync().asObservable().subscribe(new Action1<RealmResults<Character>>() {
            @Override
            public void call(RealmResults<Character> characters) {
                List<GoTCharacter> lCharacterGoTList = new ArrayList<GoTCharacter>();

                for (Character lCharacter: characters){
                    lCharacterGoTList.add(Contract.createGoTCharacter(lCharacter));
                }

                aCallback.charactersFromDB(lCharacterGoTList);
            }
        });

    }
}
