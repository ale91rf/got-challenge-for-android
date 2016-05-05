package es.npatarino.android.gotchallenge.model.db;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by alejandro on 5/5/16.
 */
public class Contract {

    public static GoTCharacter createGoTCharacter(Character aCharacter){
        return new GoTCharacter(aCharacter.getmName(), aCharacter.getmImageUrl(), aCharacter.getmDescription(),
                aCharacter.getmHouseImageUrl(), aCharacter.getmHouseName(), aCharacter.getmHouseId());
    }

    public static GoTHouse createGoTHouse(House aHouse){
        return new GoTHouse(aHouse.getmHouseImageUrl(), aHouse.getmHouseName(), aHouse.getmHouseId());
    }

    public static Character createCharacter(GoTCharacter aGoTCharacter){
        Character lCharacter = new Character();
        lCharacter.setmName(aGoTCharacter.getName());
        lCharacter.setmImageUrl(aGoTCharacter.getImageUrl());
        lCharacter.setmDescription(aGoTCharacter.getDescription());
        lCharacter.setmHouseImageUrl(aGoTCharacter.getHouseImageUrl());
        lCharacter.setmHouseName(aGoTCharacter.getHouseName());
        lCharacter.setmHouseId(aGoTCharacter.getHouseId());

        return lCharacter;
    }

    public static House createHouse(GoTHouse aHouse){
        House lHouse = new House();

        lHouse.setmHouseId(aHouse.getHouseId());
        lHouse.setmHouseImageUrl(aHouse.getHouseImageUrl());
        lHouse.setmHouseName(aHouse.getHouseName());

        return lHouse;
    }
}
