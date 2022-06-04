package utility.bareCommand;

import utility.HashMapController;

public class BareCommandClear extends BareCommand {
    public String execute() {
        HashMapController.clear();
        return "коллекция очищена";
    }
}
