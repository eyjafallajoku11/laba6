package utility.creatorCommand;


import utility.CityCreator;
import utility.CommandManager;
import utility.HashMapController;

import static java.lang.System.out;

public class CreatorCommandReplaceIfGreater extends CreatorCommand {
    public void execute(CityCreator creator) {
        try {
            String id = creator.get_data(12);
            if (!HashMapController.contains_ID(Long.parseLong(id))) {
                CommandManager.execute("insert",creator);
            } else {
                try{
                    creator.create_city();
                    if (creator.getCity().compareTo(HashMapController.get_by_ID(Long.parseLong(id))) > 0)
                        CommandManager.execute("remove_by_key",id);
                    creator.add_city_to_map();
                } catch (NullPointerException e)
                {System.out.println("не хватает обязательных переменных, город не создан");}
                catch (Exception e){System.out.println("ошибка в формате данных, город не создан");}
            }
        }
        catch (NumberFormatException e) {
            out.println("че с ключом, это цифра должна быть");
        }
        catch (Exception e){
            out.println("совсем фигня какая-то");
        }
    }
}
