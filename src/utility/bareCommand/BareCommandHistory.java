package utility.bareCommand;

public class BareCommandHistory extends BareCommand {
    static String[] history = new String[8];
    static short count = 0;
    public static void add_to_history(String command) {
        history[count] = command;
        count++;
        if (count == 8) count = 0;
    }
    public String execute(){
        StringBuilder output = new StringBuilder();
        for (short i = count; i<8; i++){
            output.append(history[i]);
            output.append("\n");
        }
        for (short i = 0; i<count; i++){
            output.append(history[i]);
            output.append("\n");
        }
        return output.toString();
    }
}
