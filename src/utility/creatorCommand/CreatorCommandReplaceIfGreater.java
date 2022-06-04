package utility.creatorCommand;


import utility.CityCreator;
import utility.CommandManager;
import utility.HashMapController;
import utility.Request;

import static java.lang.System.out;

public class CreatorCommandReplaceIfGreater extends CreatorCommand {
    public String execute(String[] creatorData) {
        CityCreator creator = new CityCreator(creatorData);
        try {
            String id = creatorData[12];
            if (!HashMapController.contains_ID(Long.parseLong(id))) {
                CommandManager.execute(new Request("insert",creatorData));
                return "город добавлен";
            } else {
                try{
                    creator.create_city();
                    if (creator.getCity().compareTo(HashMapController.get_by_ID(Long.parseLong(id))) > 0) {
                        CommandManager.execute(new Request("remove_by_key", id));
                        creator.add_city_to_map();
                        return "город добавлен";
                    }
                    return "город не добавлен";
                } catch (NullPointerException e) {
                    return ("не хватает обязательных переменных, город не создан");
                }
                catch (Exception e){
                    return ("ошибка в формате данных, город не создан");
                }
            }
        }
        catch (NumberFormatException e) {
            return ("че с ключом, это цифра должна быть");
        }
        catch (Exception e){
            return ("вообще всё плохо");
        }
    }
}
