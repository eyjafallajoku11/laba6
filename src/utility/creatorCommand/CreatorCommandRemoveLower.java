package utility.creatorCommand;

import utility.CityCreator;
import utility.HashMapController;

import java.util.Iterator;

public class CreatorCommandRemoveLower extends CreatorCommand {
    public String execute(String[] creatorData) {
        CityCreator creator = new CityCreator(creatorData);
        try {
            creator.create_city();
            Iterator<Long> it = HashMapController.getMap().keySet().iterator();
            int count =0;
            while (it.hasNext()) {
                long key = it.next();
                if (creator.getCity().compareTo(HashMapController.get_by_ID(key))>0){
                    it.remove();
                    count++;
                }
            }
            return "удалено " + count + " городов";
        } catch (NullPointerException e) {
            return ("не хватает обязательных переменных, город не создан");}
        catch (Exception e) {
            return ("ошибка в формате данных, город не создан");}

    }
}
