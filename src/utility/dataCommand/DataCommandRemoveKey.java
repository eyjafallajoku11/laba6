package utility.dataCommand;

import utility.HashMapController;

public class DataCommandRemoveKey extends DataCommand {
    public String execute (String data) {
        try {
            HashMapController.remove_from_map_by_ID(Long.parseLong(data));
            return ("канселим город с ID=" + data);
        }
        catch (NumberFormatException e) {
            return ("че с ключом, это цифра должна быть");
        }
        catch (Exception e){
            return ("совсем фигня какая-то");
        }
    }
}
