package utility.dataCommand;

import utility.HashMapController;

import static java.lang.System.out;

public class DataCommandRemoveKey extends DataCommand {
    public void execute (String data) {
        try {
            HashMapController.remove_from_map_by_ID(Long.parseLong(data));
            System.out.println("канселим город с ID=" + data);
        }
        catch (NumberFormatException e) {
        out.println("че с ключом, это цифра должна быть");
        }
        catch (Exception e){
        out.println("совсем фигня какая-то");
        }
    }
}
