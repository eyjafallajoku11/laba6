package utility.creatorCommand;

import utility.CityCreator;
import utility.HashMapController;

import java.util.Objects;

import static java.lang.System.out;
public class CreatorCommandInsert extends CreatorCommand {
    public String execute(String[] creatorData){
        try {
            if (!HashMapController.contains_ID(Long.parseLong(creatorData[12]))) {
                try {
                    CityCreator creator = new CityCreator(creatorData);
                    creator.create_city();
                    creator.add_city_to_map();
                    if (Objects.isNull(HashMapController.getInitialisation())) {
                        HashMapController.initialise();
                    }
                    return "город добавлен в коллекцию";
                } catch (NullPointerException e){
                    return ("не хватает обязательных переменных, город не создан");
                } catch (Exception e){
                    return ("ошибка в формате данных, город не создан");
                }
                //[x,y,name,creation date,area,population,metersAboveSeaLevel,climate,government,standardOfLiving,govName,birthday,id,1/0 boolean Governor]
            } else {
                return ("этот ключ уже занят");
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
