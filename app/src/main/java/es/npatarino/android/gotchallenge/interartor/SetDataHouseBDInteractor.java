package es.npatarino.android.gotchallenge.interartor;

import java.util.List;

import es.npatarino.android.gotchallenge.model.db.Contract;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import io.realm.Realm;

/**
 * Created by alejandro on 5/5/16.
 */
public class SetDataHouseBDInteractor {

    private Realm mRealm;

    public SetDataHouseBDInteractor(Realm aRealm) {
        mRealm = aRealm;
    }

    public void setData(List<GoTHouse> aHouses){

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (GoTHouse lHouse: aHouses) {
                    realm.copyToRealmOrUpdate(Contract.createHouse(lHouse));
                }
            }
        });

    }

}
