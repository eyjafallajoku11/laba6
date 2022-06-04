package utility.bareCommand;

import static java.lang.System.out;

public class BareCommandExit extends BareCommand {
    public String execute() {
        System.exit(0);
        return "адьос амигос";
    }

}
