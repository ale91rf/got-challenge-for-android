package es.npatarino.android.gotchallenge.interartor;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.db.Contract;
import io.realm.Realm;

/**
 * Created by alejandro on 4/5/16.
 */
public class SetDataCharacterBDInteractor {

    private Realm mRealm;

    public SetDataCharacterBDInteractor(Realm aRealm){
        mRealm = aRealm;
    }

    public void setData(List<GoTCharacter> aCharacters){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (GoTCharacter lGoTCharacter: aCharacters){
                    realm.copyToRealmOrUpdate(Contract.createCharacter(lGoTCharacter));
                }
            }
        });
        mRealm.close();
    }

}
