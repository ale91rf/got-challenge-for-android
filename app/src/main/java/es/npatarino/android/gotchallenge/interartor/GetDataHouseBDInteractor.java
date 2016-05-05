package es.npatarino.android.gotchallenge.interartor;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.interartor.callback.GetDataHousesCallback;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.model.db.Contract;
import es.npatarino.android.gotchallenge.model.db.House;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * Created by alejandro on 5/5/16.
 */
public class GetDataHouseBDInteractor {

    private Realm mRealm;

    public GetDataHouseBDInteractor(Realm aRealm) {
        mRealm = aRealm;
    }

    public void getHouses(GetDataHousesCallback aCallback){
        mRealm.where(House.class).findAllAsync().asObservable()
                .subscribe(new Action1<RealmResults<House>>() {
                    @Override
                    public void call(RealmResults<House> houses) {
                        List<GoTHouse> lHousesList = new ArrayList<GoTHouse>();

                        for (House lHouse: houses) {
                            lHousesList.add(Contract.createGoTHouse(lHouse));
                        }

                        aCallback.housesFromDB(lHousesList);
                    }
                });

    }


}
