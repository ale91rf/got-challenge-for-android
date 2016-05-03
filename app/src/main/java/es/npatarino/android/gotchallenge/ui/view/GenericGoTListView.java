package es.npatarino.android.gotchallenge.ui.view;

/**
 * Created by alejandro on 3/5/16.
 */
public interface GenericGoTListView {

    public void injectDependencies();

    public void showProgressBar();

    public void hideProgressBar();

    public void setupRecyclerView();

    public void showMessage(String aMessage);

    public void getData();
}
