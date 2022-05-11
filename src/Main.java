import utility.*;
import utility.bareCommand.*;
import utility.creatorCommand.CreatorCommandInsert;
import utility.creatorCommand.CreatorCommandRemoveLower;
import utility.creatorCommand.CreatorCommandReplaceIfGreater;
import utility.creatorCommand.CreatorCommandUpdate;
import utility.dataCommand.*;
import utility.humanCommand.HumanCommandFilterLessThanGovernor;

import java.io.BufferedInputStream;
import java.io.IOException;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {

    public static String[] readLine() {
        StringBuilder response = new StringBuilder();
        String[] str_out = new String[2];
        try {
            BufferedInputStream Buf_in = new BufferedInputStream(in);
            int in;
            char inChar;
            short t = 0;
            do {
                in = Buf_in.read();
                if (in != -1) {
                    if (in==32 || in==10){
                        str_out[t]=response.toString();
                        t++;
                        response = new StringBuilder();
                    }
                    else {
                        inChar = (char) in;
                        response.append(inChar);
                    }
                }
            } while ((in != -1) & (in != 10) & (t!=2));
            return str_out;
        } catch (IOException e) {
            out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] arguments) {
        CommandManager.bareRegister("help", new BareCommandHelp());
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

        CommandManager.humanRegister("filter_less_than_governor", new HumanCommandFilterLessThanGovernor());

        while (true) {
            String[] t = readLine();
            CommandManager.execute(t[0],t[1]);
        }
    }
}