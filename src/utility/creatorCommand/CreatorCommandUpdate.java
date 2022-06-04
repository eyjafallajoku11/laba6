package utility.creatorCommand;

import utility.CityCreator;
import utility.CommandManager;
import utility.Request;

public class CreatorCommandUpdate extends CreatorCommand {
    public String execute(String[] creatorData) {
        CommandManager.execute(new Request( "remove_by_key",creatorData[12]));
        CommandManager.execute(new Request("insert", creatorData));
        return ("город обновлён");
    }
}
