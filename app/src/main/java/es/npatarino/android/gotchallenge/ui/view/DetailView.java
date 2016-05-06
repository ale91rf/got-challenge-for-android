package es.npatarino.android.gotchallenge.ui.view;

/**
 * Created by alejandro on 4/5/16.
 */
public interface DetailView extends GenericActivity{

    public void injectDependencies();

    public void getData();

    public void showData();

    public void showMessage(String aMessage);

}
