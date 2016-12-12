import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * @author Mael Cattin
 */
public class MinimumSpanningTree {
    private static LinkedHashSet<City> citySet;
    private static FileWriter writer;
    private static BufferedReader reader;


    public static void main(String[] args) throws IOException{
        writer = new FileWriter("C:\\Users\\Johan\\Desktop\\mael\\SDA-TP10\\out\\MST.out");
        FileInputStream inStream = new FileInputStream("C:\\Users\\Johan\\Desktop\\mael\\SDA-TP10\\res\\sda_graph.txt");
        reader = new BufferedReader(new InputStreamReader(inStream));
        citySet = new LinkedHashSet<>();
        parseFile();
        buildMST();
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
        System.out.println("Parsing finished, " + citySet.size() + " cities loaded.");
    }

    private static void buildMST() throws IOException {
        HashSet<City> mst = new HashSet<>();
        int cost = 0;
        City current = null;
        City closest;
        for (City foo : citySet) {
            current = foo;
            break;
        }
        mst.add(current);
        while((closest = getClosest(mst)) != null) {
            current = closest.getClosestWhiteList(mst);
            cost += current.getDistTo(closest);
            mst.add(closest);
            writer.append(current.getName() + " " + current.getState() + ", " +
                    closest.getName() + " " + closest.getState() + "\n");
        }
        writer.append("Cost: " + cost);
        writer.flush();
        writer.close();
    }

    private static City getClosest(HashSet<City> existingTree) {
        int best = Integer.MAX_VALUE;
        City ret = null;
        City candidate;
        for (City current : existingTree){
            candidate = current.getClosest(existingTree);
            if (candidate != null && current.getDistTo(candidate) < best){
                ret = candidate;
                best = current.getDistTo(candidate);
            }
        }
        return ret;
    }
}
