package es.npatarino.android.gotchallenge.interartor;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.db.Character;
import es.npatarino.android.gotchallenge.interartor.callback.GetDataCallback;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * Created by alejandro on 4/5/16.
 */
public class GetDataCharacterBDInteractor {

    private Realm mRealm;

    public GetDataCharacterBDInteractor(Realm aRealm){
        mRealm = aRealm;
    }

    public void getData(GetDataCallback aCallback){
        mRealm.where(Character.class).findAllAsync().asObservable()
                .subscribe(new Action1<RealmResults<Character>>() {
                    @Override
                    public void call(RealmResults<Character> characters) {
                        List<GoTCharacter> characterGoTList = new ArrayList<GoTCharacter>();

                        for (Character lCharacter: characters){
                            characterGoTList.add(createGoTCharacter(lCharacter));
                        }

                        aCallback.dataFromDB(characterGoTList);
                    }
                });

    }

    public void getDataWithHouseId(String aId){

    }

    private GoTCharacter createGoTCharacter(Character aCharacter){
        return new GoTCharacter(aCharacter.getmName(), aCharacter.getmImageUrl(), aCharacter.getmDescription(),
                aCharacter.getmHouseImageUrl(), aCharacter.getmHouseName(), aCharacter.getmHouseId());
    }
}
