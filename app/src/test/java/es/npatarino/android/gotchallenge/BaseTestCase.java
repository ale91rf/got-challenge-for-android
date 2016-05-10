package es.npatarino.android.gotchallenge;


import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTestCase {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
