package demo;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;

public class GreetingTest {

    private AppController appCtrlr = new AppController();

    @Test
    public void appSaysHello() {
        assertThat(appCtrlr.greet(), containsString("Hello"));
    }
}