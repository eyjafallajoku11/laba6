package utility.bareCommand;

import gorod.City;
import utility.HashMapController;

import java.util.TreeMap;

public class BareCommandPrintDescending extends BareCommand {
    public void execute() {
        TreeMap<Long, City> sorted = new TreeMap<>(HashMapController.getMap());
        for (long key : sorted.keySet()){
            System.out.println(sorted.get(key));
            System.out.println();
        }
        }
    }
