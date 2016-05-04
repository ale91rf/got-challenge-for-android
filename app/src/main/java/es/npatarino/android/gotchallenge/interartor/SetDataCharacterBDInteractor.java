package es.npatarino.android.gotchallenge.interartor;

import java.util.List;

import es.npatarino.android.gotchallenge.db.Character;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
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
                    realm.copyToRealmOrUpdate(createCharacter(lGoTCharacter));
                }
            }
        });
        mRealm.close();
    }

    private Character createCharacter(GoTCharacter aGoTCharacter){
        Character lCharacter = new Character();
        lCharacter.setmName(aGoTCharacter.getName());
        lCharacter.setmImageUrl(aGoTCharacter.getImageUrl());
        lCharacter.setmDescription(aGoTCharacter.getDescription());
        lCharacter.setmHouseImageUrl(aGoTCharacter.getHouseImageUrl());
        lCharacter.setmHouseName(aGoTCharacter.getHouseName());
        lCharacter.setmHouseId(aGoTCharacter.getHouseId());

        return lCharacter;
    }
}
