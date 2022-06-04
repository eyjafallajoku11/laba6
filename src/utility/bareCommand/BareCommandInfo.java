package utility.bareCommand;

import utility.HashMapController;

import static java.lang.System.out;

public class BareCommandInfo extends BareCommand {
    public String execute(){
        try{
            return ("тип коллекции HashMap\nэлементы типа <Long, City>\nкол-во элементов="+ HashMapController.map_size()+"\nвремя инициализации: "+HashMapController.getInitialisation().toString());
        }
        catch (NullPointerException e){
            return("нужно сначала коллекцию из файла открыть");
        }
        catch (Exception e ){return ("фигню делаешь");}
    }
}
