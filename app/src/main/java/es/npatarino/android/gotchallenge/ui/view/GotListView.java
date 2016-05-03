package es.npatarino.android.gotchallenge.ui.view;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by alejandro on 3/5/16.
 */
public interface GotListView extends GenericGoTListView{

    public void displayList(List<GoTCharacter> aList);


}
