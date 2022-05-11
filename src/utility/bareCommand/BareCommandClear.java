package utility.bareCommand;

import utility.HashMapController;

public class BareCommandClear extends BareCommand {
    public void execute() {
        HashMapController.clear();
    }

}
