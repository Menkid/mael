import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * @author Mael Cattin
 */
public class City {
    private String name;
    private String state;
    private int[] pos;
    private int size;
    private LinkedHashMap<City, Integer> connexion;


    /*
     * Creates a city from the one-line description.
     *
     * @param description String with format "Name, state_twoChrUCase[longitude,latitude]population"
     */
    public City(String description) throws IllegalArgumentException{
        String[] split = description.split("[,\\[\\]] *");
        if (split.length != 5 || split[1].length() != 2 || !split[2].matches("[0-9]+") || !split[3].matches("[0-9]+") || !split[4].matches("[0-9]+")){
            throw new IllegalArgumentException("Illegal format of input string :\n\t" + description);
        }
        name = split[0];
        state = split[1];
        pos = new int[]{Integer.parseInt(split[2]), Integer.parseInt(split[3])};
        size = Integer.parseInt(split[4]);
        connexion = new LinkedHashMap<>();
    }

    /*
     * Creates a city.
     *
     * @param name  Name of the city
     * @param state Two letter ISO code of city's state
     * @param lon   Longitude of the city
     * @param lat   Latitude of the city
     * @param size  Population of the city
     */
    public City(String name, String state, int lon, int lat, int size){
        if (state.length() != 2){
            throw new IllegalArgumentException("Illegal format of input string :\n\t" + state);
        }
        this.name = name;
        this.state = state;
        pos = new int[]{lon, lat};
        this.size = size;
        connexion = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public int[] getPos() {
        return pos;
    }

    public int getSize() {
        return size;
    }


    /*
     * Add an edge between current city and one other.
     *
     * @param dest   Second city
     * @param weight Distance between cities
     */
    public void addConnexion(City dest, int weight){
        if(connexion.containsKey(dest)){
            throw new IllegalArgumentException(dest.getName() + " already in connexions list");
        }
        connexion.put(dest, weight);
        try {
            dest.addConnexion(this, weight);
        } catch (IllegalArgumentException ignored){

        }
    }

    public void remConnexion(City dest){
        connexion.remove(dest);
    }

    /**
     * @param dest Destination city
     * @return     Distance between current city and destination city
     * @throws NoSuchElementException When no distance is set
     */
    public int getDistTo(City dest) throws NoSuchElementException {
        if (!connexion.containsKey(dest)) {
            throw new NoSuchElementException(this.name + "->" + dest.name);
        }
        return connexion.get(dest);
    }

    /**
     * Get the closest city.
     *
     * @param ignoreList Ignore those city when looking for closest
     * @return           Closest city, null when every connected cities are in ignore list
     */
    public City getClosest(HashSet<City> ignoreList) {
        if (ignoreList == null){
            ignoreList = new HashSet<>(0);
        }
        int best = Integer.MAX_VALUE;
        City ret = null;
        for (Map.Entry<City, Integer> current : connexion.entrySet()) {
            if (!ignoreList.contains(current.getKey()) && current.getValue() < best){
                ret = current.getKey();
                best = current.getValue();
            }
        }
        return ret;
    }

    public City getClosestWhiteList(HashSet<City> whiteList) {
        if (whiteList == null){
            return null;
        }
        int best = Integer.MAX_VALUE;
        City ret = null;
        for (Map.Entry<City, Integer> current : connexion.entrySet()){
            if(whiteList.contains(current.getKey()) && current.getValue() < best){
                ret = current.getKey();
                best = current.getValue();
            }
        }
        return ret;
    }
}
