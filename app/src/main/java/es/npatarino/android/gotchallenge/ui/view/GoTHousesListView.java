package es.npatarino.android.gotchallenge.ui.view;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by alejandro on 3/5/16.
 */
public interface GoTHousesListView extends GenericGoTListView {

    public void displayList(List<GoTHouse> aList);

}
