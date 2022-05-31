import utility.CommandManager;
import utility.bareCommand.*;
import utility.creatorCommand.CreatorCommandInsert;
import utility.creatorCommand.CreatorCommandRemoveLower;
import utility.creatorCommand.CreatorCommandReplaceIfGreater;
import utility.creatorCommand.CreatorCommandUpdate;
import utility.dataCommand.*;
import utility.humanCommand.HumanCommandFilterLessThanGovernor;

public class Main {
    public static void main(String[] args) {
        {CommandManager.bareRegister("help", new BareCommandHelp());
        CommandManager.bareRegister("exit", new BareCommandExit());
        CommandManager.bareRegister("show", new BareCommandShow());
        CommandManager.bareRegister("clear", new BareCommandClear());
        CommandManager.bareRegister("print_descending", new BareCommandPrintDescending());
        CommandManager.bareRegister("history", new BareCommandHistory());
        CommandManager.bareRegister("info", new BareCommandInfo());

        CommandManager.creatorRegister("remove_lower", new CreatorCommandRemoveLower());
        CommandManager.creatorRegister("insert", new CreatorCommandInsert());
        CommandManager.creatorRegister("update", new CreatorCommandUpdate());
        CommandManager.creatorRegister("replace_if_greater", new CreatorCommandReplaceIfGreater());

        CommandManager.dataRegister("execute_script", new DataCommandExecuteScript());
        CommandManager.dataRegister("save", new DataCommandSave());
        CommandManager.dataRegister("open", new DataCommandOpenFile());
        CommandManager.dataRegister("remove_key", new DataCommandRemoveKey());
        CommandManager.dataRegister("remove_all_by_government", new DataCommandRemoveAllByGovernment());

        CommandManager.humanRegister("filter_less_than_governor", new HumanCommandFilterLessThanGovernor());}
        Server.run(1567);
    }
}
