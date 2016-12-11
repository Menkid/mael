import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;

import static org.junit.Assert.*;

/**
 * Created by Johan on 11.12.2016.
 */
public class CityTest {
    private City myCity;

    // West Palm Beach, FL[2672,8005]63305
    private String name = "West Palm Beach";
    private String state = "FL";
    private int[] pos = {2672, 8005};
    private int size = 63305;

    @After
    public void tearDown() throws Exception {
        assertArrayEquals(name.toCharArray(), myCity.getName().toCharArray());
        assertArrayEquals(state.toCharArray(), myCity.getState().toCharArray());
        assertArrayEquals(pos, myCity.getPos());
        assertEquals(size, myCity.getSize());
    }

    @Test
    public void singleArg() throws Exception {
        myCity = new City(name + ", " + state + "[" + pos[0] + "," + pos[1] + "]" + size);
    }

    @Test
    public void multipleArgs() throws Exception {
        myCity = new City(name, state, pos[0], pos[1], size);
    }

}