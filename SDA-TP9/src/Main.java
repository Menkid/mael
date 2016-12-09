/**
 * @author Maël Cattin
 */
public class Main {

    public static void main(String[] args){
        LinearProbingTable myTable = initTable(500);
        runStat(myTable, 500);
        runStat(myTable, 600);
        runStat(myTable, 700);
        runStat(myTable, 800);
        runStat(myTable, 900);
    }

    private static LinearProbingTable initTable(int items){
        LinearProbingTable newTable = new LinearProbingTable();
        int ht_size=997;
        int keysPerCell = 10;
        for(int j=0; j<items; j++){
            // insert data
            int aKey = (int)(java.lang.Math.random()*keysPerCell*ht_size);
            Hashable h = new HashableImpl(aKey);
            newTable.insert(h);
        }
        return newTable;
    }

    private static void runStat(LinearProbingTable myTable, int items){
        myTable = initTable(items);
        int size = myTable.array.length;
        float load = (float) items / (float) size;
        int miss = 0;
        for(int i = 0; i < 1000; i++){
            int aKey = (int)(java.lang.Math.random()*9970);
            Hashable h = new HashableImpl(aKey);
            myTable.insert(h);
            miss += myTable.findPosMiss(h)[1];
            myTable = initTable(items);
        }
        System.out.println(miss + " miss with " + items + " items");
    }


}
