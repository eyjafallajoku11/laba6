package utility.bareCommand;

import gorod.City;
import utility.HashMapController;

import java.util.TreeMap;

public class BareCommandPrintDescending extends BareCommand {
    public String execute() {
        TreeMap<Long, City> sorted = new TreeMap<>(HashMapController.getMap());
        StringBuilder output = new StringBuilder();
        for (long key : sorted.keySet()){
            output.append(sorted.get(key));
            output.append("\n");
        }
        return output.toString();
    }
}
