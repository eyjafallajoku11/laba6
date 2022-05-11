package utility.bareCommand;

public class BareCommandHistory extends BareCommand {
    static String[] history = new String[8];
    static short count = 0;
    public static void add_to_history(String command) {
        history[count] = command;
        count++;
        if (count == 8) count = 0;
    }
    public void execute(){
        for (short i = count; i<8; i++){
            System.out.println(history[i]);
        }
        for (short i = 0; i<count; i++){
            System.out.println(history[i]);
        }
    }
}
