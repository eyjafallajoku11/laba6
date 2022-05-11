package utility.creatorCommand;

import utility.CityCreator;
import utility.CommandManager;

public class CreatorCommandUpdate extends CreatorCommand {
    public void execute(CityCreator creator) {
        CommandManager.execute("remove_by_key",creator.get_data(12));
        CommandManager.execute("insert", creator);
    }
}
