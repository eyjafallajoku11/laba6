package utility.creatorCommand;

import utility.CityCreator;
import utility.HashMapController;

import java.util.Iterator;

public class CreatorCommandRemoveLower extends CreatorCommand {
    public void execute(CityCreator creator) {
        try {
            creator.create_city();
        } catch (NullPointerException e) {
            System.out.println("не хватает обязательных переменных, город не создан");}
        catch (Exception e) {
            System.out.println("ошибка в формате данных, город не создан");}
        Iterator<Long> it = HashMapController.getMap().keySet().iterator();
        while (it.hasNext()) {
            long key = it.next();
            if (creator.getCity().compareTo(HashMapController.get_by_ID(key))>0){
                it.remove();
            }
        }
    }
}
