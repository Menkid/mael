import java.io.*;
import java.util.LinkedHashSet;

/**
 * Created by Johan on 11.12.2016.
 */
public class MinimumSpanningTree {
    private static LinkedHashSet<City> citySet;

    public static void main(String[] args) throws IOException{
        FileWriter writer = new FileWriter("out/MST.out");
        FileInputStream inStream = new FileInputStream("C:\\Users\\Johan\\Desktop\\mael\\SDA-TP10\\res\\sda_graph.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        citySet = new LinkedHashSet<>();
        String line;
        String[] dists;
        City current;
        City next;
        while((line = reader.readLine()) != null){
            try {
                City city = new City(line);
            } catch (IllegalArgumentException e){
                dists = line.split(" ");
            }

        }
    }
}
