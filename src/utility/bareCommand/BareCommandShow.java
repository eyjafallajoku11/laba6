package utility.bareCommand;

import utility.HashMapController;

public class BareCommandShow extends BareCommand {
    public void execute(){
        if (HashMapController.ID_set().isEmpty()) System.out.println("коллекция пустая");
        else {
            for (long keys : HashMapController.ID_set()) {
                System.out.println(HashMapController.get_by_ID(keys).toString());
                System.out.println();
            }
        }
    }
}
