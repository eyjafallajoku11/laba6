package utility.bareCommand;

import utility.HashMapController;

import static java.lang.System.out;

public class BareCommandInfo extends BareCommand {
    public void execute(){
        try{
        out.println("тип коллекции HashMap\nэлементы типа <Long, City>\nкол-во элементов="+ HashMapController.map_size()+"\nвремя инициализации: "+HashMapController.getInitialisation().toString());
        }
        catch (NullPointerException e){
            out.println("нужно сначала коллекцию из файла открыть");
        }
        catch (Exception e ){out.println("фигню делаешь");}
    }
}
