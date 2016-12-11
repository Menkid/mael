import java.io.*;
import java.util.LinkedHashSet;

/**
 * Created by Johan on 11.12.2016.
 */
public class MinimumSpanningTree {
    private static LinkedHashSet<City> citySet;
    private static FileWriter writer;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException{
        writer = new FileWriter("out/MST.out");
        FileInputStream inStream = new FileInputStream("C:\\Users\\Johan\\Desktop\\mael\\SDA-TP10\\res\\sda_graph.txt");
        reader = new BufferedReader(new InputStreamReader(inStream));
        citySet = new LinkedHashSet<>();
        parseFile();
    }

    private static void parseFile() throws IOException{
        String line;
        String[] dists;
        City current = null;
        City next = null;
        while (!(line = reader.readLine()).startsWith("*")) {
            try {
                current = next;
                next = new City(line);
            } catch (IllegalArgumentException e) {
                next = null;
                dists = line.split(" ");
                int i = 0;
                for (City destination : citySet) {
                    current.addConnexion(destination, Integer.parseInt(dists[i % 16])); // 16 values per line
                    if (++i < citySet.size() && (i % 16) == 0) {
                        line = reader.readLine();
                        dists = line.split(" ");
                    }
                }
            }
            if (current != null) {
                citySet.add(current);
            }
        }
    }

    private static void buildMST() {
        
    }
}
