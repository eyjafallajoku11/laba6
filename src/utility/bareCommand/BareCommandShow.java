package utility.bareCommand;

import utility.HashMapController;

public class BareCommandShow extends BareCommand {
    public String execute(){
        if (HashMapController.ID_set().isEmpty()) return("коллекция пустая");
        else {
            StringBuilder output = new StringBuilder();
            for (long keys : HashMapController.ID_set()) {
                output.append(HashMapController.get_by_ID(keys).toString());
                output.append("\n");
            }
            return output.toString();
        }
    }
}
