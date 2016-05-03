package es.npatarino.android.gotchallenge.ui.view;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by alejandro on 3/5/16.
 */
public interface GotListView {

    public void injectDependencies();

    public void showProgressBar();

    public void hideProgressBar();

    public void getData();

    public void displayList(List<GoTCharacter> aList);

    public void showMessage(String aMessage);

    public void setupRecyclerView();

}
