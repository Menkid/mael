/**
 * Created by Johan on 11.12.2016.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class ConnexionTest {
    City myCity;
    City city0;
    City city1;
    City city2;
    City city3;
    City city4;

    // West Palm Beach, FL[2672,8005]63305
    private String name = "West Palm Beach";
    private String state = "FL";
    private int[] pos = {2672, 8005};
    private int size = 63305;

    private String name0 = "City0";
    private String name1 = "City1";
    private String name2 = "City2";
    private String name3 = "City3";
    private String name4 = "City4";

    private int dist0 = 95;
    private int dist1 = 96;
    private int dist2 = 97;
    private int dist3 = 98;
    private int dist4 = 99;


    @Before
    public void setUp() throws Exception {
        myCity = new City(name, state, pos[0], pos[1], size);
        city0 = new City(name0, state, pos[0], pos[1], size);
        city1 = new City(name1, state, pos[0], pos[1], size);
        city2 = new City(name2, state, pos[0], pos[1], size);
        city3 = new City(name3, state, pos[0], pos[1], size);
        city4 = new City(name4, state, pos[0], pos[1], size);
        myCity.addConnexion(city0, dist0);
        myCity.addConnexion(city1, dist1);
        myCity.addConnexion(city2, dist2);
        myCity.addConnexion(city3, dist3);
        myCity.addConnexion(city4, dist4);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getDist() throws Exception {
        assertEquals(dist0, myCity.getDistTo(city0));
    }

    @Test
    public void getClosest() throws Exception {
        assertEquals(city0, myCity.getClosest(null));
    }

    @Test
    public void getClosestIgnoreList() throws Exception {
        HashSet<City> ignore = new HashSet<>();
        ignore.add(city0);
        ignore.add(city2);
        assertEquals(city1, myCity.getClosest(ignore));
    }

    @Test
    public void remConnexion() throws Exception {
        myCity.remConnexion(city0);
        assertEquals(city1, myCity.getClosest(null));
    }


}
